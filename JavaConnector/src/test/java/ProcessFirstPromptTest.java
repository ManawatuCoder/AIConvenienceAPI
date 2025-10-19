import com.azure.ai.openai.OpenAIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testsupport.OpenAIStubs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class ProcessFirstPromptTest {

    @BeforeEach
    void setup() throws Exception {
        // Ensure a non-null deployment name for Main.sendFragments(...)
        setPrivateStatic("DEPLOYMENT_NAME", "test-deploy");

        // Overwrite the FIRST_PROMPT file with a known, tiny template.
        // This makes the test deterministic even if the file changed elsewhere.
        Path promptPath = Path.of(config.PathConfiguration.FIRST_PROMPT);
        Files.writeString(promptPath, "P1 {methodNames} :: {guidelines}");
    }

    @Test
    void replaces_placeholders_and_returns_output() throws Exception {
        // Stub the OpenAI call to return "no"
        OpenAIClient client = OpenAIStubs.clientWith(
                new OpenAIStubs.SeqMockHttpClient(new byte[][]{
                        OpenAIStubs.chatCompletionsLegacy("no")
                }));

        // Inputs to processFirstPrompt
        String methods = "[getA(, getB(]";
        String headings = "Naming\nError handling\n";
        StringBuilder report = new StringBuilder();

        // Call: private static String processFirstPrompt(OpenAIClient, String, String, StringBuilder)
        Method m = Main.class.getDeclaredMethod("processFirstPrompt",
                com.azure.ai.openai.OpenAIClient.class, String.class, String.class, StringBuilder.class);
        m.setAccessible(true);
        String out = (String) m.invoke(null, client, methods, headings, report);

        // Output from model stub
        assertEquals("no", out);

        // Report content checks
        String r = report.toString();
        assertTrue(r.contains("Prompt 1: Method and Guideline Analysis"),
                "Report should include the Step 1 section header");

        // Our template label must appear (we wrote it in @BeforeEach)
        assertTrue(r.contains("P1 "), "Prompt body should include our template label");

        // Placeholders must be replaced
        assertFalse(r.contains("{methodNames}"), "methodNames placeholder should be replaced");
        assertFalse(r.contains("{guidelines}"), "guidelines placeholder should be replaced");

        // The replaced content should appear inside the body
        assertTrue(r.contains("getA("), "Methods list should appear in the prompt body");
        assertTrue(r.contains("Naming"), "Guideline headings should appear in the prompt body");
    }

    private static void setPrivateStatic(String name, String value) throws Exception {
        Field f = Main.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(null, value);
    }
}

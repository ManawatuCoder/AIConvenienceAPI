import com.azure.ai.openai.OpenAIClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import testsupport.OpenAIStubs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProcessSecondPromptTest {

    @BeforeEach
    void setup() throws Exception {
        // Ensure a non-null deployment name for Main.sendFragments(...)
        setPrivateStatic("DEPLOYMENT_NAME", "test-deploy");

        // Overwrite the SECOND_PROMPT file with a known, tiny template.
        // This makes the test deterministic even if the file changed elsewhere.
        Path promptPath = Path.of(config.PathConfiguration.SECOND_PROMPT);
        Files.writeString(promptPath, "P2 {code} :: {guidelines}");
    }

    @Test
    void builds_selected_code_and_guidelines() throws Exception {
        // Stub the OpenAI call to return "no"
        OpenAIClient client = OpenAIStubs.clientWith(
                new OpenAIStubs.SeqMockHttpClient(new byte[][]{
                        OpenAIStubs.chatCompletionsLegacy("no")
                }));

        // Flag one method + one guideline
        Map<String,String> flaggedMethods = new LinkedHashMap<>();
        flaggedMethods.put("getA(", "public String getA(){ return \"A\"; }");

        Map<String,String> flaggedGuidelines = new LinkedHashMap<>();
        flaggedGuidelines.put("Naming", "Follow naming conventions.");

        StringBuilder report = new StringBuilder();

        // Call: private static String processSecondPrompt(OpenAIClient, Map, Map, StringBuilder)
        Method m = Main.class.getDeclaredMethod("processSecondPrompt",
                com.azure.ai.openai.OpenAIClient.class, Map.class, Map.class, StringBuilder.class);
        m.setAccessible(true);

        String out = (String) m.invoke(null, client, flaggedMethods, flaggedGuidelines, report);

        // Model stub returned "no"
        assertEquals("no", out);

        // Report content checks
        String r = report.toString();

        // Section header must be present
        assertTrue(r.contains("Prompt 2: Convenience Wrapper Generation"),
                "Report should include the Step 2 section header");

        // Our template label must appear (we wrote it in @BeforeEach)
        assertTrue(r.contains("P2 "), "Prompt body should include our template label");

        // Placeholders must be replaced
        assertFalse(r.contains("{code}"), "code placeholder should be replaced");
        assertFalse(r.contains("{guidelines}"), "guidelines placeholder should be replaced");

        // The replaced content should appear inside the body
        assertTrue(r.contains("getA("), "Method code should appear in the prompt body");
        assertTrue(r.contains("Naming"), "Guideline heading should appear in the prompt body");
    }

    private static void setPrivateStatic(String name, String value) throws Exception {
        Field f = Main.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(null, value);
    }
}
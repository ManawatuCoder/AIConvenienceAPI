import com.azure.ai.openai.OpenAIClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import testsupport.OpenAIStubs;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class SendFragmentsTest {

    @BeforeAll
    static void setDeployment() throws Exception {
        setPrivateStatic("DEPLOYMENT_NAME", "test-deploy");
    }

    @Test
    void returns_assistant_content() throws Exception {
        var client = OpenAIStubs.clientWith(
                new OpenAIStubs.SeqMockHttpClient(new byte[][]{
                        OpenAIStubs.chatCompletionsLegacy("hello-world")
                }));

        Method m = Main.class.getDeclaredMethod("sendFragments",
                com.azure.ai.openai.OpenAIClient.class, String.class);
        m.setAccessible(true);
        String out = (String) m.invoke(null, client, "prompt");

        assertEquals("hello-world", out);
    }

    private static void setPrivateStatic(String name, String value) throws Exception {
        Field f = Main.class.getDeclaredField(name);
        f.setAccessible(true);
        f.set(null, value);
    }
}

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatRequestUserMessage;
import org.junit.jupiter.api.Test;
import testsupport.OpenAIStubs;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SaintyChatCompletionsTest {
    @Test
    void two_calls_both_have_choices() {
        OpenAIClient client = OpenAIStubs.clientWith(
                new OpenAIStubs.SeqMockHttpClient(new byte[][]{
                        OpenAIStubs.chatCompletionsLegacy("one"),
                        OpenAIStubs.chatCompletionsLegacy("two")
                }));

        ChatCompletionsOptions opts =
                new ChatCompletionsOptions(List.of(new ChatRequestUserMessage("hi")));

        ChatCompletions c1 = client.getChatCompletions("test-deploy", opts);
        assertNotNull(c1);
        assertFalse(c1.getChoices().isEmpty());
        assertEquals("one", c1.getChoices().get(0).getMessage().getContent());

        ChatCompletions c2 = client.getChatCompletions("test-deploy", opts);
        assertNotNull(c2);
        assertFalse(c2.getChoices().isEmpty());
        assertEquals("two", c2.getChoices().get(0).getMessage().getContent());
    }
}

package testsupport;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpRequest;
import com.azure.core.http.HttpResponse;
import com.azure.core.test.http.MockHttpClient;
import com.azure.core.test.http.MockHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

public final class OpenAIStubs {

    /** Chat Completions payload with assistant content as a PLAIN STRING. */
    public static byte[] chatCompletionsLegacy(String contentText) {
        String json = """
      {
        "id": "chatcmpl-test",
        "object": "chat.completion",
        "created": 0,
        "model": "gpt-test",
        "choices": [
          {
            "index": 0,
            "finish_reason": "stop",
            "logprobs": null,
            "message": {
              "role": "assistant",
              "content": %s,
              "tool_calls": []
            }
          }
        ],
        "usage": { "prompt_tokens": 10, "completion_tokens": 2, "total_tokens": 12 }
      }
      """.formatted(toJsonString(contentText));
        return json.getBytes(StandardCharsets.UTF_8);
    }

    /** When the assistant content itself to be a JSON string (e.g., groups array). */
    public static byte[] chatWithJsonText(String jsonText) {
        return chatCompletionsLegacy(jsonText);
    }

    /** Sequence mock client returning successive bodies. Sets Content-Type and Content-Length. */
    public static final class SeqMockHttpClient extends MockHttpClient {
        private final byte[][] responses;
        private final AtomicInteger idx = new AtomicInteger(0);
        public SeqMockHttpClient(byte[][] responses) { this.responses = responses; }

        @Override public Mono<HttpResponse> send(HttpRequest request) {
            int i = Math.min(idx.getAndIncrement(), responses.length - 1);
            byte[] body = responses[i];
            HttpHeaders headers = new HttpHeaders()
                    .set("Content-Type", "application/json")
                    .set("Content-Length", String.valueOf(body.length));
            return Mono.just(new MockHttpResponse(request, 200, headers, body));
        }
    }

    /** Build client that uses the HttpClient. */
    public static OpenAIClient clientWith(HttpClient httpClient) {
        return new OpenAIClientBuilder()
                .endpoint("https://unit.test")
                .credential(new AzureKeyCredential("test"))
                .httpClient(httpClient)
                .buildClient();
    }

    private static String toJsonString(String s) {
        return "\"" + s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n") + "\"";
    }
}

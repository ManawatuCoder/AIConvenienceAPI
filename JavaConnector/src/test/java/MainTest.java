import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.azure.ai.openai.OpenAIClient;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MainTest {
  private static Properties props = new Properties();
  static Path tempConfigProps;

  private Path createdPromptFile;
  private final List<Path> createdPaths = new ArrayList<>();

  // Creates new test config file
  @BeforeAll
  static void initTempConfigProperties() throws IOException {
    tempConfigProps = Files.createTempFile("testConfig", ".properties");
    Files.writeString(
        tempConfigProps,
        "AZURE_OPENAI_ENDPOINT=exampleEndpoint\n" + "AZURE_OPENAI_KEY=exampleKey",
        StandardCharsets.UTF_8);
  }

  @AfterEach
  void cleanupEach() {
    deleteIfExists(createdPromptFile);

    for (Path p : createdPaths) {
      deleteIfExists(p);
      tryDeleteEmptyDir(p);
    }
    createdPaths.clear();
  }

  @AfterAll
  static void cleanupAll() {
    deleteIfExists(tempConfigProps);
    props.clear();
  }

  // Helpers
  private static void deleteIfExists(Path p) {
    if (p == null) return;
    try {
      Files.deleteIfExists(p);
    } catch (Exception ignored) {
    }
  }

  private static void tryDeleteEmptyDir(Path p) {
    if (p == null) return;
    try {
      if (Files.isDirectory(p)) {
        try (var stream = Files.list(p)) {
          if (stream.findAny().isEmpty()) {
            Files.deleteIfExists(p);
          }
        }
      }
    } catch (Exception ignored) {
    }
  }

  // Test that api endpoint and key load properly
  @Test
  void testLoadConfigProperties() {
    // call Main.loadConfigProperties(tempFile) with the temp file
    props = Main.loadConfigProperties(tempConfigProps);

    String endpoint = props.getProperty("AZURE_OPENAI_ENDPOINT");
    String apiKey = props.getProperty("AZURE_OPENAI_KEY");

    // Check that endpoint and api key loaded properly
    assertEquals("exampleEndpoint", endpoint);
    assertEquals("exampleKey", apiKey);
  }

  // Test on a file that does not exist
  @Test
  void testMissingFile() {
    Path nonExistentFile = Path.of("non_existent_file.properties");
    assertThrows(RuntimeException.class, () -> Main.loadConfigProperties(nonExistentFile));
  }

  // Test that OpenAIClient is created properly
  @Test
  void testCreateOpenAIClient() {
    // Load config before creating client
    Main.loadConfigProperties(tempConfigProps);

    OpenAIClient client = Main.createOpenAIClient();
    assertNotNull(client, "OpenAIClient should not be null");
  }
}

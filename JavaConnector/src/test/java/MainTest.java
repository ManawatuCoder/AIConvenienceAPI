import com.azure.ai.openai.OpenAIClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    private static Properties props = new Properties();
    static Path tempConfigProps;

    // Creates new test config file
    @BeforeAll
    static void initTempConfigProperties() throws IOException {
        tempConfigProps = Files.createTempFile("testConfig", ".properties");
        Files.writeString(tempConfigProps, "AZURE_OPENAI_ENDPOINT=exampleEndpoint\n" + "AZURE_OPENAI_KEY=exampleKey");
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
        assertThrows(RuntimeException.class, ()->Main.loadConfigProperties(nonExistentFile));
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
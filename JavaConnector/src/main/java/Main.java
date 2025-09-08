import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.util.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.utils.Log;

import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    // Azure OpenAI configuration constants
    private static final String AZURE_OPENAI_ENDPOINT = "enter your endpoint here";
    private static final String AZURE_OPENAI_KEY = "enter your key here";
    private static final String DEPLOYMENT_NAME = "gpt-4.1";

    // Creates and configures the Azure OpenAI client
    private static OpenAIClient createOpenAIClient() {
        String endpoint = Configuration.getGlobalConfiguration().get("AZURE_OPENAI_ENDPOINT", AZURE_OPENAI_ENDPOINT);
        String apiKey = Configuration.getGlobalConfiguration().get("AZURE_OPENAI_KEY", AZURE_OPENAI_KEY);
        return new OpenAIClientBuilder().endpoint(endpoint).credential(new AzureKeyCredential(apiKey)).buildClient();
    }

    private static String readFileContent(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path)) {
            String error = "File not found: " + filePath + " (absolute path: " + path.toAbsolutePath() + ")";
            System.err.println("Error: " + error);
            throw new IOException(error);
        }
        return Files.readString(path);
    }

    private static List<String> readAllSourceFiles(String baseDir) throws IOException {
        List<String> sourceFileContents = new ArrayList<>();
        Path basePath = Paths.get(baseDir);

        if (!Files.exists(basePath)) {
            String error = "Directory not found: " + baseDir + " (absolute path: " + basePath.toAbsolutePath() + ")";
            System.err.println("Error: " + error);
            throw new IOException(error);
        }

        try (Stream<Path> paths = Files.walk(basePath)) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> !path.toString().contains(".class")) // Exclude compiled files
                    .forEach(path -> {
                        try {
                            String relativePath = basePath.relativize(path).toString();
                            String content = Files.readString(path);
                            String fileInfo = String.format("\n\n=== %s ===\n%s", relativePath, content);
                            sourceFileContents.add(fileInfo);
                            System.out.println("Read: " + relativePath);
                        } catch (IOException e) {
                            System.err.println("Error reading file: " + path + " - " + e.getMessage());
                        }
                    });
        }

        return sourceFileContents;
    }

    // Generates convenience wrapper and returns the result as string
    private static String generateConvenienceWrapper(OpenAIClient client, String inputSpecs, String typeSpecContent,
            List<String> srcFiles) {

        // Build the content to be analyzed
        StringBuilder contentBuilder = new StringBuilder();

        // Add InputSpecs content first for reference
        contentBuilder.append("=== INPUT SPECIFICATIONS (InputSpecs.txt) ===\n");
        contentBuilder.append(inputSpecs);
        contentBuilder.append("\n\n");

        // Add TypeSpec content
        contentBuilder.append("=== GENERATED TYPESPEC (blob-storage.tsp) ===\n");
        contentBuilder.append(typeSpecContent);
        contentBuilder.append("\n\n");

        // Add source files from tsp-output/src
        contentBuilder.append("=== KEY GENERATED SOURCE FILES (SAMPLE) ===\n");

        // Send only the most important files to stay within token limits
        int filesIncluded = 0;
        int maxFiles = 5;
        for (String srcFile : srcFiles) {
            if (filesIncluded >= maxFiles)
                break;

            // Small sample size as token limits are strict
            if (srcFile.contains("Client.java") ||
                    srcFile.contains("Builder.java") ||
                    srcFile.contains("BlobContainer.java") ||
                    srcFile.contains("BlobServiceProperties.java") ||
                    srcFile.contains("ContainerProperties.java")) {
                contentBuilder.append(srcFile);
                filesIncluded++;
            }
        }

        List<ChatRequestMessage> messages = new ArrayList<>();
        // Send all content including InputSpecs, TypeSpec, and generated code
        messages.add(new ChatRequestUserMessage(contentBuilder.toString()));

        // Chat settings for the AI model
        ChatCompletionsOptions options = new ChatCompletionsOptions(messages)
                .setMaxTokens(4000) // Increase max tokens for better output
                .setTemperature(0.3) // Lower temperature for more analytical response
                .setTopP(0.95);

        try {
            ChatCompletions chatCompletions = client.getChatCompletions(DEPLOYMENT_NAME, options);
            ChatChoice choice = chatCompletions.getChoices().get(0);
            String aiResponse = choice.getMessage().getContent();

            System.out.println("=== GENERATED JAVA CONVENIENCE WRAPPER ===");
            System.out.println(aiResponse);

            // Store the AI response to a file
            saveResponseToFile(aiResponse, chatCompletions.getUsage());

            System.out.println("\n=== USAGE STATISTICS ===");
            System.out.println("Prompt tokens: " + chatCompletions.getUsage().getPromptTokens());
            System.out.println("Completion tokens: " + chatCompletions.getUsage().getCompletionTokens());
            System.out.println("Total tokens: " + chatCompletions.getUsage().getTotalTokens());

            // Return the generated wrapper
            return aiResponse;

        } catch (Exception e) {
            System.err.println("Error during convenience wrapper generation: " + e.getMessage());
            e.printStackTrace();

            // Save error report to file
            saveErrorReportToFile(e, contentBuilder.toString(), inputSpecs);

            // Re-throw the exception so it can be handled by the caller
            throw new RuntimeException("Failed to generate convenience wrapper: " + e.getMessage(), e);
        }
    }

    // Saves the AI response to a timestamped file
    private static void saveResponseToFile(String response, CompletionsUsage usage) {
        try {
            // Create a timestamped filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String filename = String.format("java_convenience_wrapper_%s.txt", timestamp);
            Path outputPath = Paths.get(filename);

            // Create the complete report content
            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Java Convenience Wrapper Generated by Azure OpenAI\n");
            reportBuilder.append("Generated: ")
                    .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .append("\n");
            reportBuilder.append("========================================\n\n");

            reportBuilder.append("=== GENERATED CONVENIENCE WRAPPER ===\n");
            reportBuilder.append(response);
            reportBuilder.append("\n\n");

            reportBuilder.append("=== USAGE STATISTICS ===\n");
            reportBuilder.append("Prompt tokens: ").append(usage.getPromptTokens()).append("\n");
            reportBuilder.append("Completion tokens: ").append(usage.getCompletionTokens()).append("\n");
            reportBuilder.append("Total tokens: ").append(usage.getTotalTokens()).append("\n");
            reportBuilder.append("========================================\n");

            // Write to file
            Files.writeString(outputPath, reportBuilder.toString(), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);

            System.out.println("\n=== CONVENIENCE WRAPPER GENERATED ===");
            System.out.println("Java convenience wrapper saved to: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving AI response to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Saves an error report to a timestamped file
    private static void saveErrorReportToFile(Exception error, String contentToAnalyze, String systemPrompt) {
        try {
            // Create a timestamped filename
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
            String filename = String.format("convenience_wrapper_error_%s.txt", timestamp);
            Path outputPath = Paths.get(filename);

            // Create the complete error report content
            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Java Convenience Wrapper Generation Error Report\n");
            reportBuilder.append("Generated: ")
                    .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .append("\n");
            reportBuilder.append("========================================\n\n");
            reportBuilder.append("=== ERROR DETAILS ===\n");
            reportBuilder.append("Error Type: ").append(error.getClass().getSimpleName()).append("\n");
            reportBuilder.append("Error Message: ").append(error.getMessage()).append("\n");
            reportBuilder.append("Stack Trace:\n");

            for (StackTraceElement element : error.getStackTrace()) {
                reportBuilder.append("  ").append(element.toString()).append("\n");
            }
            reportBuilder.append("\n========================================\n");

            // Write to file
            Files.writeString(outputPath, reportBuilder.toString(), StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE);
            System.out.println("\n=== ERROR REPORT SAVED ===");
            System.out.println("Error report saved to: " + outputPath.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving error report to file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Get Sync Tool Specification
    private static McpServerFeatures.SyncToolSpecification getSyncToolSpecification() {
        // Create and return a SyncToolSpecification instance
        String schema = "{\n" +
                "    \"type\": \"object\",\n" +
                "    \"properties\": {\n" +
                "        \"input\": {\n" +
                "            \"type\": \"string\"\n" +
                "        },\n" +
                "        \"output\": {\n" +
                "            \"type\": \"string\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        return new McpServerFeatures.SyncToolSpecification(
                new McpSchema.Tool("Blob-Storage-Generate-Convenience-Wrapper",
                        "Generates Convenience Wrapper for blob storage Azure API", schema),
                (exchange, arguments) -> {
                    // Define the behavior for the tool specification
                    try {
                        // Initialize
                        OpenAIClient client = createOpenAIClient();

                        // Read all required files with fixed absolute paths
                        System.out.println("Reading files...");
                        String projectRoot = "D:\\AiCon\\AIConvenienceAPI";

                        String inputSpecs = readFileContent(projectRoot + "\\PlainText\\InputSpecs.txt");
                        String typeSpecContent = readFileContent(
                                projectRoot + "\\TypeSpec_Conversion\\blob-storage.tsp");
                        List<String> srcFiles = readAllSourceFiles(
                                projectRoot + "\\TypeSpec_Conversion\\tsp-output\\clients\\java\\src");

                        // Generate convenience wrapper and get the result
                        String generatedWrapper = generateConvenienceWrapper(client, inputSpecs, typeSpecContent,
                                srcFiles);

                        // Return the generated wrapper as tool result
                        return new McpSchema.CallToolResult(generatedWrapper, false);

                    } catch (ClientAuthenticationException e) {
                        String errorMsg = "Authentication failed: " + e.getMessage()
                                + "\nPlease check your API key and endpoint.";
                        System.err.println(errorMsg);
                        return new McpSchema.CallToolResult(errorMsg, true);
                    } catch (IOException e) {
                        String errorMsg = "File reading error: " + e.getMessage();
                        System.err.println(errorMsg);
                        e.printStackTrace();
                        return new McpSchema.CallToolResult(errorMsg, true);
                    } catch (Exception e) {
                        String errorMsg = "Error occurred: " + e.getMessage();
                        System.err.println(errorMsg);
                        e.printStackTrace();
                        return new McpSchema.CallToolResult(errorMsg, true);
                    }
                });
    }

    public static void main(String[] args) {

        // STDIO Server Transport
        var transportProvider = new StdioServerTransportProvider(new ObjectMapper());
        // Create Sync Tool Specification
        var syncToolSpec = getSyncToolSpecification();

        // Create MCP Server
        McpServer.sync(transportProvider)
                .serverInfo("JavaConnector-MCP-Server", "1.0.0")
                .capabilities(McpSchema.ServerCapabilities.builder()
                        .tools(true)
                        .logging()
                        .build())
                .tools(syncToolSpec).build();

        Log.info("MCP Server created successfully");
    }
}

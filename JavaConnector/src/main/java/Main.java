
// Main class for generating Java convenience wrappers using Azure OpenAI
import codegenFragmenter.CodegenFragmenter;
import codegenFragmenter.FragmentLinker;
import config.PathConfiguration;

// Imports for Azure OpenAI SDK
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.util.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javaparser.utils.Log;
import com.google.gson.*;
import guidelinesFragmentation.GuidelineParser;

// Imports for MCP server
import io.modelcontextprotocol.server.McpServer;
import io.modelcontextprotocol.server.McpServerFeatures;
import io.modelcontextprotocol.server.transport.StdioServerTransportProvider;
import io.modelcontextprotocol.spec.McpSchema;

// Imports for file handling and utilities
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.PrintStream;
import java.io.FileOutputStream;

//Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
  private static final Properties prop = new Properties();
  // Azure OpenAI configuration constants
  private static String AZURE_OPENAI_ENDPOINT;
  private static String AZURE_OPENAI_KEY;
  private static final String DEPLOYMENT_NAME = "gpt-4.1";

  //Logging
  static {
    try {
      PrintStream fileErr = new PrintStream(new FileOutputStream("../Logs/system.err.log", true));
      System.setErr(fileErr);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  //Logger
  private static Logger logger = LoggerFactory.getLogger("Main logger");

  // Loads config properties
  private static void loadConfigProperties() {
    try (InputStream configInput = Files
            .newInputStream(Paths.get(PathConfiguration.CONFIG_PROPERTIES))) {
      prop.load(configInput);

      AZURE_OPENAI_ENDPOINT = prop.getProperty("AZURE_OPENAI_ENDPOINT");
      AZURE_OPENAI_KEY = prop.getProperty("AZURE_OPENAI_KEY");

      if (AZURE_OPENAI_KEY == null || AZURE_OPENAI_KEY.isEmpty()) {
        throw new IllegalStateException("AZURE_OPENAI_KEY is missing in config.properties");
      }
      if (AZURE_OPENAI_ENDPOINT == null || AZURE_OPENAI_ENDPOINT.isEmpty()) {
        throw new IllegalStateException("AZURE_OPENAI_ENDPOINT is missing in config.properties");
      }
    } catch (Exception e) {
      throw new RuntimeException("Unable to find config.properties", e);
    }
  }

  // Creates and configures the Azure OpenAI client
  private static OpenAIClient createOpenAIClient() {
    String endpoint = Configuration.getGlobalConfiguration().get("AZURE_OPENAI_ENDPOINT", AZURE_OPENAI_ENDPOINT);
    String apiKey = Configuration.getGlobalConfiguration().get("AZURE_OPENAI_KEY", AZURE_OPENAI_KEY);
    return new OpenAIClientBuilder()
            .endpoint(endpoint)
            .credential(new AzureKeyCredential(apiKey))
            .buildClient();
  }

  // Sends the prompt and fragments to Azure OpenAI and returns the AI response
  private static String sendFragments(OpenAIClient client, String prompt) throws IOException {

    List<ChatRequestMessage> messages = new ArrayList<>();

    // Add system prompt
    String systemPrompt = Files
            .readString(Path.of(PathConfiguration.METHODS_GUIDELINES_PROMPT));
    messages.add(new ChatRequestSystemMessage(systemPrompt));

    // Send all content including InputSpecs, TypeSpec, and generated code
    messages.add(new ChatRequestUserMessage(prompt));

    // Chat settings for the AI model
    ChatCompletionsOptions options = new ChatCompletionsOptions(messages)
            .setMaxTokens(4000) // Increase max tokens for better output
            .setTemperature(0.3) // Lower temperature for more analytical response
            .setTopP(0.95);

    try {
      ChatCompletions chatCompletions = client.getChatCompletions(DEPLOYMENT_NAME, options);
      ChatChoice choice = chatCompletions.getChoices().get(0);
      String aiResponse = choice.getMessage().getContent();
      return aiResponse;

    } catch (Exception e) {
      logger.error("Error during convenience wrapper generation: " + e.getMessage(), e);
      e.printStackTrace();
    }
    return null;
  }

  // Main workflow coordinator with progress tracking
  private static String prepareFragments(OpenAIClient client) throws Exception {
    logger.info("Starting convenience wrapper generation...");

    // Initialize components
    GuidelineParser parser = new GuidelineParser();

    // Create timestamped output file
    Path outputPath = createOutputFile();
    StringBuilder reportBuilder = createReportHeader();

    // Parse guidelines from web
    logger.info("Fetching and parsing guidelines...");
    JsonArray guidelineArray = parseGuidelines(parser);
    String headings = extractHeadings(guidelineArray);

    // Fragment the code
    logger.info("Fragmenting code...");
    Map<String, String> codeFragments = fragmentCode();
    String methods = codeFragments.keySet().toString();

    // Step 1: Get method and guideline recommendations
    logger.info("Step 1: Getting AI recommendations...");
    String methodGuidelineOutput = processFirstPrompt(client, methods, headings, reportBuilder);

    if (isNoImprovementsFound(methodGuidelineOutput)) {
      return finalizeReport(outputPath, reportBuilder, "No patterns found in code. Stopping operations.");
    }

    // Step 2: Generate convenience wrapper
    logger.info("Step 2: Generating convenience wrapper...");
    Map<String, String> flaggedMethods = extractFlaggedMethods(methodGuidelineOutput, codeFragments);
    Map<String, String> flaggedGuidelines = extractFlaggedGuidelines(methodGuidelineOutput, guidelineArray);

    String wrapperOutput = processSecondPrompt(client, flaggedMethods, flaggedGuidelines, reportBuilder);

    if (isNoImprovementsFound(wrapperOutput)) {
      return finalizeReport(outputPath, reportBuilder, "No wrapper improvements found.");
    }

    appendWrapperOutput(reportBuilder, wrapperOutput);

    // Save and return results
    String result = finalizeReport(outputPath, reportBuilder, "Java Wrapper generation completed successfully!");
    saveWrapperOutput(wrapperOutput);
    logger.info("Report saved to: " + outputPath.toString());

    return result;
  }

  // Creates and writes to separate file for only the wrapper output
  private static void saveWrapperOutput(String output) throws IOException {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getWrapperOutputPath(timestamp);
    Path outputPath = Paths.get(filename);

    String wrapperOutputContent = "Java Convenience Wrapper Generated by Azure OpenAI\nGenerated: " + timestamp + "\n\n\n" + output;

    logger.info("Wrapper saved to: " + outputPath.toString());

    Files.writeString(outputPath, wrapperOutputContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
  }

  // Creates a timestamped output file path
  private static Path createOutputFile() {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getLogsOutputPath(timestamp);
    return Paths.get(filename);
  }

  // Creates the report header
  private static StringBuilder createReportHeader() {
    StringBuilder reportBuilder = new StringBuilder();
    reportBuilder.append("Java Convenience Wrapper Generated by Azure OpenAI\n");
    reportBuilder.append("Generated: ")
            .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .append("\n\n");
    return reportBuilder;
  }

  // Parses guidelines from the specified URL and returns as a JsonArray
  private static JsonArray parseGuidelines(GuidelineParser parser) throws Exception {
    String guidelineString = Files.readString(
            Path.of(GuidelineParser.parse("https://azure.github.io/azure-sdk/java_introduction.html", logger)));
    return JsonParser.parseString(guidelineString).getAsJsonArray();
  }

  // Extracts the headings from the guideline array
  private static String extractHeadings(JsonArray guidelineArray) {
    StringBuilder headings = new StringBuilder();
    for (JsonElement element : guidelineArray) {
      headings.append(element.getAsJsonObject().get("heading").getAsString()).append("\n");
    }
    return headings.toString();
  }

  // Fragments the code and returns a map of method names to code snippets
  private static Map<String, String> fragmentCode() throws IOException {
    Map<String, String> codeFragments = CodegenFragmenter.fragment(
            new File(PathConfiguration.BLOB_CONTAINERS_CLIENT));

    // Remove header as it's not needed for method analysis
    codeFragments.remove("Header");
    return codeFragments;
  }

  // Processes the first prompt and returns the AI response
  private static String processFirstPrompt(OpenAIClient client, String methods, String headings,
                                           StringBuilder reportBuilder) throws IOException {

    String prompt = Files.readString(Path.of(PathConfiguration.METHODS_GUIDELINES_PROMPT));
    prompt = prompt.replace("{methodNames}", methods);
    prompt = prompt.replace("{guidelines}", headings);

    String output = sendFragments(client, prompt);

    // Append to report
    appendSectionHeader(reportBuilder, "Prompt 1: Method and Guideline Analysis");
    reportBuilder.append(prompt);
    appendSectionDivider(reportBuilder);
    reportBuilder.append("\nStep 1 Output:\n");
    reportBuilder.append(output);
    appendSectionFooter(reportBuilder);

    return output;
  }

  // Processes the second prompt and returns the AI response
  private static String processSecondPrompt(OpenAIClient client, Map<String, String> flaggedMethods,
                                            Map<String, String> flaggedGuidelines, StringBuilder reportBuilder) throws IOException {

    String prompt = Files.readString(Path.of(PathConfiguration.MAIN_PROMPT));

    // Build selected code and guidelines
    StringBuilder selectedCode = new StringBuilder();
    StringBuilder selectedGuidelines = new StringBuilder();

    flaggedMethods.values().forEach(code -> selectedCode.append(code).append("\n"));
    flaggedGuidelines
            .forEach((heading, content) -> selectedGuidelines.append(heading).append("\n").append(content).append("\n\n"));

    prompt = prompt.replace("{code}", selectedCode.toString());
    prompt = prompt.replace("{guidelines}", selectedGuidelines.toString());

    String output = sendFragments(client, prompt);

    // Append to report
    appendSectionHeader(reportBuilder, "Prompt 2: Convenience Wrapper Generation");
    reportBuilder.append(prompt);
    appendSectionDivider(reportBuilder);

    return output;
  }

  // Extracts flagged methods from the AI response
  private static Map<String, String> extractFlaggedMethods(String methodGuidelineOutput,
                                                           Map<String, String> codeFragments) {
    Map<String, String> flaggedMethods = new HashMap<>();

    try {
      JsonObject jsonOutput = JsonParser.parseString(methodGuidelineOutput).getAsJsonObject();
      JsonArray methodsArray = jsonOutput.getAsJsonArray("methods");

      for (JsonElement element : methodsArray) {
        String methodName = element.getAsString().trim();
        String code = codeFragments.get(methodName + "("); // Pattern matching key

        if (code != null) {
          flaggedMethods.put(methodName, code);
        }
      }
    } catch (Exception e) {
      logger.error("Warning: Could not parse method recommendations: " + e.getMessage(), e);
    }

    return flaggedMethods;
  }

  // Extracts flagged guidelines from the AI response
  private static Map<String, String> extractFlaggedGuidelines(String methodGuidelineOutput,
                                                              JsonArray guidelineArray) {
    Map<String, String> flaggedGuidelines = new HashMap<>();

    try {
      JsonObject jsonOutput = JsonParser.parseString(methodGuidelineOutput).getAsJsonObject();
      JsonArray guidelineHeaderArray = jsonOutput.getAsJsonArray("guidelines");

      for (JsonElement flaggedElement : guidelineHeaderArray) {
        String flaggedHeader = flaggedElement.getAsString().trim();

        for (JsonElement guideline : guidelineArray) {
          String heading = guideline.getAsJsonObject().get("heading").getAsString().trim();

          if (heading.equals(flaggedHeader)) {
            String content = guideline.getAsJsonObject().get("content").getAsString().trim();
            flaggedGuidelines.put(heading, content);
            break;
          }
        }
      }
    } catch (Exception e) {
      logger.error("Warning: Could not parse guideline recommendations: " + e.getMessage(), e);
    }

    return flaggedGuidelines;
  }

  // Checks if the AI output indicates no improvements found
  private static boolean isNoImprovementsFound(String aiOutput) {
    return aiOutput != null && aiOutput.toLowerCase().trim().equals("no");
  }

  // Appends a section header to the report
  private static void appendSectionHeader(StringBuilder builder, String title) {
    builder.append("\n\n").append("=".repeat(80)).append("\n");
    builder.append(title).append("\n");
    builder.append("=".repeat(80)).append("\n\n");
  }

  // Appends a section divider to the report
  private static void appendSectionDivider(StringBuilder builder) {
    builder.append("\n").append("-".repeat(80)).append("\n");
  }

  // Appends a section footer to the report
  private static void appendSectionFooter(StringBuilder builder) {
    builder.append("\n").append("=".repeat(80)).append("\n");
  }

  // Appends the output of the wrapper generation step to the report
  private static void appendWrapperOutput(StringBuilder reportBuilder, String wrapperOutput) {
    reportBuilder.append("\nStep 2 Output: Generated Convenience Wrapper\n");
    appendSectionDivider(reportBuilder);
    reportBuilder.append(wrapperOutput);
    appendSectionFooter(reportBuilder);
  }

  // Saves the final report to file and returns the report string
  private static String finalizeReport(Path outputPath, StringBuilder reportBuilder, String message)
          throws IOException {
    // Write final report to file
    Files.writeString(outputPath, reportBuilder.toString(),
            StandardOpenOption.CREATE, StandardOpenOption.WRITE);

    logger.info(message);
    return reportBuilder.toString();
  }

  // Creates and returns a SyncToolSpecification instance
  private static McpServerFeatures.SyncToolSpecification getSyncToolSpecification() {
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
                // Ensure required directories exist and print path configuration
                PathConfiguration.ensureDirectoriesExist();

                // Load configuration properties
                loadConfigProperties();

                // Initialize
                OpenAIClient client = createOpenAIClient();
                String result = prepareFragments(client);

                // Return the generated wrapper as tool result
                return new McpSchema.CallToolResult(result, false);

              } catch (ClientAuthenticationException e) {
                String errorMsg = "Authentication failed: " + e.getMessage()
                        + "\nPlease check your API key and endpoint.";
                logger.error(errorMsg, e);
                return new McpSchema.CallToolResult(errorMsg, true);

              } catch (IOException e) {
                String errorMsg = "File reading error: " + e.getMessage();
                logger.error(errorMsg, e);
                e.printStackTrace();
                return new McpSchema.CallToolResult(errorMsg, true);

              } catch (Exception e) {
                String errorMsg = "Error occurred: " + e.getMessage();
                logger.error(errorMsg, e);
                e.printStackTrace();
                return new McpSchema.CallToolResult(errorMsg, true);

              }
            });
  }

  public static void main(String[] args) throws Exception {

    // Check if running in CLI mode
    if (args.length > 0 && args[0].equals("--cli")) {
      System.out.println("Running in CLI mode...");
      runCliMode();
      return;
    }

    // Default MCP Server mode
    System.out.println("Starting MCP Server mode...");
    runMcpServerMode();
  }

  // Runs the application in CLI mode (direct execution)
  private static void runCliMode() {
    try {
      PathConfiguration.ensureDirectoriesExist();
      loadConfigProperties();
      OpenAIClient client = createOpenAIClient();
      prepareFragments(client);
      logger.info("=".repeat(80));
      logger.info("CLI EXECUTION COMPLETED SUCCESSFULLY");
      logger.info("=".repeat(80));

    } catch (ClientAuthenticationException e) {
      logger.error("Authentication failed: " + e.getMessage(), e);
      logger.error("Please check your API key and endpoint." + e.getMessage(), e);
      logger.warn("Please check your API key and endpoint.");
      System.exit(1);
    } catch (IOException e) {
      logger.error("File reading error: " + e.getMessage(), e);
      e.printStackTrace();
      System.exit(1);
    } catch (Exception e) {
      logger.error("Error occurred: " + e.getMessage(), e);
      e.printStackTrace();
      System.exit(1);
    }
  }

  // Runs the application in MCP Server mode
  private static void runMcpServerMode() throws Exception {
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

    logger.info("MCP Server created successfully and listening for requests...");
  }

}

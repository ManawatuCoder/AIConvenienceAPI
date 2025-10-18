
// Main class for generating Java convenience wrappers using Azure OpenAI
import codegenFragmenter.CodegenFragmenter;
import codegenFragmenter.FragmentLinker;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import config.PathConfiguration;

// Imports for Azure OpenAI SDK
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.util.Configuration;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Scanner;
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

  // Logging
  static {
    try {
      PrintStream fileErr = new PrintStream(new FileOutputStream("../Outputs/Logs/system.err.log", true));
      System.setErr(fileErr);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  // Logger
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
        .readString(Path.of(PathConfiguration.SYSTEM_PROMPT));
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
    return prepareFragments(client, null);
  }

  // Overloaded method that accepts optional custom file path
  private static String prepareFragments(OpenAIClient client, String customFilePath) throws Exception {
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
    Map<String, String> codeFragments;
    if (customFilePath != null && !customFilePath.trim().isEmpty()) {
      logger.info("Using custom SDK file: " + customFilePath);
      codeFragments = fragmentCode(customFilePath);
    } else {
      logger.info("Using default SDK file: " + PathConfiguration.getDefaultContainersClient());
      codeFragments = fragmentCode();
    }
    String methods = codeFragments.keySet().toString();

    // Step 1: Get method and guideline recommendations
    logger.info("Step 1: Getting AI recommendations...");
    logger.debug("Sending initial evaluation prompt.");
    String methodGuidelineOutput = processFirstPrompt(client, methods, headings, reportBuilder);

    if (isNoImprovementsFound(methodGuidelineOutput)) {
      return finalizeReport(outputPath, reportBuilder, "No patterns found in code. Stopping operations.");
    }

    // Step 2: Generate convenience wrapper
    logger.info("Step 2: Generating convenience wrapper...");
    JsonArray groupsArray = JsonParser.parseString(methodGuidelineOutput).getAsJsonArray();
    String wrapperOutput = "";
    int methodGroupCounter = 0;

    for (JsonElement groupElement : groupsArray) {
      methodGroupCounter++;// Debug tracker
      logger.debug("Sending iterative wrapping prompt number: {}", methodGroupCounter);
      JsonObject groupObj = groupElement.getAsJsonObject();
      String groupJson = groupObj.toString(); // Turn the individual group back into a JSON string
      // This should be optimised away by feeding the json straight to the extractor
      // methods.
      // They currently exist, as is, as a result of redefinition of usage.

      Map<String, String> flaggedMethods = extractFlaggedMethods(groupJson, codeFragments);
      Map<String, String> flaggedGuidelines = extractFlaggedGuidelines(groupJson, guidelineArray);

      String tempWrapperOutput = processSecondPrompt(client, flaggedMethods, flaggedGuidelines, reportBuilder);
      if (tempWrapperOutput == "no") {
        if (wrapperOutput == "") {
          // Set wrapper output to "no" only if nothing else received thus far.
          wrapperOutput = "no";
        }
      } else {
        // Ensure response does not start with "no".
        if (wrapperOutput == "no") {
          wrapperOutput = "";
        }
        wrapperOutput += tempWrapperOutput;
      }
    }

    if (isNoImprovementsFound(wrapperOutput)) {
      return finalizeReport(outputPath, reportBuilder, "No wrapper improvements found.");
    }

    appendWrapperOutput(reportBuilder, wrapperOutput);

    // Save and return results
    String result = finalizeReport(outputPath, reportBuilder, "Java Wrapper generation completed successfully!");
    saveWrapperOutput(wrapperOutput, customFilePath);
    logger.info("Report saved to: " + outputPath.toString());

    mergeWrapperToExistingFile(wrapperOutput, customFilePath);

    return result;
  }

  // Creates and writes to separate file for only the wrapper output
  private static void saveWrapperOutput(String output) throws IOException {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getWrapperOutputPath(timestamp);
    Path outputPath = Paths.get(filename);

    String wrapperOutputContent = "Java Convenience Wrapper Generated by Azure OpenAI\\nGenerated: " + timestamp
        + "\\n\\n\\n" + output;

    logger.info("Wrapper saved to: " + outputPath.toString());

    Files.writeString(outputPath, wrapperOutputContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
  }

  // Overloaded method that accepts custom file path
  private static void saveWrapperOutput(String output, String customFilePath) throws IOException {
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getWrapperOutputPath(timestamp);
    Path outputPath = Paths.get(filename);

    String sourceFile = (customFilePath != null && !customFilePath.trim().isEmpty())
        ? customFilePath
        : PathConfiguration.getDefaultContainersClient();
    String wrapperOutputContent = "Java Convenience Wrapper Generated by Azure OpenAI\\nGenerated: " + timestamp
        + "\\nSource File: " + sourceFile + "\\n\\n\\n" + output;

    logger.info("Wrapper saved to: " + outputPath.toString());

    Files.writeString(outputPath, wrapperOutputContent, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
  }

  // Adds the wrapper code into a file with the original library ocde
  private static void mergeWrapperToExistingFile(String wrapperOutput) throws IOException {
    // Create new file
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getFinalOutputPath(timestamp);
    Path outputPath = Paths.get(filename);

    Path inputFilePath = Paths.get(PathConfiguration.getDefaultContainersClient());
    String inputFileContent = Files.readString(inputFilePath);

    List<String> lines = Files.readAllLines(inputFilePath, StandardCharsets.UTF_8);
    StringBuilder mergedWrapperOutput = new StringBuilder();

    String[] wrapperLines = wrapperOutput.split("\\R");

    StringBuilder wrapperOutputBuilder = new StringBuilder();
    for (String wrapperLine : wrapperLines) {
      wrapperOutputBuilder.append("    ").append(wrapperLine).append("\n");
    }
    String formattedWrapperOutput = wrapperOutputBuilder.toString();

    // Append generated wrapper code to file
    String commentedWrapperOutput = "\n\n    /********************* GENERATED WRAPPER CODE *********************/\n"
        + formattedWrapperOutput + "\n    /********************* END OF GENERATED CODE *********************/\n";

    CompilationUnit compilationUnit = StaticJavaParser.parse(inputFileContent);
    Optional<ClassOrInterfaceDeclaration> inputClass = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class);

    inputClass.ifPresent(clazz -> {
      int classEndLine = clazz.getEnd().get().line - 1;
      // Insert wrapper output at line above end
      lines.add(classEndLine, commentedWrapperOutput);
    });

    // Rebuild the output with merged content
    for (String line : lines) {
      mergedWrapperOutput.append(line).append("\n");
    }

    Files.writeString(outputPath, mergedWrapperOutput, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    System.out.println("Merged wrapper saved to: " + outputPath);
  }

  // Overloaded method that accepts custom file path
  private static void mergeWrapperToExistingFile(String wrapperOutput, String customFilePath) throws IOException {
    // Create new file
    String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
    String filename = PathConfiguration.getFinalOutputPath(timestamp);
    Path outputPath = Paths.get(filename);

    String sourceFile = (customFilePath != null && !customFilePath.trim().isEmpty())
        ? customFilePath
        : PathConfiguration.getDefaultContainersClient();
    Path inputFilePath = Paths.get(sourceFile);
    String inputFileContent = Files.readString(inputFilePath);

    List<String> lines = Files.readAllLines(inputFilePath, StandardCharsets.UTF_8);
    StringBuilder mergedWrapperOutput = new StringBuilder();

    String[] wrapperLines = wrapperOutput.split("\\R");

    StringBuilder wrapperOutputBuilder = new StringBuilder();
    for (String wrapperLine : wrapperLines) {
      wrapperOutputBuilder.append("    ").append(wrapperLine).append("\n");
    }
    String formattedWrapperOutput = wrapperOutputBuilder.toString();

    // Append generated wrapper code to file
    String commentedWrapperOutput = "\n\n    /********************* GENERATED WRAPPER CODE *********************/\n"
        + formattedWrapperOutput + "\n    /********************* END OF GENERATED CODE *********************/\n";

    CompilationUnit compilationUnit = StaticJavaParser.parse(inputFileContent);
    Optional<ClassOrInterfaceDeclaration> inputClass = compilationUnit.findFirst(ClassOrInterfaceDeclaration.class);

    inputClass.ifPresent(clazz -> {
      int classEndLine = clazz.getEnd().get().line - 1;
      // Insert wrapper output at line above end
      lines.add(classEndLine, commentedWrapperOutput);
    });

    // Rebuild the output with merged content
    for (String line : lines) {
      mergedWrapperOutput.append(line).append("\n");
    }

    Files.writeString(outputPath, mergedWrapperOutput, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    System.out.println("Merged wrapper saved to: " + outputPath);
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
        new File(PathConfiguration.getDefaultContainersClient()));

    // Remove header as it's not needed for method analysis
    codeFragments.remove("Header");
    return codeFragments;
  }

  // Overloaded method that accepts a custom file path
  private static Map<String, String> fragmentCode(String customFilePath) throws IOException {
    Map<String, String> codeFragments = CodegenFragmenter.fragment(
        new File(customFilePath));

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

    Map<String, List<String>> fragmentsMap = FragmentLinker.link(codeFragments);

    try {
      JsonObject jsonOutput = JsonParser.parseString(methodGuidelineOutput).getAsJsonObject();
      JsonArray methodsArray = jsonOutput.getAsJsonArray("methods");

      for (JsonElement element : methodsArray) {
        String methodName = element.getAsString().trim();

        // if fragment key contains requested method, send back values
        for (String key : fragmentsMap.keySet()) {
          if (key.contains(methodName)) {
            List<String> list = fragmentsMap.get(key);

            String output = "";
            // Stores every method related to fragment
            for (String string : list) {
              output += string;
            }

            flaggedMethods.put(key, output);
          }
        }
      }
    } catch (Exception e) {
      logger.error("Warning: Could not parse method recommendations: " + e.getMessage(), e);
    }

    return flaggedMethods;
  }

  // Extracts flagged guidelines from the AI response
  private static Map<String, String> extractFlaggedGuidelines(String methodGuidelineOutput, JsonArray guidelineArray) {
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

  // Helper method to extract file path from MCP arguments
  private static String extractFilePath(Map<String, Object> arguments) {
    if (arguments == null) {
      return null;
    }

    logger.info("Arguments received: " + arguments);

    // First try to get filePath directly
    if (arguments.containsKey("filePath")) {
      Object filePathObj = arguments.get("filePath");
      if (filePathObj != null) {
        String filePath = filePathObj.toString().trim();
        return filePath.isEmpty() ? null : filePath;
      }
    }

    // Fallback: check if first argument value looks like a file path
    if (!arguments.isEmpty()) {
      Object firstValue = arguments.values().iterator().next();
      if (firstValue != null) {
        String potentialPath = firstValue.toString().trim();
        if (potentialPath.endsWith(".java") && new File(potentialPath).exists()) {
          logger.info("Using alternative file path format: " + potentialPath);
          return potentialPath;
        }
      }
    }

    return null;
  }

  // Method to validate file path
  private static McpSchema.CallToolResult validateFilePath(String filePath) {
    File file = new File(filePath);

    if (!file.exists()) {
      return new McpSchema.CallToolResult(
          "Error: File does not exist: " + filePath + "\nPlease provide a valid file path.",
          true);
    }

    if (!file.isFile()) {
      return new McpSchema.CallToolResult(
          "Error: Path is not a file: " + filePath + "\nPlease provide a valid file path.",
          true);
    }

    if (!filePath.toLowerCase().endsWith(".java")) {
      logger.warn("Warning: File does not appear to be a Java file: " + filePath);
    }

    return null; // No validation errors
  }

  // Creates and returns a SyncToolSpecification instance
  private static McpServerFeatures.SyncToolSpecification getSyncToolSpecification() {
    String schema = "{\n" +
        "    \"type\": \"object\",\n" +
        "    \"properties\": {\n" +
        "        \"filePath\": {\n" +
        "            \"type\": \"string\",\n" +
        "            \"description\": \"Optional path to the Azure SDK component file to analyze. If not provided, a default Azure SDK client file will be used.\"\n"
        +
        "        }\n" +
        "    },\n" +
        "    \"required\": []\n" +
        "}";
    return new McpServerFeatures.SyncToolSpecification(
        new McpSchema.Tool("Generate-Convenience-Wrapper",
            "This tool generates a convenience wrapper for Azure SDK libraries. You can optionally specify a custom Azure SDK component file path to analyze. This tool will also display the generated wrapper code within the MCP Client.",
            schema),
        (exchange, arguments) -> {
          // Define the behavior for the tool specification
          try {
            // Ensure required directories exist and print path configuration
            PathConfiguration.ensureDirectoriesExist();

            // Load configuration properties
            loadConfigProperties();

            // Extract and validate file path from arguments
            String customFilePath = extractFilePath(arguments);

            // Validate file path if provided
            if (customFilePath != null) {
              McpSchema.CallToolResult validationError = validateFilePath(customFilePath);
              if (validationError != null) {
                return validationError;
              }
            }

            // Initialize
            OpenAIClient client = createOpenAIClient();

            // Generate the convenience wrapper
            String result = prepareFragments(client, customFilePath);
            String fileInfo = "\nAnalyzed file: " + customFilePath;

            return new McpSchema.CallToolResult(result + fileInfo, false);

          } catch (ClientAuthenticationException e) {
            String errorMsg = "Authentication failed: " + e.getMessage()
                + "\\nPlease check your API key and endpoint.";
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
    Scanner scanner = new Scanner(System.in);
    try {
      PathConfiguration.ensureDirectoriesExist();
      loadConfigProperties();
      OpenAIClient client = createOpenAIClient();

      // Prompt user for SDK file path
      System.out.println("=".repeat(80));
      System.out.println("AZURE SDK COMPONENT FILE SELECTION");
      System.out.println("=".repeat(80));
      System.out.println("Please enter the path to the Azure SDK component file.");
      System.out.println("Default: " + PathConfiguration.getDefaultContainersClient());
      System.out.println("=".repeat(80));
      System.out.println("Press Enter to use the default, or enter a custom file path:");
      System.out.print("> ");

      String userFilePath = scanner.nextLine().trim();

      // Validate file path if provided
      if (!userFilePath.isEmpty()) {
        File userFile = new File(userFilePath);
        if (!userFile.exists()) {
          System.err.println("Error: File does not exist: " + userFilePath);
          System.err.println("Using default file instead.");
          userFilePath = null;
        } else if (!userFile.isFile()) {
          System.err.println("Error: Path is not a file: " + userFilePath);
          System.err.println("Using default file instead.");
          userFilePath = null;
        } else if (!userFilePath.toLowerCase().endsWith(".java")) {
          System.err.println("Warning: File does not appear to be a Java file: " + userFilePath);
          System.out.print("Continue anyway? (y/N): ");
          String confirm = scanner.nextLine().trim().toLowerCase();
          if (!confirm.equals("y") && !confirm.equals("yes")) {
            System.err.println("Using default file instead.");
            userFilePath = null;
          }
        }
      }

      // Use empty string to indicate default should be used
      if (userFilePath != null && userFilePath.isEmpty()) {
        userFilePath = null;
      }

      prepareFragments(client, userFilePath);

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

    } finally {
      scanner.close();
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

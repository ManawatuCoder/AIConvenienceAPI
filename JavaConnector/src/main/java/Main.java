import codegenFragmenter.ChunkLinker;
import codegenFragmenter.CodegenFragmenter;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.exception.ClientAuthenticationException;
import com.azure.core.util.Configuration;
import com.google.gson.*;
import guidelinesFragmentation.GuidelineParser;
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
import java.util.stream.Stream;

public class Main {
  private static final Properties prop = new Properties();
  // Azure OpenAI configuration constants
  private static String AZURE_OPENAI_ENDPOINT;
  private static String AZURE_OPENAI_KEY;
  private static final String DEPLOYMENT_NAME = "gpt-4.1";

  // Loads config properties
  private static void loadConfigProperties() {
    try (InputStream configInput = Files.newInputStream(Paths.get("config.properties"))) {
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
    String endpoint =
        Configuration.getGlobalConfiguration().get("AZURE_OPENAI_ENDPOINT", AZURE_OPENAI_ENDPOINT);
    String apiKey =
        Configuration.getGlobalConfiguration().get("AZURE_OPENAI_KEY", AZURE_OPENAI_KEY);
    return new OpenAIClientBuilder()
        .endpoint(endpoint)
        .credential(new AzureKeyCredential(apiKey))
        .buildClient();
  }

  private static String readFileContent(String filePath) throws IOException {
    Path path = Paths.get(filePath);
    if (!Files.exists(path)) {
      System.err.println("Warning: File not found: " + filePath);
      return "File not found: " + filePath;
    }
    return Files.readString(path);
  }

  private static List<String> readAllSourceFiles(String baseDir) throws IOException {
    List<String> sourceFileContents = new ArrayList<>();
    Path basePath = Paths.get(baseDir);

    if (!Files.exists(basePath)) {
      System.err.println("Warning: Directory not found: " + baseDir);
      return sourceFileContents;
    }

    try (Stream<Path> paths = Files.walk(basePath)) {
      paths
          .filter(Files::isRegularFile)
          .filter(path -> !path.toString().contains(".class")) // Exclude compiled files
          .forEach(
              path -> {
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

  // Sends all content to Azure OpenAI
  private static void analyzeGeneratedCode(
      OpenAIClient client, String inputSpecs, String typeSpecContent, List<String> srcFiles) {

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
      if (filesIncluded >= maxFiles) break;

      // Small sample size as token limits are strict
      if (srcFile.contains("Client.java")
          || srcFile.contains("Builder.java")
          || srcFile.contains("BlobContainer.java")
          || srcFile.contains("BlobServiceProperties.java")
          || srcFile.contains("ContainerProperties.java")) {
        contentBuilder.append(srcFile);
        filesIncluded++;
      }
    }

    List<ChatRequestMessage> messages = new ArrayList<>();
    // Send all content including InputSpecs, TypeSpec, and generated code
    messages.add(new ChatRequestUserMessage(contentBuilder.toString()));

    // Chat settings for the AI model
    ChatCompletionsOptions options =
        new ChatCompletionsOptions(messages)
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

    } catch (Exception e) {
      System.err.println("Error during convenience wrapper generation: " + e.getMessage());
      e.printStackTrace();

      // Save error report to file
      saveErrorReportToFile(e, contentBuilder.toString(), inputSpecs);
    }
  }

  // Saves the AI response to a timestamped file
  private static void saveResponseToFile(String response, CompletionsUsage usage) {
    try {
      // Create a timestamped filename
      String timestamp =
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
      String filename = String.format("java_convenience_wrapper_%s.txt", timestamp);
      Path outputPath = Paths.get(filename);

      // Create the complete report content
      StringBuilder reportBuilder = new StringBuilder();
      reportBuilder.append("Java Convenience Wrapper Generated by Azure OpenAI\n");
      reportBuilder
          .append("Generated: ")
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
      Files.writeString(
          outputPath,
          reportBuilder.toString(),
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE);

      System.out.println("\n=== CONVENIENCE WRAPPER GENERATED ===");
      System.out.println("Java convenience wrapper saved to: " + outputPath.toAbsolutePath());

    } catch (IOException e) {
      System.err.println("Error saving AI response to file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  // Saves an error report to a timestamped file
  private static void saveErrorReportToFile(
      Exception error, String contentToAnalyze, String systemPrompt) {
    try {
      // Create a timestamped filename
      String timestamp =
          LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
      String filename = String.format("convenience_wrapper_error_%s.txt", timestamp);
      Path outputPath = Paths.get(filename);

      // Create the complete error report content
      StringBuilder reportBuilder = new StringBuilder();
      reportBuilder.append("Java Convenience Wrapper Generation Error Report\n");
      reportBuilder
          .append("Generated: ")
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
      Files.writeString(
          outputPath,
          reportBuilder.toString(),
          StandardOpenOption.CREATE,
          StandardOpenOption.WRITE);
      System.out.println("\n=== ERROR REPORT SAVED ===");
      System.out.println("Error report saved to: " + outputPath.toAbsolutePath());

    } catch (IOException e) {
      System.err.println("Error saving error report to file: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private static String sendChunks(OpenAIClient client, String prompt) {

    List<ChatRequestMessage> messages = new ArrayList<>();
    // Send all content including InputSpecs, TypeSpec, and generated code
    messages.add(new ChatRequestUserMessage(prompt));

    // Chat settings for the AI model
    ChatCompletionsOptions options =
        new ChatCompletionsOptions(messages)
            .setMaxTokens(4000) // Increase max tokens for better output
            .setTemperature(0.3) // Lower temperature for more analytical response
            .setTopP(0.95);

    try {
      ChatCompletions chatCompletions = client.getChatCompletions(DEPLOYMENT_NAME, options);
      ChatChoice choice = chatCompletions.getChoices().get(0);
      String aiResponse = choice.getMessage().getContent();
      return aiResponse;

    } catch (Exception e) {
      System.err.println("Error during convenience wrapper generation: " + e.getMessage());
      e.printStackTrace();
    }
    return null;
  }

  private static void prepareFragments(OpenAIClient client) throws Exception {
      CodegenFragmenter fragmenter = new CodegenFragmenter();
      ChunkLinker linker = new ChunkLinker();
      List<List<String>> linked = List.of();
      GuidelineParser parser = new GuidelineParser();

      String guidelineString =
              Files.readString(
                      Path.of(parser.parse("https://azure.github.io/azure-sdk/java_introduction.html")));
      JsonArray guidelineArray = JsonParser.parseString(guidelineString).getAsJsonArray();
      String headings = "";
      String codeHeader = "";
      String output = "";
      String methods = "";

      for (JsonElement element : guidelineArray) {
          headings += element.getAsJsonObject().get("heading").getAsString() + "\n";
      }
      //        System.out.println(headings);
      Map<String, String> newMap = null;
      try {
          newMap = fragmenter.fragment(
                  new File(
                          "..\\TypeSpec_Conversion\\tsp-output\\clients\\java\\src\\main\\java\\azurestoragemanagement\\BlobContainer.java"));

          codeHeader = newMap.get("Header");
          newMap.remove("Header");
          methods = newMap.keySet().toString();

          //            System.out.println(codeHeader);

          linked = linker.link(newMap);

      } catch (IOException e) {
          e.printStackTrace();
      }

      String prompt = "";

      // ########################################
      // First Prompt (Method Names & Guidelines)
      // ########################################
      prompt = Files.readString(Path.of("../Prompts/MethodsGuidelinesPrompt.txt"));
      prompt = prompt.replace("{methodNames}", methods);
      prompt = prompt.replace("{guidelines}", headings);

      // Call the AI, returns
      String outputMethodsGuidelines = sendChunks(client, prompt);

      System.out.println("###################################");
      System.out.println(prompt);
      System.out.println("###################################\n\n");


      if (output.toLowerCase().equals("no")) {
        // No patterns found in method names
        System.out.println("No patterns found in code");
        // TODO: We may want to add some state/fallback for when no patterns are found
        // return;
      }

      // Convert to JSON
      JsonObject JSONMethodsGuidelines = JsonParser.parseString(outputMethodsGuidelines).getAsJsonObject();

      JsonArray methodsArray = JSONMethodsGuidelines.getAsJsonArray("methods");
      JsonArray guidelineHeaderArray = JSONMethodsGuidelines.getAsJsonArray("guidelines");


      //String formattedMethodNames = outputMethodNames.replace("[", "").replace("]", "").replace("\"", "");
      //String[] methodNames = formattedMethodNames.split(",\\s*"); // Strips comma and trailing space


      Map<String, String> flaggedMethods = new HashMap<>();
      Map<String, String> flaggedGuidelines = new HashMap<>();

      // Attach code to each flagged method
      for (JsonElement e : methodsArray) {
          assert newMap != null;
          String methodName = e.getAsString().trim();
          String code = newMap.get(methodName + "(");

          // If method name exists in map
          if (code != null) {
            // Attach code to method name entry
            flaggedMethods.put(methodName, newMap.get(methodName + "(")); // The '(' is on all map entry names for pattern matching
          }
      }

      // Attach guideline to guideline header
      for (JsonElement flaggedGuideline : guidelineHeaderArray) {
          String flaggedGuidelineHeader = flaggedGuideline.getAsString().trim();

          for (JsonElement guideline : guidelineArray) {
              String heading = guideline.getAsJsonObject().get("heading").getAsString().trim();

              if (heading.equals(flaggedGuidelineHeader)) {
                  String guidelineContent = guideline.getAsJsonObject().get("content").getAsString().trim();
                  flaggedGuidelines.put(heading, guidelineContent);
                  break;
              }
          }
      }

      // Prints out the flagged code
//      for (Map.Entry<String, String> flaggedMethod : flaggedMethods.entrySet()) {
//        System.out.println("Method Name: " + flaggedMethod.getKey());
//        System.out.println("Method Code: " + flaggedMethod.getValue());
//        System.out.println("\n\n");
//      }
      // Prints out flagged guidelines
//      for (Map.Entry<String, String> flaggedGuideline : flaggedGuidelines.entrySet()) {
//          System.out.println("Guideline Name: " + flaggedGuideline.getKey());
//          System.out.println("Guidelines Code: " + flaggedGuideline.getValue());
//          System.out.println("\n\n");
//      }

      // ########################################
      // Second (Main) Prompt (Generates wrapper from flagged code and guidelines)
      // ########################################
      String selectedCode = "";
      String selectedGuidelines = "";
      prompt = Files.readString(Path.of("../Prompts/MainPrompt.txt"));

      for (Map.Entry<String, String> method : flaggedMethods.entrySet()) {
          selectedCode += method.getValue() + "\n";
      }
      for (Map.Entry<String, String> guideline : flaggedGuidelines.entrySet()) {
          selectedGuidelines += guideline.getKey() + "\n" + guideline.getValue() + "\n\n";
      }

      prompt = prompt.replace("{code}", selectedCode);
      prompt = prompt.replace("{guidelines}", selectedGuidelines);

      String outputWrapper = sendChunks(client, prompt);

      System.out.println("GENERATED WRAPPER");
      System.out.println("##########################");
      System.out.println(outputWrapper);
      System.out.println("##########################");

      // Guideliens Prompt
//      for (List<String> list : linked) {
//          prompt = Files.readString(Path.of("../PlainText/GuidelineRequest.txt")).toString();
//
//          String code = "";
//
//          for (String entry : list) {
//              code += entry;
//          }
//
//          code = codeHeader + code;
//
//          prompt = prompt.replace("{code}", code);
//          // TODO: Consider changing this to only include method names, rather than entire methods.
//          prompt = prompt.replace("{guidelines}", headings);
//          prompt = prompt.replace("{existingMethods}", methods);
//
//          //            System.out.println(prompt + "\n\nEnd\n\n\n");
//
//          //            String guidelineResponse = "Model Types;Java API Best Practices;Naming
//          // Patterns";
//          String guidelineResponse = sendChunks(client, prompt);
//          String guidelinesRequested = "";
//
//          for (JsonElement guideline : guidelineArray) {
//              String heading = guideline.getAsJsonObject().get("heading").getAsString();
//
//              if (guidelineResponse.contains(heading)) {
//                  guidelinesRequested +=
//                          heading + "\n" + guideline.getAsJsonObject().get("content").getAsString() + "\n\n";
//              }
//          }
//
//          //            System.out.println(guidelinesRequested);
//
//          prompt = Files.readString(Path.of("../PlainText/WrapperRequest.txt")).toString();
//
//          prompt = prompt.replace("{code}", code);
//          prompt = prompt.replace("{guidelines}", guidelinesRequested);
//
//          output = sendChunks(client, prompt);
//
//          // Todo: Save response to output file, to prepare for Spotless parsing.
//
//          //            System.out.println("Chunk List: ");
//          //            for(String entry : list){
//          //            System.out.println(entry);
//          //            }
////      System.out.println(output + "\n\n\n Wrapper code: \n\n\n");
//
//          // TODO: Remove this - just here for demonstrative purposes
//          JsonObject responseArray = JsonParser.parseString(output).getAsJsonObject();
//          String stuff = "";
//
//          stuff += responseArray.get("wrapperCode").getAsString() + "\n";
//
//          System.out.println(stuff);
//      }
      // System.out.println(output);
  }

  public static void main(String[] args) throws Exception {
    try {
      loadConfigProperties();
      OpenAIClient client = createOpenAIClient();
      prepareFragments(client);

      //      // Initialize
      //
      //      // Read all required files
      //      System.out.println("Reading files...");
      //      String inputSpecs = readFileContent("../PlainText/InputSpecs.txt");
      //      String typeSpecContent = readFileContent("../TypeSpec_Conversion/blob-storage.tsp");
      //      List<String> srcFiles =
      //          readAllSourceFiles("../TypeSpec_Conversion/tsp-output/clients/java/src");
      //
      //      // Send content to AI for analysis
      //      analyzeGeneratedCode(client, inputSpecs, typeSpecContent, srcFiles);
      //
    } catch (ClientAuthenticationException e) {
      System.err.println("Authentication failed: " + e.getMessage());
      System.err.println("Please check your API key and endpoint.");
    } catch (IOException e) {
      System.err.println("File reading error: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Error occurred: " + e.getMessage());
      e.printStackTrace();
    }
  }
}

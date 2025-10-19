package config;

import java.io.File;
import java.nio.file.Paths;

public class PathConfiguration {

    static String slash = File.separator;

    // *** UPDATE THIS PATH FOR YOUR LOCAL ENVIRONMENT ***
    private static final String BASE_PROJECT_PATH = Paths.get(System.getProperty("user.dir")).toString();

    public String getBasePath(){
        return BASE_PROJECT_PATH;
    }
    // TypeSpec (codegen) generated files - default path
    public static final String DEFAULT_CONTAINERS_CLIENT = BASE_PROJECT_PATH +
            "" + slash + "azure-sdks" + slash + "ai-src" + slash + "DatasetsClient.java";

    // DO NOT MODIFY THESE
    private static final String JAVA_CONNECTOR_BASE = BASE_PROJECT_PATH + "" + slash + "JavaConnector";
    private static final String PROMPTS_BASE = BASE_PROJECT_PATH + "" + slash + "Prompts";
    private static final String LOGS_OUTPUTS_BASE = BASE_PROJECT_PATH + "" + slash + "Outputs" + slash + "Logs";
    private static final String WRAPPER_OUTPUTS_BASE = BASE_PROJECT_PATH + "" + slash + "Outputs" + slash + "RawWrapperOutputs";
    private static final String MERGED_OUTPUT_BASE = BASE_PROJECT_PATH + "" + slash + "Outputs" + slash + "MergedOutputs";
    private static final String GUIDELINES_OUTPUT_BASE = JAVA_CONNECTOR_BASE +
            "" + slash + "src" + slash + "main" + slash + "java" + slash + "guidelinesFragmentation" + slash + "output";

    // Configuration files
    public static final String CONFIG_PROPERTIES = JAVA_CONNECTOR_BASE + "" + slash + "config.properties";

    // Prompt files
    public static final String SYSTEM_PROMPT = PROMPTS_BASE + "" + slash + "SystemPrompt.txt";
    public static final String FIRST_PROMPT = PROMPTS_BASE + "" + slash + "FirstPrompt.txt"; // Method/guideline request prompt
    public static final String SECOND_PROMPT = PROMPTS_BASE + "" + slash + "SecondPrompt.txt"; // Wrapper generation prompt

    // Output files
    public static final String MERGED_OUTPUT_TEMPLATE = MERGED_OUTPUT_BASE + "" + slash + "java_wrapper_%s.java";
    public static final String WRAPPER_OUTPUT_TEMPLATE = WRAPPER_OUTPUTS_BASE + "" + slash + "raw_wrapper_output_%s.txt";
    public static final String LOG_OUTPUT_TEMPLATE = LOGS_OUTPUTS_BASE + "" + slash + "wrapper_logs_%s.txt";

    // Guidelines fragmentation cache files
    public static final String GUIDELINES_JSON = GUIDELINES_OUTPUT_BASE + "" + slash + "guidelinesJson.txt";
    public static final String LAST_MODIFIED_FILE = GUIDELINES_OUTPUT_BASE + "" + slash + "last-modified.txt";

    public static String getFinalOutputPath(String timestamp) {
        return String.format(MERGED_OUTPUT_TEMPLATE, timestamp);
    }

    public static String getWrapperOutputPath(String timestamp) {
        return String.format(WRAPPER_OUTPUT_TEMPLATE, timestamp);
    }

    public static String getLogsOutputPath(String timestamp) {
        return String.format(LOG_OUTPUT_TEMPLATE, timestamp);
    }

    public static String getBaseProjectPath() {
        return BASE_PROJECT_PATH;
    }

    public static String getDefaultContainersClient() {
        return DEFAULT_CONTAINERS_CLIENT;
    }

    public static void ensureDirectoriesExist() {
        java.nio.file.Path[] requiredDirs = {
                java.nio.file.Paths.get(LOGS_OUTPUTS_BASE),
                java.nio.file.Paths.get(GUIDELINES_OUTPUT_BASE),
                java.nio.file.Paths.get(PROMPTS_BASE)
        };

        for (java.nio.file.Path dir : requiredDirs) {
            try {
                if (!java.nio.file.Files.exists(dir)) {
                    java.nio.file.Files.createDirectories(dir);
                    System.out.println("Created directory: " + dir);
                }
            } catch (Exception e) {
                System.err.println("Failed to create directory: " + dir);
                throw new RuntimeException("Failed to create required directory: " + dir, e);
            }
        }
        System.out.println("All required directories are available");
    }
}
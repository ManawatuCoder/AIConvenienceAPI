package config;

public class PathConfiguration {

    // *** UPDATE THIS PATH FOR YOUR LOCAL ENVIRONMENT ***
    private static final String BASE_PROJECT_PATH = "C:\\Users\\smith\\IdeaProjects\\AIConvenienceAPI";

    // DO NOT MODIFY THESE
    private static final String JAVA_CONNECTOR_BASE = BASE_PROJECT_PATH + "\\JavaConnector";
    private static final String PROMPTS_BASE = BASE_PROJECT_PATH + "\\Prompts";
    private static final String LOGS_OUTPUTS_BASE = BASE_PROJECT_PATH + "\\Logs";
    private static final String WRAPPER_OUTPUTS_BASE = BASE_PROJECT_PATH + "\\WrapperOutputs";
    private static final String TYPESPEC_BASE = BASE_PROJECT_PATH + "\\TypeSpec_Conversion";
    private static final String GUIDELINES_OUTPUT_BASE = JAVA_CONNECTOR_BASE +
            "\\src\\main\\java\\guidelinesFragmentation\\output";

    // Configuration files
    public static final String CONFIG_PROPERTIES = JAVA_CONNECTOR_BASE + "\\config.properties";

    // Prompt files
    public static final String METHODS_GUIDELINES_PROMPT = PROMPTS_BASE + "\\MethodsGuidelinesPrompt.txt";
    public static final String MAIN_PROMPT = PROMPTS_BASE + "\\MainPrompt.txt";

    // Output files
    public static final String WRAPPER_OUTPUT_TEMPLATE = WRAPPER_OUTPUTS_BASE + "\\java_wrapper_%s.txt";
    public static final String LOG_OUTPUT_TEMPLATE = LOGS_OUTPUTS_BASE + "\\wrapper_logs_%s.txt";

    // TypeSpec generated files
    public static final String BLOB_CONTAINERS_CLIENT = TYPESPEC_BASE +
            "\\tsp-output\\clients\\java\\src\\main\\java\\azurestoragemanagement\\BlobContainersClient.java";

    // Guidelines fragmentation cache files
    public static final String GUIDELINES_JSON = GUIDELINES_OUTPUT_BASE + "\\guidelinesJson.txt";
    public static final String LAST_MODIFIED_FILE = GUIDELINES_OUTPUT_BASE + "\\last-modified.txt";

    public static String getWrapperOutputPath(String timestamp) {
        return String.format(WRAPPER_OUTPUT_TEMPLATE, timestamp);
    }
    public static String getLogsOutputPath(String timestamp) {
        return String.format(LOG_OUTPUT_TEMPLATE, timestamp);
    }

    public static String getBaseProjectPath() {
        return BASE_PROJECT_PATH;
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
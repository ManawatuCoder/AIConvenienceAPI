# Azure SDK Convenience Wrapper Generator

This project provides an AI-powered tool that generates convenience wrapper methods for Azure SDK Java client libraries. It analyzes existing SDK methods and creates higher-level convenience methods that improve developer experience

## Setup Guide

### 1. Clone this repository

```bash
git clone https://github.com/ManawatuCoder/AIConvenienceAPI.git
```

### 2. Create config file

Inside the 'JavaConnector' directory, make a copy of the example `config.properties.example` file, then paste in your API key
On Windows:

```bash
cd JavaConnector
copy config.properties.example config.properties
```

On Linux:

```bash
cd JavaConnector
cp config.properties.example config.properties
```

**Note:** You must have access to an Azure OpenAI API key to paste into the `AZURE_OPENAI_KEY` property

### 3. Build the Project

```bash
# From inside the JavaConnector directory
mvn clean package
```

### 4. Run in CLI Mode

```bash
# Note that you must update the path in this command:
java -jar /path/to/your/AIConvenienceAPI/JavaConnector/target/MCP-Server-1.0.0.jar --cli
```

**Important:** Replace `/path/to/your` with your actual local project path

### 5. (Optional) Run in MCP Server Mode

1. Build the project:

```bash
# From inside the JavaConnector directory
mvn clean package
```

2. Note down the path to the generated JAR file (e.g., `JavaConnector/target/MCP-Server-1.0.0.jar`).
3. Install Github Co-Pilot within VS Code.
4. Connect Github Co-Pilot to the MCP server by adding a new server configuration.
5. Configure the server in `.vscode/mcp.json`:

```json
{
  "servers": {
    "MCP-CodeGen": {
      "type": "stdio",
      "command": "java",
      "args": [
        "-jar",
        "/path/to/your/AIConvenienceAPI/JavaConnector/target/MCP-Server-1.0.0.jar"
      ]
    }
  }
}
```

6. Ensure you update the JAR file path to your local build location.
7. Ensure to start the MCP server in VS Code inside the mcp.json configuration.
8. Use the `Generate-Convenience-Wrapper filepath/to/your/AzureSDKJavaFile.java` tool in Github Co-Pilot to analyze Azure SDK Java files.

## Prerequisites

- **Java 8+**
- **Maven 3.6+**
- **Node.js** (for MCP Inspector)
- **Azure OpenAI** access with API key
- **VS Code** (for MCP integration)

## Project Structure

```
AIConvenienceAPI/
├── azure-sdks/              # Azure SDK source files for analysis
│   ├── ai-src/              # AI Project SDK files
│   └── blob-storage-src/    # Blob Storage SDK files
├── JavaConnector/           # Main application
│   ├── src/main/java/       # Java source code
│   ├── config.properties    # Azure OpenAI configuration
│   └── target/              # Built JAR files
├── Outputs/                 # Generated wrapper outputs
│   ├── Logs/                # Application logs
│   ├── MergedOutputs/       # Final java wrapper file (merged with input file)
│   └── RawWrapperOutputs/   # The raw AI output
├── Prompts/                 # AI prompt templates
└── .vscode/                 # VS Code MCP configuration
    └── mcp.json             # MCP server configuration
```

## Technical Architecture

### **Application Entry Point**

- `main(String[] args)` - Application entry point that initializes the MCP server with STDIO transport or CLI mode

### **Configuration & Setup**

- `loadConfigProperties()` - Loads Azure OpenAI credentials from config.properties file
- `createOpenAIClient()` - Creates and configures the Azure OpenAI client with authentication
- `PathConfiguration` - Manages file paths and directory structure

### **MCP Server Framework**

- `getSyncToolSpecification()` - Defines the MCP tool specification for the convenience wrapper generator
- Creates tool schema and behavior for "Generate-Convenience-Wrapper"
- Handles STDIO communication protocol for VS Code integration

### **AI-Powered Analysis Engine**

- `prepareFragments(OpenAIClient client)` - Controls the complete process from guideline parsing to wrapper generation
- Two-phase AI analysis:
  1. **Method Pattern Analysis** - Identifies potential convenience improvements
  2. **Wrapper Generation** - Creates actual convenience wrapper methods

### **Code Analysis & Processing**

- `CodegenFragmenter` - Fragments Java source code into method-specific chunks for analysis
- `extractFlaggedMethods(String, Map)` - Parses AI recommendations to extract relevant methods
- `extractFlaggedGuidelines(String, JsonArray)` - Parses AI recommendations to extract relevant guidelines
- Method signature parsing and return type analysis

### **Azure SDK Guidelines Integration**

- `GuidelineParser` - Fetches and parses Azure SDK design guidelines from official documentation
- `parseGuidelines()` - Retrieves current Azure SDK Java design principles
- `extractHeadings(JsonArray)` - Extracts guideline headings for AI context
- Ensures generated wrappers follow Azure SDK best practices

### **AI Interaction & Communication**

- `sendFragments(OpenAIClient, String)` - Handles communication with Azure OpenAI API
- Configures chat completion options (temperature, max tokens, top-p)
- `processFirstPrompt()` - Method/guideline analysis phase
- `processSecondPrompt()` - Convenience wrapper generation phase
- Structured prompt engineering for consistent results

### **Output Management & Reporting**

- `createOutputFile()` - Generates timestamped output file paths for wrapper results
- `createReportHeader()` - Creates detailed report headers with generation metadata
- `finalizeReport(Path, StringBuilder, String)` - Saves comprehensive reports to file
- Multi-format output support (Java code, documentation, analysis reports)

### **Validation & Quality Control**

- `isNoImprovementsFound(String)` - Handles cases when no wrapper improvements are identified
- Method signature validation and compatibility checking
- Azure SDK design pattern compliance verification

### **Report Formatting & Documentation**

- `appendSectionHeader(StringBuilder, String)` - Adds formatted section headers (80-char width)
- `appendSectionDivider(StringBuilder)` - Adds horizontal dividers for report sections
- `appendSectionFooter(StringBuilder)` - Adds formatted section footers
- `appendWrapperOutput(StringBuilder, String)` - Formats and appends final wrapper output with explanations

## Output Examples

### Input: ConnectionsClient.java

Methods analyzed: `getConnection()`, `getConnectionWithCredentials()`, `listConnections()`, `getDefaultConnection()`

### Generated Convenience Wrappers:

```java
/**
 * Gets the default connection of the specified type, including credentials.
 * Eliminates the need to list all connections and filter manually.
 */
public Connection getDefaultConnectionWithCredentials(ConnectionType connectionType)

/**
 * Lists connections with optional credential inclusion.
 * Simplifies the common scenario of needing credentials with connection data.
 */
public PagedIterable<Connection> listConnections(boolean includeCredentials)

/**
 * Intelligently retrieves connection credentials only when supported.
 * Reduces error-prone manual type checking logic.
 */
public Connection getConnectionIfCredentialsSupported(String name)
```
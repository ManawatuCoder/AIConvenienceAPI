# How to Run the Application

#### Option A: CLI Mode (Direct Execution)
```bash
# Run directly as a command-line application
java -jar JavaConnector/target/MCP-Server-1.0.0.jar --cli
```

#### Option B: MCP Server Mode (with Inspector)
```bash
# Install MCP Inspector (one-time setup)
npm install -g @modelcontextprotocol/inspector

# Launch MCP Inspector
npx @modelcontextprotocol/inspector

# In the inspector, use this path:
java -jar /path/to/your/JavaConnector/target/MCP-Server-1.0.0.jar
```

**Important:** Replace `/path/to/your/` with your actual local project path.

## MCP Inpector Settings

- **Transport Type**: STDIO
- **Command**: java
- **Arguments**: Paste the above jar file location with your updated dir
- **Request Timeout & Maximum Request Timeout**: This might need adjusting according to your machine

## Usage Modes

- **CLI Mode (`--cli`)**: Runs once and generates wrapper immediately, then exits
- **MCP Server Mode** (default): Starts persistent server waiting for MCP client requests
- **Maven 3.6+** 
- **Node.js** (for MCP Inspector)
- **Azure OpenAI** access with API key

## Setup Steps

### 1. Configure Paths
Update the base path in `JavaConnector/src/main/java/config/PathConfiguration.java`:
```java
public static final String BASE_PROJECT_PATH = "D:/UpdatedMCP/AIConvenienceAPI";
```
Change this to your local project directory path.

### 2. Configure Azure OpenAI
Create/update `JavaConnector/config.properties`:
```properties
azure.openai.endpoint=https://your-resource.openai.azure.com/
azure.openai.key=your-api-key-here
azure.openai.deployment.name=your-gpt4-deployment-name
```

### 3. Build the Project
```bash
cd JavaConnector
mvn clean package
```

### 4. Run with MCP Inspector
```bash
# Install MCP Inspector (one-time setup)
npm install -g @modelcontextprotocol/inspector

# Launch MCP Inspector
npx @modelcontextprotocol/inspector

# In the inspector, use this path:
java -jar /path/to/your/JavaConnector/target/MCP-Server-1.0.0.jar
```
**Important:** Replace `/path/to/your/` with your actual local project path.

## Architecture

### **Application Entry Point**
- `main(String[] args)` - Application entry point that initializes the MCP server with STDIO transport

### **Configuration & Setup** 
- `loadConfigProperties()` - Loads Azure OpenAI credentials from config.properties file
- `createOpenAIClient()` - Creates and configures the Azure OpenAI client with authentication

### **MCP Server Framework**
- `getSyncToolSpecification()` - Defines the MCP tool specification for the convenience wrapper generator
- Creates tool schema and behavior for "Blob-Storage-Generate-Convenience-Wrapper"

### **Main Orchestrator**
- `prepareFragments(OpenAIClient client)`- Controls the complete process from guideline parsing to wrapper generation

### **File & Report Management**
- `createOutputFile()` - Generates timestamped output file path for wrapper results
- `createReportHeader()` - Creates report header with generation timestamp
- `finalizeReport(Path, StringBuilder, String)` - Saves final report to file and returns results

### **Data Processing**
- `parseGuidelines(GuidelineParser)` - Fetches and parses Azure SDK guidelines from web
- `extractHeadings(JsonArray)` - Extracts guideline headings from parsed JSON data
- `fragmentCode()` - Fragments Java source code into method-specific chunks using CodegenFragmenter
- `extractFlaggedMethods(String, Map)` - Parses AI recommendations to extract relevant methods
- `extractFlaggedGuidelines(String, JsonArray)` - Parses AI recommendations to extract relevant guidelines

### **AI Interaction & Communication**
- `sendFragments(OpenAIClient, String)` - Handles communication with Azure OpenAI API
- Configures chat completion options (temperature, max tokens, top-p)
- `processFirstPrompt(OpenAIClient, String, String, StringBuilder)` - Method/guideline analysis
- `processSecondPrompt(OpenAIClient, Map, Map, StringBuilder)` - Convenience wrapper generation

### **Logic & Validation**
- `isNoImprovementsFound(String)` - Handles when no wrapper improvements are identified

### **Report Formatting Utilities**
- `appendSectionHeader(StringBuilder, String)` - Adds formatted section headers (80-char width)
- `appendSectionDivider(StringBuilder)` - Adds horizontal dividers for report sections  
- `appendSectionFooter(StringBuilder)` - Adds formatted section footers
- `appendWrapperOutput(StringBuilder, String)` - Formats and appends final wrapper output


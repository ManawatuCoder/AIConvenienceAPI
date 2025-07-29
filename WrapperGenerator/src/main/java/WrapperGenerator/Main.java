package WrapperGenerator;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    static PromptJSON currentPrompt = new PromptJSON();
    static Properties prop = new Properties();
    static String apiKey;
    static String baseInstructions;
    static String JSObj;
    static String projSpecs;
    static String codegenOutput;
    static SimplePanel display;

    static String model = "gpt-4.1";//4.1 default model
    static String models[] = {"gpt-4.1",
            "gpt-4.1-mini",
            "model-router",
            "o4-mini"};

    public static void main(String[] args) throws IOException, InterruptedException {

        JFrame frame = new JFrame("My App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        display = new SimplePanel(); // your custom JPanel
        frame.getContentPane().add(display);

        frame.setVisible(true);

        readInput();

        currentPrompt.addMessage(1,baseInstructions);
        currentPrompt.addMessage(0,projSpecs);
        currentPrompt.addMessage(0,codegenOutput);

        display.getSubmitButton().addActionListener(e -> {
            model = models[display.modelChoice];
            System.out.println(model);
            JSObj = jsonify();
            try {
                sendAndParse();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    static void sendAndParse() throws IOException, InterruptedException {
        String response = postMessages();
        System.out.println(response);

//        Parse response to extract message.
        JSONObject responseJSON = new JSONObject(response);
        JSONArray choices = responseJSON.getJSONArray("choices");
        String message = choices.getJSONObject(0).getJSONObject("message").getString("content");
        System.out.println(message);
        display.setTextField(message);
    }

    static String postMessages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://azsdk-openai.openai.azure.com/openai/deployments/" + model + "/chat/completions?api-version=2024-12-01-preview"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(JSObj))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println("Status: " + response.statusCode());

        return response.body();
    }


    static void readInput(){
        //Read config properties for API key
        try { InputStream input = Files.newInputStream(Paths.get("config.properties"));
            if (input == null) {
                //TODO decide what to do in this case
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            // Load the properties from the input stream
            prop.load(input);
            apiKey = prop.getProperty("api_key");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Read base instruction file for first message
        try { InputStream input = Files.newInputStream(Paths.get("src/main/resources/BaseInstructions.txt"));
            if (input == null) {
                //TODO decide what to do in this case
                System.out.println("Sorry, unable to find base instruction file");
                return;
            }
            baseInstructions = new String(input.readAllBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Read project specifications file
        try { InputStream input = Files.newInputStream(Paths.get("src/main/resources/ProjectSpecs.txt"));
            if (input == null) {
                //TODO decide what to do in this case
                System.out.println("Sorry, unable to find codegen output file");
                return;
            }
            projSpecs = new String(input.readAllBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        //Read codegen output file for first message
        try { InputStream input = Files.newInputStream(Paths.get("src/main/resources/CodegenOutput.java"));
            if (input == null) {
                //TODO decide what to do in this case
                System.out.println("Sorry, unable to find codegen output file");
                return;
            }
            codegenOutput = new String(input.readAllBytes());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    static String jsonify(){
        String model = "{\n";
        String messages = "\"messages\": [\n";
        for(int i = 0; i < currentPrompt.messageCount(); i++) {
            messages += "{\"role\": \"" + currentPrompt.messages.get(i).role + "\", ";
            messages += "\"content\": \"" + currentPrompt.messages.get(i).content + "\"}";
            if (i < currentPrompt.messageCount() - 1){
                messages += ",\n";
            }else{
                messages += "\n";
            }
        }
        messages += "]\n}\n";
        String JSObj = model + messages;
        return JSObj;
    }
}
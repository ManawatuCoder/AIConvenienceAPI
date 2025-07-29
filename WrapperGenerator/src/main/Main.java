package main;

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
    static PromptJSON test = new PromptJSON(); //TODO Change name
    static String apiKey;
    static String JSObj = "";

    static String model = "gpt-4.1";//4.1 default model
    static String models[] = {"gpt-4.1",
            "gpt-4.1-mini",
            "model-router",
            "o4-mini"};

    public static void main(String[] args) throws IOException, InterruptedException {
        Properties prop = new Properties();

        //Read config.properties to access API key.
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




        test.addMessage(1,"respond with aaaaa if yes, bbbb if no");
        test.addMessage(0,"Is this working?");

        JSObj = jsonify();
        System.out.println(JSObj);

        System.out.println(postMessages());
        //choices.content_filter_results.message.content
        //"choices":[{"content_filter_results":
        //"message":{"annotations":[],"content":"aaaaa","refusal":null,"role":"assistant"}
    }

    static String postMessages() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://azsdk-openai.openai.azure.com/openai/deployments/" + model + "/chat/completions?api-version=2024-10-21"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(JSObj))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

//        System.out.println("Status: " + response.statusCode());

        return response.body();
    }


    public static String jsonify(){
        String model = "{\n";
        String messages = "\"messages\": [\n";
        for(int i = 0; i < test.messageCount(); i++) {
            messages += "{\"role\": \"" + test.messages.get(i).role + "\", ";
            messages += "\"content\": \"" + test.messages.get(i).content + "\"}";
            if (i < test.messageCount() - 1){
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
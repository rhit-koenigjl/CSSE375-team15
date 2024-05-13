package arcadeGame.gameHelpers.transitions;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class AiMessageGenerator implements MessageGenerator {
    private static final AiMessageEndpoint DEFAULT_ENDPOINT = GeminiMessageEndpoint.getInstance();
    private static final AiMessageEndpoint CUSTOM_ENDPOINT = CustomMessageEndpoint.getInstance();
    private static final String DEFAULT_MESSAGE = "You are doing great!";
    private static final String API_PROMPT = "/apiPrompt.txt";
    private static final String REQUEST_BODY = "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}";

    private AiMessageEndpoint endpoint = DEFAULT_ENDPOINT;
    private String message = DEFAULT_MESSAGE;
    private String apiKey = "";

    public AiMessageGenerator() {
        loadApiKey();
        requestMessage();
    }

    private void loadApiKey() {
        apiKey = endpoint.getApiKey();
    }

    private void requestMessage() {
        try {
            InputStream apiPromptFile = getClass().getResourceAsStream(API_PROMPT);
            Scanner scanner = new Scanner(apiPromptFile);
            String prompt = scanner.nextLine();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endpoint.getUrl() + apiKey))
                    .header("Content-Type", "text/plain")
                    .POST(HttpRequest.BodyPublishers.ofString(String.format(REQUEST_BODY, prompt)))
                    .build();
            scanner.close();

            HttpClient client = HttpClient.newHttpClient();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(this::parseResponse);
        } catch (Exception e) {
            System.err.println("Error generating message");
            message = DEFAULT_MESSAGE;
        }
    }

    @Override
    public String generateEncouragingMessage() {
        String currentMessage = message;
        requestMessage();
        return currentMessage;
    }

    private void parseResponse(HttpResponse<String> response) {
        message = endpoint.processResponse(response.body());
        if (message.isBlank()) {
            message = DEFAULT_MESSAGE;
        } else if (message.equals("FAIL")) {
            requestMessage();
        }
    }

    @Override
    public void setDefaultEndpoint() {
        this.endpoint = DEFAULT_ENDPOINT;
        loadApiKey();
        this.requestMessage();
    }

    @Override
    public void setCustomEndpoint(String url) {
        this.endpoint = CUSTOM_ENDPOINT;
        this.endpoint.setUrl(url);
        loadApiKey();
        this.requestMessage();
    }

}

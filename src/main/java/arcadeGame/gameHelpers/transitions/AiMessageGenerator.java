package arcadeGame.gameHelpers.transitions;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AiMessageGenerator implements MessageGenerator {
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=";

    private Scanner scanner;
    private String message = "You are doing great!";
    private String apiKey = "";

    public AiMessageGenerator() {
        loadApiKey();
        requestMessage();
    }

    private void loadApiKey() {
        try {
            URL apiKeyFile = getClass().getResource("/apiKey.local");
            scanner = new Scanner(apiKeyFile.openStream());
            apiKey = scanner.nextLine();
            scanner.close();
        } catch (Exception e) {
            System.err.println(
                    "API key not found. Please follow the repo instructions to change the default message.");
        }
    }

    private void requestMessage() {
        if (apiKey.isEmpty()) {
            return;
        }
        try {
            URL apiPromptFile = getClass().getResource("/apiPrompt.txt");
            Scanner scanner = new Scanner(apiPromptFile.openStream());
            String prompt = scanner.nextLine();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GEMINI_URL + apiKey))
                    .POST(HttpRequest.BodyPublishers.ofString(
                            "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}"))
                    .build();
            scanner.close();

            HttpClient client = HttpClient.newHttpClient();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(response -> parseResponse(response));
        } catch (Exception e) {
            System.err.println(
                    "API prompt instructions not found. Please follow the repo instructions to change the default message.");
        }
    }

    @Override
    public String generateEncouragingMessage() {
        String currentMessage = message;
        requestMessage();
        return currentMessage;
    }

    private void parseResponse(HttpResponse<String> response) {
        try {
            Pattern successPattern = Pattern.compile("\"text\": \".*\"");
            Matcher successMatcher = successPattern.matcher(response.body());
            if (!successMatcher.find()) {
                throw new Exception();
            }
            message = successMatcher.group().substring(9, successMatcher.group().length() - 1);
        } catch (Exception e) {
            Pattern failurePattern = Pattern.compile("\"finishReason\": \"SAFETY\"");
            Matcher failureMatcher = failurePattern.matcher(response.body());
            if (failureMatcher.find()) {
                requestMessage();
            } else {
                System.err.println("API request quota exceeded. Try again in a few minutes.");
                message = "You got this!";
            }
        }
    }

}

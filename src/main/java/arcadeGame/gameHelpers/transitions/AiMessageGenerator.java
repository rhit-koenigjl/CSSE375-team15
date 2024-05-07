package arcadeGame.gameHelpers.transitions;

import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AiMessageGenerator implements MessageGenerator {
    private static final String GEMINI_URL =
            "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=";
    private static final String DEFAULT_MESSAGE = "You are doing great!";
    private static final String API_KEY = "/apiKey.local";
    private static final String API_PROMPT = "/apiPrompt.txt";
    private static final String REQUEST_BODY = "{\"contents\":[{\"parts\":[{\"text\":\"%s\"}]}]}";
    private static final int MATCH_START = 9;
    private static final Pattern SUCCESS_PATTERN = Pattern.compile("\"text\": \".*\"");
    private static final Pattern FAILURE_PATTERN = Pattern.compile("\"finishReason\": \"SAFETY\"");
    private static final Pattern INTERNAL_ERROR_MATCHER = Pattern.compile("\"status\": \"INTERNAL\"");

    private String message = DEFAULT_MESSAGE;
    private String apiKey = "";

    public AiMessageGenerator() {
        loadApiKey();
        requestMessage();
    }

    private void loadApiKey() {
        try {
            InputStream apiKeyFile = getClass().getResourceAsStream(API_KEY);
            Scanner scanner = new Scanner(apiKeyFile);
            apiKey = scanner.nextLine();
            scanner.close();
        } catch (Exception e) {
            System.err.println("API key not found");
        }
    }

    private void requestMessage() {
        if (apiKey.isEmpty()) {
            return;
        }
        try {
            InputStream apiPromptFile = getClass().getResourceAsStream(API_PROMPT);
            Scanner scanner = new Scanner(apiPromptFile);
            String prompt = scanner.nextLine();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(GEMINI_URL + apiKey))
                    .POST(HttpRequest.BodyPublishers.ofString(String.format(REQUEST_BODY, prompt)))
                    .build();
            scanner.close();

            HttpClient client = HttpClient.newHttpClient();
            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenAccept(this::parseResponse);
        } catch (Exception e) {
            System.err.println("API prompt instructions not found");
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
            Matcher successMatcher = SUCCESS_PATTERN.matcher(response.body());
            if (!successMatcher.find()) {
                throw new Exception();
            }
            message = successMatcher.group().substring(MATCH_START, successMatcher.group().length() - 1);
        } catch (Exception e) {
            Matcher failureMatcher = FAILURE_PATTERN.matcher(response.body());
            Matcher internalErrorMatcher = INTERNAL_ERROR_MATCHER.matcher(response.body());
            if (failureMatcher.find() || internalErrorMatcher.find()) {
                requestMessage();
            } else {
                System.err.println("API request quota exceeded. Try again in a few minutes.");
                message = DEFAULT_MESSAGE;
            }
        }
    }

}

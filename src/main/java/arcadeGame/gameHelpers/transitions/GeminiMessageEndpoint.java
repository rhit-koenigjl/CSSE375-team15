package arcadeGame.gameHelpers.transitions;

import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class GeminiMessageEndpoint implements AiMessageEndpoint {
    private static final String DEFAULT_ENDPOINT =
            "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=";
    private static final String API_KEY = "/apiKey.local";
    private static final AiMessageEndpoint INSTANCE = new GeminiMessageEndpoint();
    private static final int MATCH_START = 9;
    private static final Pattern SUCCESS_PATTERN = Pattern.compile("\"text\": \".*\"");
    private static final Pattern FAILURE_PATTERN = Pattern.compile("\"finishReason\": \"SAFETY\"");
    private static final Pattern INTERNAL_ERROR_MATCHER =
            Pattern.compile("\"status\": \"INTERNAL\"");

    @Override
    public String getUrl() {
        return DEFAULT_ENDPOINT;
    }

    @Override
    public String getApiKey() {
        String apiKey = "";
        try {
            InputStream apiKeyFile = getClass().getResourceAsStream(API_KEY);
            Scanner scanner = new Scanner(apiKeyFile);
            apiKey = scanner.nextLine();
            scanner.close();
        } catch (Exception e) {
            System.err.println("API key not found");
        }
        return apiKey;
    }

    @Override
    public void setUrl(String url) {}

    @Override
    public String processResponse(String response) {
        try {
            Matcher successMatcher = SUCCESS_PATTERN.matcher(response);
            if (!successMatcher.find()) {
                throw new Exception();
            }
            return successMatcher.group().substring(MATCH_START,
                    successMatcher.group().length() - 1);
        } catch (Exception e) {
            Matcher failureMatcher = FAILURE_PATTERN.matcher(response);
            Matcher internalErrorMatcher = INTERNAL_ERROR_MATCHER.matcher(response);
            if (failureMatcher.find() || internalErrorMatcher.find()) {
                return "FAIL";
            } else {
                System.err.println("API request quota exceeded. Try again in a few minutes.");
                return "FAIL";
            }
        }
    }

    static AiMessageEndpoint getInstance() {
        return INSTANCE;
    }

}

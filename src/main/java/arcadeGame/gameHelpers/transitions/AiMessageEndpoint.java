package arcadeGame.gameHelpers.transitions;

import java.io.InputStream;
import java.util.Scanner;

class AiMessageEndpoint {
    private static final String DEFAULT_ENDPOINT =
            "https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=";
    private static final String API_KEY = "/apiKey.local";
    private static final AiMessageEndpoint INSTANCE = new AiMessageEndpoint();

    private String getEndpointUrl() {
        return DEFAULT_ENDPOINT;
    }
    
    private String getApiKey() {
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

    static String getEndpoint() {
        return INSTANCE.getEndpointUrl();
    }

    static String getKey() {
        return INSTANCE.getApiKey();
    }
}

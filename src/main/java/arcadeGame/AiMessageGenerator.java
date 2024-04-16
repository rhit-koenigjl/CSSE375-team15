package arcadeGame;

import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AiMessageGenerator implements MessageGenerator {
  private static final String GEMINI_URL =
      "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=";

  private CompletableFuture<HttpResponse<String>> response;

  public AiMessageGenerator() {
    requestMessage();
  }

  @Override
  public String generateEncouragingMessage() {
    try {
      Pattern pattern = Pattern.compile("\"text\": \".*\"");
      Matcher matcher = pattern.matcher(response.get().body());
      String output =
          matcher.find() ? matcher.group().substring(9, matcher.group().length() - 1) : "";
      return output;
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }

  public void requestMessage() {
    try {
      URL apiPromptFile = getClass().getResource("/apiPrompt.txt");
      Scanner scanner = new Scanner(apiPromptFile.openStream());
      String prompt = scanner.nextLine();

      URL apiKeyFile = getClass().getResource("/apiKey.local");
      scanner = new Scanner(apiKeyFile.openStream());
      HttpRequest request =
          HttpRequest.newBuilder().uri(URI.create(GEMINI_URL + scanner.nextLine()))
              .POST(HttpRequest.BodyPublishers
                  .ofString("{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}"))
              .build();
      scanner.close();

      HttpClient client = HttpClient.newHttpClient();
      response = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}

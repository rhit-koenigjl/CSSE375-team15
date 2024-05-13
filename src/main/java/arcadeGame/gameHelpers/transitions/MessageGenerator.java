package arcadeGame.gameHelpers.transitions;

public interface MessageGenerator {
    String generateEncouragingMessage();
    void setDefaultEndpoint();
    void setCustomEndpoint(String url);
}

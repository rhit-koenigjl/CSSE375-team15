package arcadeGame.gameHelpers.transitions;

interface AiMessageEndpoint {
    String getUrl();
    String getApiKey();
    void setUrl(String url);
    String processResponse(String response);
}
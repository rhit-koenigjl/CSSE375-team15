package arcadeGame.gameHelpers.transitions;

class CustomMessageEndpoint implements AiMessageEndpoint {
    private static final AiMessageEndpoint INSTANCE = new CustomMessageEndpoint();
    private String url;

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public String getApiKey() {
        return "";
    }

    @Override
    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String processResponse(String response) {
        return response;
    }

    static AiMessageEndpoint getInstance() {
        return INSTANCE;
    }
    
}

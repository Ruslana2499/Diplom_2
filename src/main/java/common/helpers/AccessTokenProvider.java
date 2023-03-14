package common.helpers;

public class AccessTokenProvider {
    private String accessToken = "";

    public AccessTokenProvider() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

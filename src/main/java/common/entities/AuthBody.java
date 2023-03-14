package common.entities;

public class AuthBody {
    private boolean success;
    private String accessToken;
    private String refreshToken;
    private UserPersonalData user;

    public AuthBody() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(UserPersonalData user) {
        this.user = user;
    }

}

package common.entities;

public class UserBody {

    private boolean success;
    private UserPersonalData user;

    public UserBody() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserPersonalData getUser() {
        return user;
    }

    public void setUser(UserPersonalData user) {
        this.user = user;
    }

}

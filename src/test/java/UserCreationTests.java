import api.UserApiClient;
import common.constans.Constants;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserCreationTests {
    private UserApiClient apiClient = new UserApiClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
    }

    @After
    public void cleanUp() {
        apiClient.auth(Constants.USER_AUTH);
        apiClient.delete();
    }

    @Test
    @DisplayName("создать уникального пользователя")
    public void testCreateUser() {
        apiClient.create(Constants.USER)
                .then().statusCode(200).and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("создать пользователя, который уже зарегистрирован")
    public void testCreatingExistingUser() {
        apiClient.create(Constants.USER)
                .then().statusCode(200);
        apiClient.create(Constants.USER)
                .then().statusCode(403).and().assertThat().body("message", equalTo("User already exists"));
    }
}

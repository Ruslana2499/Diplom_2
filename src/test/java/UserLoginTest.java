import api.UserApiClient;
import common.constans.Constants;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserLoginTest {
    private UserApiClient apiClient = new UserApiClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
        apiClient.create(Constants.USER);
    }

    @After
    public void cleanUp() {
        apiClient.delete();
    }

    @Test
    @DisplayName("логин под существующим пользователем")
    public void testLoginUser() {
        apiClient.auth(Constants.USER_AUTH)
                .then().statusCode(200).and().assertThat().body("success", equalTo(true));

    }
}

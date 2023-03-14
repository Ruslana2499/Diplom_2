import api.UserApiClient;
import common.constans.Constants;
import common.entities.UserPersonalData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangingUserDataUseMailTest {
    private UserApiClient apiClient = new UserApiClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
        apiClient.create(Constants.USER);
        apiClient.create(Constants.TEST_USER);
        apiClient.auth(Constants.USER_AUTH);
    }

    @After
    public void cleanUp() {
        apiClient.delete();
    }

    @Test
    @DisplayName("Изменение данных пользователя с почтой, которая уже используется")
    public void testChangingUserDataWithMailAlreadyUse() {
        UserPersonalData user = new UserPersonalData("test-data@yandex.ru", "");
        apiClient.edit(user)
                .then().statusCode(403).and().assertThat().body("message", equalTo("User with such email already exists"));
    }
}

import api.UserApiClient;
import common.constans.Constants;
import common.entities.UserPersonalData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class ChangingUnauthorizedUserDataTests {
    private UserApiClient apiClient = new UserApiClient();

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
        apiClient.create(Constants.USER);
    }

    @After
    public void cleanUp() {
        apiClient.auth(Constants.USER_AUTH);
        apiClient.delete();
    }

    @Test
    @DisplayName("Изменение данных неавторизованного пользователя")
    public void testChangingUserDataWithMailAlreadyUse() {
        UserPersonalData user = new UserPersonalData("test-data@yandex.ru", "");
        apiClient.edit(user)
                .then().statusCode(401).and().assertThat().body("message", equalTo("You should be authorised"));
    }
}

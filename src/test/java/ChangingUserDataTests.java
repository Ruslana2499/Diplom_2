import api.UserApiClient;
import common.constans.Constants;
import common.entities.UserPersonalData;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class ChangingUserDataTests {
    private UserApiClient apiClient = new UserApiClient();
    private String email;
    private String name;

    public ChangingUserDataTests(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Parameterized.Parameters(name = "Тестовые данные: email: {0}, имя: {1}")
    public static Iterable<Object[]> getChangingUserDataTests() {
        return Arrays.asList(new Object[][]{
                {"qwer@qw.ru", "Stewie"},
                {"stewie@qw.ru", ""},
                {"stewie@qw.ru", "Stewie"},
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
        apiClient.create(Constants.USER);
        apiClient.auth(Constants.USER_AUTH);
    }

    @After
    public void cleanUp() {
        apiClient.delete();
    }

    @Test
    @DisplayName("Изменение данных авторизованного пользователя")
    public void testChangingUserData() {
        UserPersonalData user = new UserPersonalData(email, name);
        apiClient.edit(user)
                .then().body("success", equalTo(true))
                .and().body("user.email", equalTo(email))
                .and().body("user.name", equalTo(name));
    }
}

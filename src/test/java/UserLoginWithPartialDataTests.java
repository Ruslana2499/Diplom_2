import api.UserApiClient;
import common.constans.Constants;
import common.entities.UserAuth;
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
public class UserLoginWithPartialDataTests {
    private UserApiClient apiClient = new UserApiClient();

    private String email;
    private String password;

    public UserLoginWithPartialDataTests(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Parameterized.Parameters(name = "Тестовые данные: email: {0}, пароль: {1}")
    public static Iterable<Object[]> getUserLoginWithPartialDataTests() {
        return Arrays.asList(new Object[][]{
                {"poiu@qw.ru", "1234qwerty"},
                {"qwer@qw.ru", "1234poiuy"},
                {"", "1234qwerty"},
                {"qwer@qw.ru", ""},
        });
    }

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
    @DisplayName("логин с неверным логином и паролем")
    public void testLoginEnteredIncorrectlyHasErrorMessage() {
        UserAuth user = new UserAuth(email, password);
        apiClient.auth(user)
                .then().statusCode(401).and().assertThat().body("message", equalTo("email or password are incorrect"));
    }
}

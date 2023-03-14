import api.UserApiClient;
import common.constans.Constants;
import common.entities.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;

@RunWith(Parameterized.class)
public class UserCreationWithPartialDataTests {
    private UserApiClient apiClient = new UserApiClient();

    private String email;
    private String password;
    private String name;

    public UserCreationWithPartialDataTests(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.RESOURCE_URL;
    }

    @Parameterized.Parameters(name = "Тестовые данные: email: {0}, пароль: {1}, имя: {2}")
    public static Iterable<Object[]> getUserCreationWithPartialDataTests() {
        return Arrays.asList(new Object[][]{
                {"", "1234qwerty", "Kuslo"},
                {"qwer@qw.ru", "", "Kuslo"},
                {"qwer@qw.ru", "1234qwerty", ""},
        });
    }

    @Test
    @DisplayName("создать пользователя и не заполнить одно из обязательных полей")
    public void testCreatingWithoutLoginHasErrorMessage() {
        User user = new User(email, password, name);
        apiClient.create(user)
                .then().statusCode(403).and().assertThat().body("message", equalTo("Email, password and name are required fields"));
    }
}

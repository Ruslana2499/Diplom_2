import api.OrderApiClient;
import api.UserApiClient;
import common.constans.Constants;
import common.entities.IngredientsResponse;
import common.entities.Order;
import common.helpers.AccessTokenProvider;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class GetOrdersSpecificUserTests {
    private AccessTokenProvider accessTokenProvider = new AccessTokenProvider();
    private UserApiClient apiClient = new UserApiClient(accessTokenProvider);
    private OrderApiClient apiOrder = new OrderApiClient(accessTokenProvider);
    private String idIngredients;

    @Before
    public void setUp() throws Exception {
        RestAssured.baseURI = Constants.RESOURCE_URL;
        apiClient.create(Constants.USER);
        IngredientsResponse ingredientsResponse = apiOrder.getIngredients().as(IngredientsResponse.class);
        if (ingredientsResponse.getData().length == 0) {
            throw new Exception("No Ingredients");
        }
        idIngredients = ingredientsResponse.getData()[0].get_id();
    }

    @After
    public void cleanUp() {
        apiClient.delete();
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя авторизованный пользователь")
    public void testGetOrdersSpecificUser() {
        apiClient.auth(Constants.USER_AUTH);
        apiOrder.createOrder(new Order(new String[]{idIngredients}));
        apiOrder.getOrder()
                .then().statusCode(200).and().assertThat().body("orders[0].ingredients[0]", equalTo(idIngredients));
    }

    @Test
    @DisplayName("Получение заказов конкретного пользователя неавторизованный пользователь")
    public void testGetOrdersSpecificUnauthorizedUser() {
        apiOrder.createOrder(new Order(new String[]{idIngredients}));
        apiOrder.getOrder()
                .then().statusCode(401).and().assertThat().body("message", equalTo("You should be authorised"));
    }

}

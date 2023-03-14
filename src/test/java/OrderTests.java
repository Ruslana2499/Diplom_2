import api.OrderApiClient;
import api.UserApiClient;
import common.constans.Constants;
import common.entities.IngredientsResponse;
import common.entities.Order;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class OrderTests {
    private UserApiClient apiClient = new UserApiClient();
    private OrderApiClient apiOrder = new OrderApiClient();
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
    @DisplayName("Создание заказа с авторизацией")
    public void testOrder() {
        apiClient.auth(Constants.USER_AUTH);
        apiOrder.createOrder(new Order(new String[]{idIngredients}))
                .then().statusCode(200).and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа без авторизации")
    public void testOrderUnauthorized() {
        apiOrder.createOrder(new Order(new String[]{idIngredients}))
                .then().statusCode(200).and().assertThat().body("success", equalTo(true));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией, без ингредиентов")
    public void testOrderNoIngredients() {
        apiClient.auth(Constants.USER_AUTH);
        apiOrder.createOrder(Constants.EMPTY_ORDER)
                .then().statusCode(400).and().assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа без авторизации, без ингредиентов")
    public void testOrderUnauthorizedNotIngredients() {
        apiOrder.createOrder(Constants.EMPTY_ORDER)
                .then().statusCode(400).and().assertThat().body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("Создание заказа с авторизацией, с неверным хешем ингредиентов")
    public void testOrderInvalidHash() {
        apiClient.auth(Constants.USER_AUTH);
        apiOrder.createOrder(Constants.INVALID_INGREDIENTS_ORDER)
                .then().statusCode(500);
    }

    @Test
    @DisplayName("Создание заказа без авторизации, с неверным хешем ингредиентов")
    public void testOrderUnauthorizedInvalidHash() {
        apiOrder.createOrder(Constants.INVALID_INGREDIENTS_ORDER)
                .then().statusCode(500);
    }
}

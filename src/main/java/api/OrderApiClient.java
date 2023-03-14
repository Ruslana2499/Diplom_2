package api;

import common.constans.Constants;
import common.entities.Order;
import common.helpers.AccessTokenProvider;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class OrderApiClient extends BaseApiClient {
    public OrderApiClient(AccessTokenProvider accessTokenProvider) {
        super(accessTokenProvider);
    }

    public OrderApiClient() {
        super();
    }

    @Step("Создание заказа")
    public Response createOrder(Order order) {
        return this.post(Constants.ORDERS_URI, order, accessTokenProvider.getAccessToken());
    }

    @Step("Получение заказов конкретного пользователя")
    public Response getOrder() {
        return this.get(Constants.ORDERS_URI, accessTokenProvider.getAccessToken());
    }

    @Step("Получение данных об ингредиентах")
    public Response getIngredients() {
        return this.get(Constants.INGREDIENTS_URI, accessTokenProvider.getAccessToken());
    }

}

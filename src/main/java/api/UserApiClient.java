package api;

import common.constans.Constants;
import common.entities.AuthBody;
import common.entities.User;
import common.entities.UserAuth;
import common.entities.UserPersonalData;
import common.helpers.AccessTokenProvider;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class UserApiClient extends BaseApiClient {
    public UserApiClient(AccessTokenProvider accessTokenProvider) {
        super(accessTokenProvider);
    }

    public UserApiClient() {
        super();
    }

    @Step("Авторизация пользователя")
    public Response auth(UserAuth body) {
        Response response = this.post(Constants.LOGIN_URI, body, "");
        AuthBody authBody = response.as(AuthBody.class);
        if (authBody.isSuccess()) {
            accessTokenProvider.setAccessToken(authBody.getAccessToken().substring(7));
        }
        return response;
    }

    @Step("Создание пользователя")
    public Response create(User body) {
        return this.post(Constants.REGISTER_URI, body, "");
    }

    @Step("Удаление пользователя")
    public Response delete() {
        return this.delete(Constants.USER_URI, accessTokenProvider.getAccessToken());
    }

    @Step("Получение данных о пользователе")
    public Response get() {
        return this.get(Constants.USER_URI, accessTokenProvider.getAccessToken());
    }

    @Step("Обновление данных о пользователе")
    public Response edit(UserPersonalData body) {
        return this.patch(Constants.USER_URI, body, accessTokenProvider.getAccessToken());
    }
}

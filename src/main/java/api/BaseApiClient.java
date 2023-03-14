package api;

import common.helpers.AccessTokenProvider;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BaseApiClient {
    protected AccessTokenProvider accessTokenProvider;

    public BaseApiClient(AccessTokenProvider accessTokenProvider) {
        this.accessTokenProvider = accessTokenProvider;
    }

    public BaseApiClient() {
        accessTokenProvider = new AccessTokenProvider();
    }

    protected Response post(String url, Object body, String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .and()
                .body(body)
                .when()
                .post(url);
    }

    protected Response delete(String url, String accessToken) {
        return given()
                .auth().oauth2(accessToken)
                .delete(url);
    }

    protected Response get(String url, String accessToken) {
        return given()
                .auth().oauth2(accessToken)
                .get(url);
    }

    protected Response patch(String url, Object body, String accessToken) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(accessToken)
                .and()
                .body(body)
                .when()
                .patch(url);
    }
}

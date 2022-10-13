package lib;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;

import java.util.Map;

public class ApiCoreRequests {

    @Step("GET-request with token and cookie")
    public Response makeGetRequest(String url, String token, String cookie) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .cookie("auth_sid", cookie)
                .get(url)
                .andReturn();
    }

    @Step("GET-request with cookie")
    public Response makeGetRequestWithCookie(String url, String cookie) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .cookie("auth_sid", cookie)
                .get(url)
                .andReturn();
    }

    @Step("GET-request with token")
    public Response makeGetRequestWithToken(String url, String token) {
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .header(new Header("x-csrf-token", token))
                .get(url)
                .andReturn();
    }


    @Step("POST-request")
    public Response makePostRequest(String url, Map<String, String> body){
        return RestAssured.given()
                .filter(new AllureRestAssured())
                .body(body)
                .post(url)
                .andReturn();
    }
}
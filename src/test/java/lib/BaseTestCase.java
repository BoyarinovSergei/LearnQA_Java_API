package lib;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import static org.hamcrest.Matchers.hasKey;

import java.util.Map;
import java.util.Random;

public class BaseTestCase {
    protected String getHeader(Response response, String name){
        Headers headers = response.getHeaders();

        Assertions.assertTrue(headers.hasHeaderWithName(name), "Response doesn't have header with name " + name);
        return headers.getValue(name);
    }

    protected String getCookie(Response response, String name){
        Map<String, String> cookies = response.getCookies();

        Assertions.assertTrue(cookies.containsKey(name), "Response doesn't have cookie with name " + name);
        return cookies.get(name);
    }

    protected int getIntFromJson(Response response, String name){
        response.then().assertThat().body("$", hasKey(name));
        return response.jsonPath().getInt(name);
    }

    public static String generateString(String characters, int length)
    {
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(new Random().nextInt(characters.length()));
        }
        return new String(text);
    }
}

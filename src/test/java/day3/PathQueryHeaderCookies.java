package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class PathQueryHeaderCookies {

    @Test
    void getCookiesInfo() {

        given()
                .when()
                .get("https://www.google.co.in/")
                .cookies()
                .forEach((key, value) -> System.out.println(key + ":" + value));

    }

}

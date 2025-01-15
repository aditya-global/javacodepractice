package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class pathQueryHeaderCookies {

    @Test
    void getCookiesInfo() {

        given()
                .when()
                .get("https://www.google.co.in/")
                .cookies()
                .forEach((key, value) -> System.out.println(key + ":" + value));

    }

}

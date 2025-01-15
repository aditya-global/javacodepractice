package day3;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathQueryHeaderCookies {

    @Test
    void getCookiesInfo() {

        given()
                .when()
                .get("https://www.google.co.in/")
                .cookies()
                .forEach((key, value) -> System.out.println("Cache: " + key + ":" + value));

    }

    @Test
    void getHeadersInfo() {

        given()
                .when()
                .get("https://www.google.co.in/")
                .headers()
                .forEach(header -> System.out.println("Header: " + header.getName() + ":" + header.getValue()));

    }

}

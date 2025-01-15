package day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class PathQueryHeaderCookies {

    @Test
    void getCookiesInfo() {

        Response res = given()
                .when()
                .get("https://www.google.co.in/");

               res.cookies()
                .forEach((key, value) -> System.out.println("Cache: " + key + ":" + value));
                //or
                //res.detailedCookies().forEach(System.out::println);
                // this will give output similar too log().cookies();

    }

    @Test
    void getHeadersInfo() {

        given()
                .when()
                .get("https://www.google.co.in/").then().log().headers();
//                .headers()
//                .forEach(header -> System.out.println("Header: " + header.getName() + ":" + header.getValue()))


    }

}

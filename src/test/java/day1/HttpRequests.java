package day1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HttpRequests {

    @Test
    void getUsers() {

        when().get("https://reqres.in/api/users?page=2")
                .then()
                .body("page", equalTo(2))
                .log()
                .body();
    }
}

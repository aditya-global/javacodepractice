package day2;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WaysToCreateRequestBody {
    String id;

    @Test
    void usingHashmap() {
        String name = "Aditya";
        String location = "India";
        String pnumber = "8989898989";
        String[] course = {"Selenium", "Java"};


        HashMap<String, Object> data = new HashMap<>();

        data.put("name", name);
        data.put("location", location);
        data.put("phone", pnumber);
        data.put("courses", course);

        id = given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("http://localhost:4500/students")
                .then()
                .statusCode(201)
                .body("name", equalTo(name))
                .body("location", equalTo(location))
                .body("phone", equalTo(pnumber))
                .body("courses[0]", equalTo(course[0]))
                .body("courses[1]", equalTo(course[1]))
                .header("Content-Type", "application/json")
                .log().body().extract().response().jsonPath().getString("id");
        System.out.println("Id of new student is :" + id);

    }

    @Test(dependsOnMethods = "usingHashmap")
    void deleteUser() {

        given()
                .pathParam("id", id)
                .when()
                .delete("http://localhost:4500/students/{id}")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test(priority = 2)
    void usingPOJOClass(){

    }
}

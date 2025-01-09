package day1;

import io.restassured.RestAssured;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //restassured internally uses hamcrest matchers

public class HttpRequests {

    private static final Logger log = LoggerFactory.getLogger(HttpRequests.class);
    int id;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }


    @Test(priority = 1, enabled = false)
    void getUsers() {

        given()
                .queryParam("page", 2);
        when()
                .get("/api/users")
                .then()
                .body("page", equalTo(2))
                .log()
                .body();
    }

    @Test(priority = 2, enabled = false)
    void createUser() {

        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "Aditya");
        hm.put("job", "QA");

        //  This process posts an api request and 'then' check the status code
        //  and logs the api response body

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .log().body();

    }

    @Test(priority = 3, enabled = true)
    void createUser2() {

        // Same post request but for capturing a response parameter

        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "Aditya5");
        hm.put("job", "QA");

        id = given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("/api/users")
                .jsonPath().getInt("id"); //capturing an int response
        System.out.println("User id create is: "+id);
    }

    //This test will execute only when test getUser2 will pass
    @Test(priority = 4, dependsOnMethods = {"createUser2"})
    void updateUser() {

        // Here, we will update the "job" value using id created and captured from createUser2

        HashMap<String, String> hm = new HashMap<>();
        hm.put("name", "Aditya5");
        hm.put("job", "Automation QA");

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .put("/api/users/" + id)
                .then()
                .statusCode(200)
                .log().

    }
    @Test(priority = 5, dependsOnMethods = {"createUser2"})
    void deleteUser(){
        given()
                .pathParam("id",id)
                .when()
                .delete("/api/users/{id}")
                .then()
                .statusCode(204)
                .log().all();
    }


}

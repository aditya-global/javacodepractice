package day1;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*; //restassured internally uses hamcrest matchers

public class HttpRequests {

    int id;

    @Test (priority = 1)
    void getUsers() {

        when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .body("page", equalTo(2))
                .log()
                .body();
    }
    @Test(priority = 2)
    void  createUser(){
        HashMap<String,String> hm = new HashMap<>();
        hm.put("name","Aditya");
        hm.put("job","QA");


        //  This process posts an api request and 'then' check the status code
        //  and logs the api response body

        given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .log().body();

        // Same post request but for capturing a response parameter

        id = given()
                .contentType("application/json")
                .body(hm)
                .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");

    }
}

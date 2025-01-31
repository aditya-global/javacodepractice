package day4;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class ParsingJsonResponse {

    @Test
            void testJSONResponse(){

        // Approach 1
        /*
        given()
            .contentType("ContentType.JSON")
        .when()
            .get("http://localhost:3000/store")
        .then()
            .statusCode(200)
            .header("Content-Type", "application/json;")
            .body("book[3].title", equalTo("1984"));
        */


        //Approach 2
        Response res = given()
                .contentType("application/json")
                .when()
                .get("http://localhost:3000/store");

        // Validations
        Assert.assertEquals(res.getStatusCode(), 200); // Validation 1
        Assert.assertEquals(res.header("Content-Type"), "application/json");

        // Extracting book title with reference to unique key
        String bookTitle = res.jsonPath().getString("book.find{it.author == 'J.R.R. Tolkien'}.title");
        Assert.assertEquals(bookTitle, "The Hobbit");

        // Extracting book title with reference to repeating key
        List<String> bookTitles = res.jsonPath().getList("book.findAll{it.category == 'fantasy'}.title");
        Assert.assertTrue(bookTitles.contains("The Hobbit"));
    }

}

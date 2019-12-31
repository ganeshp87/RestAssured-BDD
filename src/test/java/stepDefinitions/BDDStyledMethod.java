package stepDefinitions;

import io.restassured.http.ContentType;
import org.hamcrest.core.Is;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BDDStyledMethod {


    public static void SimpleGETPost(String postNumber){
        given().contentType(ContentType.JSON).
                when().get(String.format("http://localhost:3000/posts/%s", postNumber)).
                then().body("author", is("Ganesh P")).
        		and().body("title",is("Restassured with BDD"));
    }

    public static void PerformContainsCollection() {
        given()
                .contentType(ContentType.JSON)
        .when()
                .get("http://localhost:3000/posts/")
        .then()
                .body("author", containsInAnyOrder("Ganesh P", "Ganesh P", null, "Ganesh P", "ExecuteAutomation", "Ganesh P", "Ganesh P")).statusCode(200);
    }

    public static void PerformPathParameter() {

        given()
                .contentType(ContentType.JSON).
        with()
                .pathParams("post", 1).
        when()
                .get("http://localhost:3000/posts/{post}").
        then()
                .body("author", containsString("Ganesh P"));
    }

    public static void PerformQueryParameter(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("id", 1).
        when()
                .get("http://localhost:3000/posts/").
        then()
                .body("author", hasItem("Ganesh P"));
    }

    public static void PerformPOSTWithBodyParameter() {
        
    	HashMap<String,String> postContent = new HashMap<>();
        postContent.put("id", "4");
        postContent.put("title", "BDD-RestAssured Exercise");
        postContent.put("author", "Ganesh P");

        given()
                .contentType(ContentType.JSON).
        with()
                .body(postContent).
        when()
                .post("http://localhost:3000/posts").
        then()
                .body("author", Is.is("Ganesh P"));
    }

}

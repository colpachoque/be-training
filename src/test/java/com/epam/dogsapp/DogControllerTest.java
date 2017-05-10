package com.epam.dogsapp;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.IsEqual.equalTo;

public class DogControllerTest {

    @Test
    public void shouldGetDogById() {
//        Dog newDog = new Dog("Ukrop", "Shepherd", 50);
//        Response r = given()
//                .contentType(ContentType.JSON)
//                .body(newDog)
//                .when()
//                .post("/dog");
//
//        int code = r.getStatusCode();
//        System.out.println(code);
//        String id = r.getHeader("").substring(r.getHeader("").lastIndexOf("/") + 1);
//        System.out.println(id);

        when().
        get("dog/" + 0).
        then()
                .statusCode(200)
                .body("name", equalTo("Name0"))
                .body("breed", equalTo("Korgi0"))
                .body("weight", equalTo(50));
    }

    @Test
    public void shouldGetAllDogs() {
        given().
                when().
                get("/dog").
                then().
                assertThat().
                contentType(ContentType.JSON).
                statusCode(200);
    }

    @Test
    public void postExample() {
        String myJson = "{\"name\": \"pojo\", \"breed\":\"tax\", \"weight\":\"50\" }";
        RestAssured.baseURI = "http://localhost:8180";
        Response response = given().
                contentType(ContentType.JSON).
                body(myJson).
                when().
                post("/dog");

        String body = response.getBody().asString();
        System.out.println(body);
    }
}

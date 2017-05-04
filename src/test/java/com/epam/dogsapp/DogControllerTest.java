package com.epam.dogsapp;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class DogControllerTest {

//    @Test
//    public void shouldGetDogById() {
//        Dog newDog = new Dog(1, "Ukrop", "Shepherd", 50);
//        int id = 1;
//        Response r = given()
//                .contentType(ContentType.JSON)
//                .body(newDog)
//                .when()
//                .post("/dog/" + id);
//
//        String body = r.getBody().asString();
//        System.out.println(body);
//
//        given().
//                param("id", id).
//        when().
//                get("/dog/{id}").
//        then()
//                .statusCode(200)
//                .body("name", equalTo(newDog.getName()))
//                .body("breed", equalTo(newDog.getBreed()))
//                .body("weight", equalTo(newDog.getWeight()));
//    }
}

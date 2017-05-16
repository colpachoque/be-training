package com.epam.dogsapp;

        import io.restassured.RestAssured;
        import io.restassured.http.ContentType;
        import io.restassured.response.Response;
        import org.testng.annotations.BeforeTest;
        import org.testng.annotations.Test;

        import static io.restassured.RestAssured.given;
        import static io.restassured.RestAssured.when;
        import static org.hamcrest.core.IsEqual.equalTo;
        import static org.testng.AssertJUnit.assertTrue;
        import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

public class DogControllerTest {

    @BeforeTest
    public void postExample() {
        RestAssured.baseURI = "http://localhost:8180";
    }

    @Test public void afterDogIsPosted_getsDogWithSameFields() {
        Dog original = new Dog("pojo", "tax", 50);
        Dog created = postDog(original);
        original.setId(created.getId());
        Dog fromDb = getDogById(original.getId());
//        assertReflectionEquals(original, fromDb);
    }

    private Dog getDogById(int id) {
        return given().port(8180).pathParam("id", id).get("/dog/{id}").as(Dog.class);
    }

    private Dog postDog(Dog original) {
        return given().port(8180).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(original).
                when().post("/dog").as(Dog.class);
    }

    @Test
    public void shouldUpdateDog() {
        Dog original = new Dog("Francheska", "chinchilla", 2);
        Dog created = postDog(original);
        original.setId(created.getId());
        original.setWeight(3);
        Dog updatedDog = putDog(original);
//        assert(updatedDog.getWeight(), original.getWeight());
    }

    private Dog putDog(Dog original) {
        return given().port(8180).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(original).
                when().pathParam("id", original.getId()).post("/dog/{id}").as(Dog.class);
    }

    @Test
    public void shouldDeleteDog() {
        Dog original = new Dog("Freedom", "dog", 20);
        Dog created = postDog(original);
        original.setId(created.getId());
        deleteDog(original);
        Dog dog = getDogById(original.getId());
    }

    private Dog deleteDog(Dog original) {
        return given().port(8180).
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(original).
                when().pathParam("id", original.getId()).delete("/dog/{id}").as(Dog.class);
    }
}

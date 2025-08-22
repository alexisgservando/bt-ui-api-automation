package api;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PetStoreApiTest {

    // ID USING TIMESTAMP
    private String petId;

    @BeforeClass
    public void setup() {
        baseURI = utils.ConfigReader.get("base.url");
        petId = String.valueOf(System.currentTimeMillis() / 1000L);
    }

    // JSON PAYLOAD
    private JsonObject pet(String name, String status) {
        JsonObject json = new JsonObject();
        json.addProperty("id", petId);   // store as string
        json.addProperty("name", name);
        json.addProperty("status", status);
        return json;
    }

    @Test
    public void createPet_success() {
        given()
            .contentType(ContentType.JSON)
            .body(pet("Pluto", "available").toString()) // send String
        .when()
            .post("/pet")
        .then()
            .statusCode(anyOf(is(200), is(201)))
            .body("id", equalTo(Integer.parseInt(petId)))
            .body("name", equalTo("Pluto"))
            .body("status", equalTo("available"));
    }

    @Test(dependsOnMethods = "createPet_success")
    public void getPet_success() {
        when()
            .get("/pet/{id}", petId)
        .then()
            .statusCode(200)
            .body("id", equalTo(Integer.parseInt(petId)));
    }

    @Test(dependsOnMethods = "getPet_success")
    public void updatePet_success() {
        given()
            .contentType(ContentType.JSON)
            .body(pet("PlutoUpdated", "sold").toString()) // send String
        .when()
            .put("/pet")
        .then()
            .statusCode(200)
            .body("name", equalTo("PlutoUpdated"))
            .body("status", equalTo("sold"));
    }

    @Test(dependsOnMethods = "updatePet_success")
    public void deletePet_success() {
        when()
            .delete("/pet/{id}", petId)
        .then()
            .statusCode(anyOf(is(200), is(204)));
    }

    @Test(dependsOnMethods = "deletePet_success")
    public void getPet_afterDelete_notFound() {
        when()
            .get("/pet/{id}", petId)
        .then()
            .statusCode(404);
    }
}

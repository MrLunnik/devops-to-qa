package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class JsonSchemaTest {

    private static final String BASE_URL = "https://api.example.com"; // замени на актуальный URL

    @Test
    void userSchemaShouldBeValid() {
        RestAssured
          .given()
            .contentType(ContentType.JSON)
            .header("Authorization", "Bearer YOUR_TOKEN") // если требуется
          .when()
            .get(BASE_URL + "/users/abc123")
          .then()
            .statusCode(200)
            .assertThat()
            .body(matchesJsonSchemaInClasspath("user-schema.json"));
    }
}

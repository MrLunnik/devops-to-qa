```java
package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PasswordResetTest {

    private static final String BASE_URL = "https://api.example.com"; // замени на актуальный URL

    @Test
    void shouldResetPasswordSuccessfully() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"password\":\"NewPass123\", \"confirm\":\"NewPass123\"}")
        .when()
            .patch(BASE_URL + "/users/abc123/password")
        .then()
            .statusCode(200)
            .body(containsString("success"));
    }

    @Test
    void shouldReturn400WhenPasswordsDoNotMatch() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"password\":\"abc\", \"confirm\":\"xyz\"}")
        .when()
            .patch(BASE_URL + "/users/abc123/password")
        .then()
            .statusCode(400);
    }

    @Test
    void shouldReturn401WhenNoTokenProvided() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"password\":\"NewPass123\", \"confirm\":\"NewPass123\"}")
        .when()
            .patch(BASE_URL + "/users/abc123/password")
        .then()
            .statusCode(401);
    }

    @Test
    void shouldReturn404ForNonexistentUserId() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"password\":\"NewPass123\", \"confirm\":\"NewPass123\"}")
        .when()
            .patch(BASE_URL + "/users/nonexistent-id/password")
        .then()
            .statusCode(404);
    }
}

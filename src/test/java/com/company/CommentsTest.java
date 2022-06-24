package com.company;

import com.company.services.CommentsService;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class CommentsTest extends BaseTest{

    @Test
    @DisplayName("Check Comments size")
    void getComments() {
        given()
                .spec(CommentsService.getCommentRequestSpecifications())
        .when()
                .get()
        .then()
                .spec(CommentsService.getCommentResponseSpecifications())
                .and() // SYNTACTIC SUGAR
                .body("", hasSize(greaterThan(5)));
    }


    @Test
    @DisplayName("Check fist Comment id greater the 1600")
    void getFirstCommentId() {
        given()
                .spec(CommentsService.getCommentRequestSpecifications())
        .when()
                .get()
        .then()
                .spec(CommentsService.getCommentResponseSpecifications())
                .and() // SYNTACTIC SUGAR
                .body("[0].id", greaterThan(1600));
    }

    @Test
    @DisplayName("Validate Comments json schema")
    void validateJsonSchema() {
        given()
                .spec(CommentsService.getCommentRequestSpecifications())
        .when()
                .get()
        .then()
                .assertThat() // SYNTACTIC SUGAR
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("jsonSchemas/comments.json"));
    }



}

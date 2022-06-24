package com.company;

import com.company.models.User;
import com.company.services.UserService;
import com.github.javafaker.Faker;
import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.oneOf;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UsersTest extends BaseTest {


    @Test
    @DisplayName("Check GET Users status code and response time V1")
    void getUsersV1() {
        given()
                .relaxedHTTPSValidation()
                .baseUri("https://gorest.co.in")
                .contentType(ContentType.JSON)
                .basePath("/public/v2/users")
        .when()
                .get()
        .then()
                .statusCode(200)
                .and()
                .time(lessThan(1500L));
    }

    @Test
    @DisplayName("Check GET Users status code and response time V2")
    void getUsersV2() {
        given()
                .spec(UserService.getUserRequestSpecifications())
        .when()
                .get()
        .then()
                .statusCode(200)
                .and()
                .time(lessThan(500L));
    }


    @Test
    @DisplayName("Create User status code and response time V1")
    void createUseV1() {
        given()
                .spec(UserService.getUserRequestSpecifications())
        .when()
                .body("""
                            {
                                "name":"Johnny Poghosyan",
                                "gender":"male",
                                "email":"poghros.dzadza@fake.com",
                                "status":"active"
                            }
                        """)
                .post()
        .then()
                .spec(UserService.getUserResponseSpecifications())
                .and()
                .body("id", is(Matchers.notNullValue()),
                        "name", equalTo("Johnny Poghosyan"),
                        "gender", oneOf("male", "female"),
                        "email", equalTo("poghros.dzadza@fake.com"));
    }


    @Test
    @DisplayName("Create User status code and response time V2")
    void createUserV2() {
        Faker faker = new Faker();
        User user = new User(
                faker.name().fullName(),
                faker.name().username() + "@yopmail.com",
                RandomUtils.nextBoolean() ? "male" : "female",
                "active");


        User userResponse = given()
                .spec(UserService.getUserRequestSpecifications())
                .when()
                .body(user)
                .post()
                .then()
                .spec(UserService.getUserResponseSpecifications())
                .and()
                .extract()
                .as(User.class);

        assertAll(
                () -> assertNotNull(userResponse.id),
                () -> assertEquals(userResponse.name, user.name),
                () -> assertEquals(userResponse.email, user.email),
                () -> assertEquals(userResponse.gender, user.gender),
                () -> assertEquals(userResponse.status, user.status)
        );


    }


}

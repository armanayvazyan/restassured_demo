package com.company;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    static {
        RestAssured.filters(new AllureRestAssured());
        RestAssured.filters(new RestAssuredResponseFilter());
    }
}

package com.company.services;

import com.company.Commons;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;

public class UserService extends BaseService{

    public static RequestSpecification getUserRequestSpecifications() {
        return getBaseRequestSpecifications()
                .setBasePath("/users")
                .build()
                .header("Authorization", "Bearer " + Commons.GO_REST_TOKEN);
    }


    public static ResponseSpecification getUserResponseSpecifications() {
        return new ResponseSpecBuilder()
                .expectStatusCode(allOf(greaterThanOrEqualTo(200),lessThan(299)))
                .expectResponseTime(lessThan(1500L))
                .expectBody("$", is(not(empty())))
                .build();
    }
}

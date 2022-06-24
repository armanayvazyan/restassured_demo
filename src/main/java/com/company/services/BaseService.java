package com.company.services;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;

public class BaseService {

    public static final String BASE_URL = "https://gorest.co.in/public/v2/";

    protected static RequestSpecBuilder getBaseRequestSpecifications() {
        return new RequestSpecBuilder()
                .setRelaxedHTTPSValidation()
                .setBaseUri(BASE_URL)
                .setContentType(ContentType.JSON);
    }

}

package com.vinod.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApiClient {
    private static final String BASE_URL = "http://jsonplaceholder.typicode.com";
    public Response get(String endpoint) {
        Response response= RestAssured.get(BASE_URL + endpoint);
        log.info("Response for the Request {}",response.prettyPrint());
        return response;
    }
}

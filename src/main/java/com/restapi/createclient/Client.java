package com.restapi.createclient;

import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface Client {

    RequestSpecification getResponseSpecification();
    Response getRequestResponse(RequestProperties requestProperties);

}

package com.restapi.createclient;

import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface Client {

    void setRequestProperties(RequestProperties requestProperties);
    RequestSpecification getResponseSpecification();
//    Response getRequest(RequestProperties requestProperties);
    Response getRequest();

}

package com.restapi.createclient;

import com.google.inject.ImplementedBy;
import com.restapi.properties.RequestProperties;
import com.restapi.requestbuilder.RequestBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@ImplementedBy(RequestBuilder.class)
public interface Client {

    void setRequestProperties(RequestProperties requestProperties);
    RequestSpecification getResponseSpecification();
    Response getRequest();
    Response postRequest();
}

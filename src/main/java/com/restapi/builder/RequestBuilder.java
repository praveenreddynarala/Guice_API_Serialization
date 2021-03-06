package com.restapi.builder;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.restapi.interfaces.Client;
import static io.restassured.RestAssured.given;

import com.restapi.properties.RequestProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RequestBuilder implements Client {

    @Inject(optional=true) @Named("Server Url")
    private String serverUrl = "";
    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecification;

    private RequestProperties requestProperties;

    public RequestSpecBuilder getRequestSpecBuilder() {
        requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(requestProperties.getBaseUrl());

        requestSpecBuilder.addHeaders(this.requestProperties.getReqHeaders());
        if(this.requestProperties.getReqBody()!= null)
            requestSpecBuilder.setBody(this.requestProperties.getReqBody());

        return requestSpecBuilder;
    }

    @Override
    public void setRequestProperties(RequestProperties requestProperties) {
        this.requestProperties = requestProperties;
    }

    @Override
    public RequestSpecification getResponseSpecification() {
        requestSpecification = getRequestSpecBuilder().build();

        if(this.requestProperties.getUserName() != null && !this.requestProperties.getUserName().isEmpty()
                && this.requestProperties.getPsw() != null && !this.requestProperties.getPsw().isEmpty())
            requestSpecification.auth().preemptive().basic(this.requestProperties.getUserName() , this.requestProperties.getPsw());
        else if(this.requestProperties.getToken() != null && !this.requestProperties.getToken().isEmpty())
            requestSpecification.auth().oauth2(this.requestProperties.getToken());

        return given().spec(requestSpecification);
    }

    @Override
    public Response getRequest() {
        if(!requestProperties.getBaseUrl().isEmpty() || requestProperties.getBaseUrl() != null)
            serverUrl = requestProperties.getBaseUrl();
        return getResponseSpecification().get(requestProperties.getUrlEndPoint());
    }

    @Override
    public Response postRequest(){
        return getResponseSpecification().post(serverUrl + requestProperties.getUrlEndPoint());
    }
}

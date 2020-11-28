package com.restapi.requestbuilder;

import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import com.restapi.createclient.Client;
import static io.restassured.RestAssured.given;

import com.restapi.properties.RequestProperties;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RequestBuilder implements Client {

    private RequestSpecBuilder requestSpecBuilder;
    private RequestSpecification requestSpecification;

    private RequestProperties requestProperties;

//    @AssistedInject
//    public RequestBuilder(
//            @Assisted String reqHeaders,
//            @Assisted String reqBody,
//            @Assisted String baseUrl,
//            @Assisted String userName,
//            @Assisted String psw,
//            @Assisted String token,
//            @Assisted String secretKey,
//            @Assisted String clientId){
//        this.reqstHeaders = reqHeaders;
//        this.baseUrl = baseUrl;
//        this.reqBody = reqBody;
//        this.userName = userName;
//        this.psw = psw;
//        this.token = token;
//        this.secretKey = secretKey;
//        this.clientId = clientId;
//    }

//    @Inject
//    public RequestBuilder(RequestProperties requestProperties){
//        this.requestProperties = requestProperties;
//    }

    public RequestSpecBuilder getRequestSpecBuilder() {
        requestSpecBuilder = new RequestSpecBuilder();

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
        return getResponseSpecification().get(requestProperties.getBaseUrl());
    }
}

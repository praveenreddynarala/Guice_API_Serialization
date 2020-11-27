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

    private Map<String, String> reqHeaders = new HashMap<>();
    private String reqstHeaders;
    private String reqBody;
    private String baseUrl;
    private String userName;
    private String psw;
    private String token;
    private String secretKey;
    private String clientId;

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

    public Map<String, String> getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(String headersString) {
        if(headersString == null || headersString.trim() ==""){
            return;
        }
        String[] headerStr = headersString.trim().split(",");
        String[] headerEntry = null;
        for (int i = 0; i < headerStr.length; i++){
            headerEntry = headerStr[i].trim().split(":");
            if(headerEntry.length != 2){
                continue;
            }
            this.reqHeaders.put(headerEntry[0].trim(), headerEntry[1].trim());
        }
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        if (reqBody == null) {
            reqBody = "";
        }
        this.reqBody = reqBody.trim();
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

//    public RequestBuilder(RequestProperties requestProperties){ this.requestProperties = requestProperties; }

    public RequestSpecBuilder getRequestSpecBuilder() {
        requestSpecBuilder = new RequestSpecBuilder();

        requestSpecBuilder.addHeaders(this.requestProperties.getReqHeaders());
        if(this.requestProperties.getReqBody()!= null)
            requestSpecBuilder.setBody(this.requestProperties.getReqBody());

        return requestSpecBuilder;
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
    public Response getRequestResponse(RequestProperties requestProperties) {
        this.requestProperties = requestProperties;
        return getResponseSpecification().get(requestProperties.getBaseUrl());
    }
}

package com.restapi.requests;

import com.restapi.interfaces.Client;
import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;

import javax.inject.Inject;
import javax.inject.Named;

public class GitHubGetRequest {

    private Client client;
    private String baseUrl;
    private String contentType;
    private String token;

    @Inject
    public GitHubGetRequest(
            Client client,
            @Named("base_url") String baseUrl,
            @Named("content_type") String content_type,
            @Named("token") String token
    ){
        this.client = client;
        this.baseUrl = baseUrl;
        this.contentType = content_type;
        this.token = token;
    }

    public String getBaseUrl(){return this.baseUrl;}
    public String getContentType(){return this.contentType;}
    public String getToken(){return this.token;}

    public Response getRequest(RequestProperties requestProperties){
        client.setRequestProperties(requestProperties);
        return client.getRequest();
    }

}

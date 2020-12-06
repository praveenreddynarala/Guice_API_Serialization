package com.restapi.requests;

import com.restapi.interfaces.Client;
import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;

import javax.inject.Inject;
import javax.inject.Named;

public class PostRequest {

    private Client client;
    private String baseUrl;
    private String contentType;

    @Inject
    public PostRequest(
            Client client,
            @Named("base_url") String baseUrl,
            @Named("content_type") String content_type
    ){
        this.client = client;
        this.baseUrl = baseUrl;
        this.contentType = content_type;
    }

    public String getBaseUrl(){return this.baseUrl;}
    public String getContentType(){return this.contentType;}

    public Response postRequest(RequestProperties requestProperties){
        client.setRequestProperties(requestProperties);
        return client.postRequest();
    }
}

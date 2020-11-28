package com.restapi.requests;

import com.restapi.createclient.Client;
import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;

import javax.inject.Inject;

public class PostRequest {

    private Client client;

    @Inject
    public PostRequest(Client client){this.client = client;}

    public Response postRequest(RequestProperties requestProperties){
        client.setRequestProperties(requestProperties);
        return client.postRequest();
    }
}

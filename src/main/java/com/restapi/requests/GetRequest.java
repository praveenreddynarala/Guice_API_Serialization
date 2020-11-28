package com.restapi.requests;

import com.restapi.createclient.Client;
import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;

import javax.inject.Inject;
import javax.inject.Named;

public class GetRequest {

    private Client client;

    @Inject
    public GetRequest(Client client) {
        this.client = client;
    }

    public Response getRequest(RequestProperties requestProperties){
        client.setRequestProperties(requestProperties);
        return client.getRequest();
    }

}

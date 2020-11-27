package com.restapi.requests;

import com.restapi.createclient.Client;
import com.restapi.properties.RequestProperties;
import io.restassured.response.Response;

import javax.inject.Inject;
import javax.inject.Named;

public class GetRequest {

    private Client client;

    public void setRequestProperties(RequestProperties requestProperties) {
        this.requestProperties = requestProperties;
    }

    private RequestProperties requestProperties;

    @Inject
    public GetRequest(Client client, RequestProperties requestProperties) {
        this.client = client;
        this.requestProperties = requestProperties;
    }

    public Response getResponse(RequestProperties requestProperties){
        return client.getRequestResponse(requestProperties);
    }

}

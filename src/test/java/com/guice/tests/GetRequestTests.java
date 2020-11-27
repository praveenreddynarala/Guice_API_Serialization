package com.guice.tests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.restapi.createclient.Client;
import com.restapi.guicemodule.GetRequestModule;
import com.restapi.guicemodule.RequestPropertiesModule;
import com.restapi.properties.RequestProperties;
import com.restapi.properties.RequestPropertiesFactory;
import com.restapi.requestbuilder.RequestBuilder;
import com.restapi.requests.GetRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.HashMap;

public class GetRequestTests {

//    @Inject private RequestPropertiesFactory requestPropertiesFactory;
    private RequestProperties requestProperties;
    private Injector injector;
    private GetRequest request;

    @BeforeEach
    public void beforeTest(){
        requestProperties = new RequestProperties();
//        Client builder = requestPropertiesFactory.create(
//                "Content-Type:application/json",
//                "", "https://reqres.in/api/users?page=2",
//                "", "", "", "", ""
//        );
//        builder.getRequestResponse();

        Injector injector = Guice.createInjector(new GetRequestModule());
        request = injector.getInstance(GetRequest.class);
        System.out.println(request);
//        request.requestProperties = requestProperties;
    }
    @Test
    public void sendGetRequest() {
//        request.requestProperties.setBaseUrl("https://reqres.in/api/users?page=2");
//        request.requestProperties.setReqHeaders("Content-Type:application/json");
        requestProperties.setBaseUrl("https://reqres.in/api/users?page=2");
        requestProperties.setReqHeaders("Content-Type:application/json");
        request.setRequestProperties(requestProperties);
        Response response = request.getResponse(requestProperties);
        Assertions.assertTrue(response.getStatusCode()==200, "Response Status Code :: "+response.getStatusCode());
    }

}

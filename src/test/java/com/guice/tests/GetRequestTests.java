package com.guice.tests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.restapi.guicemodule.RequestModule;
import com.restapi.properties.RequestProperties;
import com.restapi.requests.GetRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetRequestTests {

    private RequestProperties requestProperties;
    private GetRequest request;

    @BeforeEach
    public void beforeTest(){
        requestProperties = new RequestProperties();
        Injector injector = Guice.createInjector(new RequestModule());
        request = injector.getInstance(GetRequest.class);
    }

    @Test
    public void sendGetRequest() {
        requestProperties.setBaseUrl("https://reqres.in/api/users?page=2");
        requestProperties.setReqHeaders("Content-Type:application/json");
        Response response = request.getRequest(requestProperties);
        System.out.println(response.getStatusCode());
        Assertions.assertTrue(response.getStatusCode()==200, "Response Status Code :: "+response.getStatusCode());
    }

}

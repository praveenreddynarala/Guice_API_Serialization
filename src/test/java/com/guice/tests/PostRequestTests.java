package com.guice.tests;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.restapi.guicemodule.RequestModule;
import com.restapi.properties.RequestProperties;
import com.restapi.requests.PostRequest;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PostRequestTests {
    private RequestProperties requestProperties;
    private PostRequest request;

    @BeforeEach
    public void beforeTest(){
        requestProperties = new RequestProperties();
        Injector injector = Guice.createInjector(new RequestModule());
        request = injector.getInstance(PostRequest.class);
    }

    @Test
    public void sendGetRequest() {
        requestProperties.setUrlEndPoint("/api/users");
        requestProperties.setReqHeaders("Content-Type:application/json");
        requestProperties.setReqBody("{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"leader\"\n" +
                "}");
        Response response = request.postRequest(requestProperties);
        System.out.println(response.getStatusCode());
        Assertions.assertTrue(response.getStatusCode()==201, "Response Status Code :: "+response.getStatusCode());
    }
}

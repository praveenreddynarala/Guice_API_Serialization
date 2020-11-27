package com.reqres.tests;

import com.google.gson.Gson;
import com.reqres.pojo.Datum;
import com.reqres.pojo.Example;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallingAPI {
    public static void main(String[] arg) {
        Response response = RestAssured.given()
                .contentType("application/json")
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response();

        String bodyString = response.body().asString();

        Gson gsonObj = new Gson();
        Example exampleObj = gsonObj.fromJson(bodyString, Example.class);
        //odbc
        //ArrayList/Map
        Map<Object, Object> mapObj = new HashMap<Object, Object>();
        mapObj.put("id", "7");
        mapObj.put("email", "michael.lawson@reqres.in");

        List<Datum> listObj = exampleObj.getData();
        for (Datum obj :
             listObj) {
            obj.getId().equals(mapObj.get("id").toString());
        }
        System.out.println(exampleObj.getData().get(0).getId());
        System.out.println(exampleObj);
    }
}

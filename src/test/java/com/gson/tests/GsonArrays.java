package com.gson.tests;

import com.google.gson.Gson;
import com.gson.pojo.Users;
import io.restassured.RestAssured;

public class GsonArrays {
    public static void main(String[] arg) {
        deserialization();
    }

    private static void deserialization(){
        Users usersObj = new Gson().fromJson(getResponse(), Users.class);
        usersObj.getData().stream().forEach((c) -> System.out.println("ID :: "+c.getId()+" Avatar :: "+c.getAvatar()
                +" Email :: "+c.getEmail()+ " First Name :: "+c.getFirst_name()+"  Last Name :: "+c.getLast_name()));
    }

    private static String getResponse(){
        return RestAssured.given()
                .contentType("application/json")
                .get("https://reqres.in/api/users?page=2")
                .then()
                .extract()
                .response().body().asString();
    }
}

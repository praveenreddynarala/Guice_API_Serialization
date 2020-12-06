package com.gson.tests;

import com.expose.pojo.ExposeUserProperties;
import com.google.gson.GsonBuilder;

public class ExposeJavaPropertiesTest {
    public static void main(String[] arg){
        serialization();
        deserialization();
    }

    private static void serialization() {
        ExposeUserProperties users = new ExposeUserProperties(
                "Praveen",
                "Narala",
                35,
                true
        );

        String jsonString = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(users);
        System.out.println(jsonString);
    }

    private static void deserialization() {
        String jsonString = "{\"name\":\"Praveen\",\"lastName\":\"Narala\",\"age\":null,\"isDeveloper\":false}";

        ExposeUserProperties users = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
                .create().fromJson(jsonString, ExposeUserProperties.class);
        System.out.println(users);
    }
}

package com.gson.tests;

import com.google.gson.GsonBuilder;
import com.serializetransient.pojo.TransientSerialization;

public class TransientSerializationTest {
    public static void main(String[] arg){
        serialization();
        deserialization();
    }

    private static void deserialization() {
        TransientSerialization users = new TransientSerialization(
                "Praveen",
                "Narala",
                35,
                true
        );

        String jsonString = new GsonBuilder().serializeNulls().create().toJson(users);
        System.out.println(jsonString);
    }

    private static void serialization() {
        String jsonString = "{\"name\":\"Praveen\",\"lastName\":\"Narala\",\"age\":null,\"isDeveloper\":false}";

        TransientSerialization users = new GsonBuilder().serializeNulls().create().fromJson(jsonString, TransientSerialization.class);
        System.out.println(users);
    }
}

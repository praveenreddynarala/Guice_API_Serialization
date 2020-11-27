package com.gson.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.serializednames.pojo.Users;

public class SerializedNamesTest {
    public static void main(String[] arg){
        serialization();
        deserialization();
    }

    private static void deserialization() {
        Users users = new Users(
                "Praveen",
                "Narala",
                null,
                false
        );

        String jsonString = new GsonBuilder().serializeNulls().create().toJson(users);
    }

    private static void serialization() {
        String jsonString = "{\"name\":\"Praveen\",\"last_name\":\"Narala\",\"age\":null,\"isDeveloper\":false}";

        Users users = new Gson().fromJson(jsonString, Users.class);
    }
}

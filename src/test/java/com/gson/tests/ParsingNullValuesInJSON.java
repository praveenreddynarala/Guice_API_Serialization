package com.gson.tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.parsingnulls.pojo.Users;

public class ParsingNullValuesInJSON {
    public static void main(String[] arg){
        serialization();
        deserialization();
    }

    private static void deserialization() {
        String jsonString = "{\"name\":\"Praveen\",\"last_name\":\"Narala\",\"age\":null}";

        Users users = new Gson().fromJson(jsonString, Users.class);
        System.out.println(users);
    }

    private static void serialization() {
        Users users = new Users(
                "Praveen",
                "Narala",
                null
        );

        Gson gson = new GsonBuilder().serializeNulls().create();
        String jsonString = gson.toJson(users);
        System.out.println(jsonString);
    }
}

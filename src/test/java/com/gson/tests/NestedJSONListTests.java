package com.gson.tests;

import com.google.gson.Gson;
import com.gson.pojo.NestedJSONList;

public class NestedJSONListTests {
    public static void main(String[] arg) {
        deserialization();
    }

    private static void deserialization() {
        String jsonFile = "{\"name\":\"Praveen\",\"last_name\":\"Narala\",\"address\":[{\"street\":\"Cotteralls\",\"house_number\":\"19 K D Towers\",\"Town\":\"Hemel Hempstead\"}],\"other_info\":[{\"info\":\"testing\"}],\"repo\":\"test\",\"details\":[]}";
        NestedJSONList nestedJSONListObj = new Gson().fromJson(jsonFile, NestedJSONList.class);
        System.out.println(nestedJSONListObj);
    }
}

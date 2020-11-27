package com.gson.tests;

import com.google.gson.Gson;
import com.gson.pojo.NestedJSONList;
import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;

import java.io.File;
import java.io.InputStream;

public class NestedJSONListTests {
    public static void main(String[] arg) {
        deserialization();
    }

    private static void deserialization() {
        String jsonFile = "{\"name\":\"Praveen\",\"last_name\":\"Narala\",\"address\":[{\"street\":\"Cotteralls\",\"house_number\":\"19 K D Towers\",\"Town\":\"Hemel Hempstead\"}],\"other_info\":[{\"info\":\"testing\"}],\"repo\":\"test\",\"details\":[]}";
        NestedJSONList nestedJSONListObj = new Gson().fromJson(jsonFile, NestedJSONList.class);
    }
}

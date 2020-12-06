package com.restapi.serialization;

import com.google.gson.reflect.TypeToken;
import com.google.inject.Inject;
import com.restapi.interfaces.DataSerialization;

public class Deserialize {

    DataSerialization d;

    @Inject
    public Deserialize(DataSerialization d){ this.d = d; }

    public <E> E tokenTypeDeserialization(String jsonStringResponse, TypeToken<E> typeToken, Boolean gsonBuilder, Boolean parseNullValues){
        return d.tokenTypeDeserialization(jsonStringResponse, typeToken, gsonBuilder, parseNullValues);
    }

    public <E> E deserializeResponse(String jsonStringResponse, Class<E> klass){
        return d.deserializeResponse(jsonStringResponse, klass);
    }

    public <E> E parseJSON(Class<E> klass, String jsonFileLocation){
        return d.parseJSON(klass, jsonFileLocation);
    }

}

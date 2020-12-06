package com.restapi.interfaces;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.inject.ImplementedBy;
import com.restapi.builder.SerializeBuilder;

import java.io.IOException;

@ImplementedBy(SerializeBuilder.class)
public interface DataSerialization {

    <E> E parseJSON(Class<E> klass, String jsonFileLocation);
    String serializeJSONResponse(Object src);
    <E> E tokenTypeDeserialization(String json, TypeToken<E> typeToken, Boolean gsonBuilder, Boolean parseNullValues) throws JsonSyntaxException;
    <E> E deserializeResponse(String json, Class<E> klass) throws JsonSyntaxException;
}

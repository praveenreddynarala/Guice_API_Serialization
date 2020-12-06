package com.restapi.builder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.restapi.interfaces.DataSerialization;

import javax.inject.Singleton;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@Singleton
public class SerializeBuilder implements DataSerialization {

    private Gson gsonObj = null;
    private BufferedReader bufferedReader;

    @Override
    public <E> E parseJSON(Class<E> klass, String fileName) {
        E obj = null;
        gsonObj = new GsonBuilder().serializeNulls().create();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
             InputStreamReader streamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
             BufferedReader reader = new BufferedReader(streamReader)) {

            obj = gsonObj.fromJson(reader, klass);

        }catch (FileNotFoundException e){
            System.exit(1);
        }catch (IOException e){
            System.exit(1);
        }

        return obj;
    }

    @Override
    public String serializeJSONResponse(Object src) {

        return new GsonBuilder().serializeNulls().create().toJson(src);

    }

    @Override
    public <E> E tokenTypeDeserialization(String jsonStringResponse, TypeToken<E> typeToken, Boolean gsonBuilder, Boolean parseNullValues) throws JsonSyntaxException {

        Type githubRepo = typeToken.getType(); //If the root element is List [], should use TypeToken
        return new Gson().fromJson(jsonStringResponse, githubRepo);
    }

    @Override
    public <E> E deserializeResponse(String jsonStringResponse, Class<E> klass) throws JsonSyntaxException {
        return new Gson().fromJson(jsonStringResponse, klass);
    }

}

package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.restapi.createclient.Client;
import com.restapi.requestbuilder.RequestBuilder;

public class RequestModule extends AbstractModule {

    @Override
    public void configure(){
        bind(String.class)
                .annotatedWith(Names.named("Server Url"))
        .toInstance("https://reqres.in");
        bind(Client.class).to(RequestBuilder.class);
    }

}

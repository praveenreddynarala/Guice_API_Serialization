package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.restapi.createclient.Client;
import com.restapi.requestbuilder.RequestBuilder;

public class RequestModule extends AbstractModule {

    @Override
    public void configure(){
//        binder().install(new FactoryModuleBuilder()
//        .implement(Client.class, RequestBuilder.class)
//        .build(RequestPropertiesFactory.class));

        bind(Client.class).to(RequestBuilder.class);
    }

}

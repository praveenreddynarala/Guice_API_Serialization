package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.restapi.createclient.Client;
import com.restapi.properties.RequestProperties;
import com.restapi.properties.RequestPropertiesFactory;
import com.restapi.requestbuilder.RequestBuilder;

public class GetRequestModule extends AbstractModule {

    @Override
    public void configure(){
//        binder().install(new FactoryModuleBuilder()
//        .implement(Client.class, RequestBuilder.class)
//        .build(RequestPropertiesFactory.class));
        bind(Client.class).to(RequestBuilder.class);
    }

}

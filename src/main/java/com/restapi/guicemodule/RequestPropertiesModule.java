package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.restapi.properties.RequestProperties;
import com.restapi.properties.RequestPropertiesFactory;

public class RequestPropertiesModule extends AbstractModule {

    @Override
    protected void configure() {
//        bind(RequestProperties.class).toInstance(new RequestProperties());
    }
}

package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.restapi.builder.SerializeBuilder;
import com.restapi.interfaces.DataSerialization;

public class SerializationModule extends AbstractModule {

    @Override
    protected void configure(){
        bind(DataSerialization.class).to(SerializeBuilder.class).in(Singleton.class);
    }

}

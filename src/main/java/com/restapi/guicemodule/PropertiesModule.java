package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {

    @Override
    protected void configure(){
        try{
            Properties properties = new Properties();
            properties.load(this.getClass().getClassLoader().getResourceAsStream("Application.properties"));
            Names.bindProperties(binder(), properties);
        }catch (IOException e){
            System.exit(1);
        }
    }
}

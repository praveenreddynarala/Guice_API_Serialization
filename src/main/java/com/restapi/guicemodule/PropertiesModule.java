package com.restapi.guicemodule;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

import java.io.IOException;
import java.util.Properties;

public class PropertiesModule extends AbstractModule {

    @Override
    protected void configure(){
        Names.bindProperties(binder(), loadProperties("GitHub.properties"));

    }

    private Properties loadProperties(String propoeryFileName){
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream(propoeryFileName));

        }catch (IOException e){
            System.exit(1);
        }
        return properties;
    }
}

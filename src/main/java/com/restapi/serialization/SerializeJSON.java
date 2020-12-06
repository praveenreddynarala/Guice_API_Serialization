package com.restapi.serialization;

import com.google.inject.Inject;
import com.restapi.interfaces.DataSerialization;

public class SerializeJSON {

    private DataSerialization d;

    @Inject
    public SerializeJSON(DataSerialization d){ this.d = d;}

    public String serialization(Object src){
        return d.serializeJSONResponse(src);
    }

}

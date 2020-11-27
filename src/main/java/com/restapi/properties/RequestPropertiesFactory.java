package com.restapi.properties;

import com.restapi.createclient.Client;

public interface RequestPropertiesFactory {
    Client create(String reqHeaders, String reqBody, String baseUrl, String userName, String psw, String token, String secretKey, String clientId);
}

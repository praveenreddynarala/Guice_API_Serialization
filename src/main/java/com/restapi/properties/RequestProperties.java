package com.restapi.properties;

import com.google.inject.assistedinject.Assisted;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

public class RequestProperties{

    private Map<String, String> reqHeaders = new HashMap<>();
    private String reqBody;
    private String urlEndPoint;
    private String userName;
    private String psw;
    private String token;
    private String secretKey;
    private String clientId;

    public Map<String, String> getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(String headersString) {
        if(headersString == null || headersString.trim() ==""){
            return;
        }
        String[] headerStr = headersString.trim().split(",");
        String[] headerEntry = null;
        for (int i = 0; i < headerStr.length; i++){
            headerEntry = headerStr[i].trim().split(":");
            if(headerEntry.length != 2){
                continue;
            }
            this.reqHeaders.put(headerEntry[0].trim(), headerEntry[1].trim());
        }
    }

    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        if (reqBody == null) {
            reqBody = "";
        }
        this.reqBody = reqBody.trim();
    }

    public String getUrlEndPoint() {
        return urlEndPoint;
    }

    public void setUrlEndPoint(String urlEndPoint) {
        this.urlEndPoint = urlEndPoint;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}

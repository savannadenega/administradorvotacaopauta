package com.sicredi.administradorvotacaopauta.client;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class UserInfoClient {

    final String USER_INFO_URL = "https://user-info.herokuapp.com/users/%s";
    JSONParser parser = new JSONParser();

    public boolean validarAssociadoPodeVotar(String associadoCpf) throws Exception {

        HttpUriRequest request = new HttpGet(String.format(USER_INFO_URL, associadoCpf));
        HttpResponse response = HttpClientBuilder.create().build().execute(request);

        String responseUserInfo = EntityUtils.toString(response.getEntity());
        JSONObject json = (JSONObject) parser.parse(responseUserInfo);
        String status = (String) json.get("status");
        return !status.equalsIgnoreCase("ABLE_TO_VOTE");
    }

}

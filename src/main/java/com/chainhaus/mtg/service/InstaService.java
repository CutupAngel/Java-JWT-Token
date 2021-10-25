package com.chainhaus.mtg.service;

import com.chainhaus.mtg.util.Constants;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * Created by Asad Sarwar on 9/9/2021.
 */
@Service
public class InstaService {

    final Logger logger = LoggerFactory.getLogger("InstaService");

//    OkHttpClient client = new OkHttpClient();
//    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private String appId;
    private String appSecret;
    private String succesRedirectUrl;

    public InstaService(@Autowired Environment environment){
        appId = environment.getProperty(Constants.INSTA_APP_ID, String.class);
        appSecret = environment.getProperty(Constants.INSTA_APP_SECRET, String.class);
        succesRedirectUrl = environment.getProperty(Constants.MTG_INSTA_SUCCESS_REDIRECT_URL, String.class);
    }

    public String getUserToken(String code) throws IOException {

        logger.info("Insta app details");
        logger.info(appId);
        logger.info(appSecret);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("client_id", appId);
        map.add("client_secret", appSecret);
        map.add("grant_type", "authorization_code");
        map.add("redirect_uri", succesRedirectUrl);
        map.add("code", code);
        map.add("email", "first.last@example.com");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("https://api.instagram.com/oauth/access_token", request, String.class);
        logger.info(response.getBody());
//        return new JSONObject(response.getBody());
        return response.getBody();

//        RequestBody body =
//                new FormBody.Builder()
//                    .add("client_id",appId)
//                    .add("client_secret",appSecret)
//                    .add("grant_type","authorization_code")
//                    .add("redirect_uri",succesRedirectUrl)
//                    .add("code",code)
//                    .build();
//
//        Request request = new Request.Builder()
//                .url("https://api.instagram.com/oauth/access_token")
//                .post(body)
//                .build();
//        try (Response response = client.newCall(request).execute()) {
//            return new JSONObject(response.body().string());
//        }
    }
}

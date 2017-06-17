package com.linguo.sharecenter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by bin on 17/06/2017.
 */
@Service
public class UserUpdateService {
    public static final String INACTIVE_USER_UPDATE_FUNCTION = "functions/updateOldUserScore";
    @Value("${parse.server.url}")
    private String parseUrl;

    @Value("${parse.app.id}")
    private String parseId;

    @Value("${parse.app.key}")
    private String parseKey;


    private RestTemplate restTemplate = new RestTemplate() ;

    @Scheduled(cron = "0 0 6 * * *") //everyday 6 am
    public String updateInactiveUser(){
        String path = parseUrl + INACTIVE_USER_UPDATE_FUNCTION;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-Parse-Application-Id", parseId);
        headers.set("X-Parse-Master-Key", parseKey);

        HttpEntity<String> entity = new HttpEntity<String>("",headers);
        ResponseEntity<String> response = restTemplate.exchange(path, HttpMethod.POST, entity, String.class);
        System.out.println("updateInactiveUser response: " + response);
        return response.getBody();
    }
}

package com.linguo.sharecenter.service;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.googleapis.auth.oauth2.GoogleRefreshTokenRequest;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * Created by bin on 16/10/2016.
 */
@Service
public class GoogleAuthService {
    public static final int MAX_TIME_KEEP_TOKEN = 45 * 60 * 1000;
    @Value("${google.youtube.refreshToken}")
    private String refreshToken;

    @Value("${google.youtube.clientId}")
    private String clientId;

    @Value("${google.youtube.clientSecret}")
    private String clientSecret;

    private Long lastRefreshTime = 0L;
    private String accessToken;

    public String getAccessToken() throws IOException {
        Long currentTime = System.currentTimeMillis();
        if(currentTime-lastRefreshTime> MAX_TIME_KEEP_TOKEN || accessToken == null){
            accessToken = refreshAccessToken();
            lastRefreshTime = currentTime;
        }
        return  accessToken;
    }


    private String refreshAccessToken() throws IOException {
        try {
            TokenResponse response =
                    new GoogleRefreshTokenRequest(new NetHttpTransport(), new JacksonFactory(),
                            refreshToken, clientId, clientSecret).execute();
            System.out.println("Refreshed Access token: " + response.getAccessToken());

            return response.getAccessToken();
        } catch (TokenResponseException e) {
            if (e.getDetails() != null) {
                System.err.println("Error: " + e.getDetails().getError());
                if (e.getDetails().getErrorDescription() != null) {
                    System.err.println(e.getDetails().getErrorDescription());
                }
                if (e.getDetails().getErrorUri() != null) {
                    System.err.println(e.getDetails().getErrorUri());
                }
            } else {
                System.err.println(e.getMessage());
            }
            throw new RuntimeException("failed to refresh AccessToken", e);
        }
    }
}

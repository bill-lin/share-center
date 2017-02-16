/*
 * Copyright 2014 Google Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.linguo.sharecenter.purchase;

import com.amazonaws.util.StringInputStream;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.AndroidPublisherScopes;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Collections;

/**
 * Helper class to initialize the publisher APIs client library.
 * <p>
 * Before making any calls to the API through the client library you need to
 * call the {@link AndroidPublisherFactory#init(String)} method. This will run
 * all precondition checks for for client id and secret setup properly in
 * resources/client_secrets.json and authorize this client against the API.
 * </p>
 */
@Component
public class AndroidPublisherFactory {
    @Value("${google.app.name}")
    private String appName;

    @Value("${google.app.key.json}")
    private String jsonKey;

    private  final Log log = LogFactory.getLog(AndroidPublisherFactory.class);


    /** Global instance of the JSON factory. */
    private  final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private  HttpTransport HTTP_TRANSPORT;


    private  void newTrustedTransport() throws GeneralSecurityException,
            IOException {
        if (null == HTTP_TRANSPORT) {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        }
    }

    protected AndroidPublisher createAndroidPublisherUsingJsonKey() throws IOException, GeneralSecurityException {

        // Authorization.
        GoogleCredential credential = authorizeWithServiceAccountUsingJsonKey();
        credential = credential.createScoped(Collections.singleton(AndroidPublisherScopes.ANDROIDPUBLISHER));

        // Set up and return API client.
        return new AndroidPublisher.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(appName)
                .build();
    }

    private  GoogleCredential authorizeWithServiceAccountUsingJsonKey()
            throws GeneralSecurityException, IOException {
        newTrustedTransport();
        InputStream inputStream = new StringInputStream(jsonKey);
        return GoogleCredential.fromStream(inputStream);
    }
}

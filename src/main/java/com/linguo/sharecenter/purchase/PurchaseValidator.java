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

import com.google.api.services.androidpublisher.AndroidPublisher;
import com.google.api.services.androidpublisher.model.ProductPurchase;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Lists all the apks for a given app.
 */
@Component
public class PurchaseValidator {

    @Autowired
    private AndroidPublisherFactory androidPublisherFactory;

    @Value("${google.app.package}")
    private String appPackageName;

    private static final Log log = LogFactory.getLog(PurchaseValidator.class);

    public Boolean validate(String productKey, String token, String user) {
        // Create the API service.
        try {
            final AndroidPublisher service = androidPublisherFactory.createAndroidPublisherUsingJsonKey();
            AndroidPublisher.Purchases.Products.Get result = service.purchases().products().get(appPackageName, productKey, token);
            ProductPurchase purchase = result.execute();
            log.info("User:" + user + " - verified productKey:" + productKey + ", token: " + token);
            log.info("PurchaseState:" + purchase.getPurchaseState() + " - ConsumptionState:" + purchase.getConsumptionState() + purchase.getDeveloperPayload() + " - " + new Date(purchase.getPurchaseTimeMillis()));
            return purchase.getPurchaseState() == 0;
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("User:" + user + " - could not verify productKey:" + productKey + ", token: " + token);
            return false;
        }
    }
}

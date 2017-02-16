package com.linguo.sharecenter.controller;

import com.linguo.sharecenter.purchase.PurchaseValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin on 05/09/2016.
 */
@RestController
public class GoogleIAPValidateController {

    @Autowired
    private PurchaseValidator purchaseValidator;


    @RequestMapping(path = "/iap/validation", method = RequestMethod.GET )
    public Boolean validate(@RequestParam(value = "product") String product,
                            @RequestParam(value = "token") String token,
                            @RequestParam(value = "user") String user)  {
       return  purchaseValidator.validate(product, token, user);
    }

}

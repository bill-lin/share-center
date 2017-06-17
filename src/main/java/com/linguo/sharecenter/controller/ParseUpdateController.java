package com.linguo.sharecenter.controller;

import com.linguo.sharecenter.service.UserUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin on 17/06/2017.
 */
@RestController
public class ParseUpdateController {
    @Autowired
    private UserUpdateService userUpdateService;

    @RequestMapping(path = "/updateUser", method = RequestMethod.GET )
    public String updateUser()  {
        return  userUpdateService.updateInactiveUser();
    }
}

package com.linguo.sharecenter.controller;

import com.linguo.sharecenter.dao.UserRepository;
import com.linguo.sharecenter.exception.AccessTokenError;
import com.linguo.sharecenter.model.User;
import com.linguo.sharecenter.service.GoogleAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by bin on 05/09/2016.
 */
@RestController
public class VideoUploadAccessTokenController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(path = "/video/youtube/accesstoken", method = RequestMethod.GET )
    public String searchImages(@RequestParam(value = "username") String username,
                               @RequestParam(value = "email") String email) throws AccessTokenError {
        try {
            User user = userRepository.findByUserNameAndEmail(username, email);
            if(user != null) {
                return googleAuthService.getAccessToken();
            }else {
                throw new AccessTokenError ("invalid user");
            }
        }
        catch (  IOException e) {
            throw new AccessTokenError(e.getMessage(),e.getCause());
        }
    }

}

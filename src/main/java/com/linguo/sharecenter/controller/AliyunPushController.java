package com.linguo.sharecenter.controller;

import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.push.model.v20160801.PushResponse;
import com.linguo.sharecenter.aliyunpush.AliyunPushService;
import com.linguo.sharecenter.aliyunpush.PushMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by bin on 01/09/2017.
 */
@RestController
public class AliyunPushController {
    @Autowired
    private AliyunPushService aliyunPushService;

    @RequestMapping(path = "/message/push", method = RequestMethod.POST )
    public PushResponse push(@RequestBody PushMessage pushMessage) throws ClientException {
        return  aliyunPushService.pushMessage(pushMessage);
    }

}
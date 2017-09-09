package com.linguo.sharecenter.aliyunpush;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.aliyuncs.push.model.v20160801.PushRequest;
import com.aliyuncs.push.model.v20160801.PushResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by bin on 31/08/2017.
 */
@Service
public class AliyunPushService {
    @Value("${aliyun.push.app.regionId}")
    private String regionId;

    @Value("${aliyun.push.app.accessKeyId}")
    private String accessKeyId ;

    @Value("${aliyun.push.app.accessKeySecret}")
    private String accessKeySecret ;

    @Value("${aliyun.push.app.id}")
    private String appKey;


    public PushResponse pushMessage(PushMessage pushMessage) throws ClientException {
        System.out.println("Reveived push request: "+ pushMessage);
        DefaultAcsClient client = createClient();
        // 推送目标
        PushRequest pushRequest = createRequest(pushMessage);
        try {
            PushResponse pushResponse = client.getAcsResponse(pushRequest);
            System.out.printf("RequestId: %s, MessageID: %s\n", pushResponse.getRequestId(), pushResponse.getMessageId());
            return pushResponse;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Could not send push message due to "+ e.getMessage(), e);
        }

    }




    private DefaultAcsClient createClient(){
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyId, accessKeySecret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return  client;
    }

    private PushRequest createRequest(PushMessage pushMessage) {
        PushRequest pushRequest = new PushRequest();
        pushRequest.setAppKey(new Long(appKey));

        pushRequest.setTarget(pushMessage.getTargetType());
        pushRequest.setTargetValue(pushMessage.getTargetValue());
        pushRequest.setDeviceType(pushMessage.getDeviceType());
        pushRequest.setPushType(pushMessage.getPushType());
        pushRequest.setTitle(pushMessage.getMessageTitle());
        pushRequest.setBody(pushMessage.getMessageBody());

        // 推送控制
//        Date pushDate = new Date(System.currentTimeMillis()); // 30秒之间的时间点, 也可以设置成你指定固定时间
//        String pushTime = ParameterHelper.getISO8601Time(pushDate);
//        pushRequest.setPushTime(pushTime); // 延后推送。可选，如果不设置表示立即推送
//        String expireTime = ParameterHelper.getISO8601Time(new Date(System.currentTimeMillis() + 24 * 3600 * 1000)); // 1 days 后消息失效, 不会再发送
//        pushRequest.setExpireTime(expireTime);
        pushRequest.setStoreOffline(false); // 离线消息是否保存,若保存, 在推送时候，用户即使不在线，下一次上线则会收到


        // 推送配置: Android
        pushRequest.setAndroidNotifyType("BOTH");//通知的提醒方式 "VIBRATE" : 震动 "SOUND" : 声音 "BOTH" : 声音和震动 NONE : 静音
//        pushRequest.setAndroidNotificationBarType(1);//通知栏自定义样式0-100
//        pushRequest.setAndroidNotificationBarPriority(1);//通知栏自定义样式0-100
//        pushRequest.setAndroidOpenType("Activity"); //点击通知后动作 "APPLICATION" : 打开应用 "ACTIVITY" : 打开AndroidActivity "URL" : 打开URL "NONE" : 无跳转
//        pushRequest.setAndroidOpenUrl("http://www.aliyun.com"); //Android收到推送后打开对应的url,仅当AndroidOpenType="URL"有效
//        pushRequest.setAndroidActivity("com.alibaba.push2.demo.XiaoMiPushActivity"); // 设定通知打开的activity，仅当AndroidOpenType="Activity"有效
//        pushRequest.setAndroidMusic("default"); // Android通知音乐
//        pushRequest.setAndroidPopupActivity("com.ali.demo.PopupActivity");//设置该参数后启动辅助弹窗功能, 此处指定通知点击后跳转的Activity（辅助弹窗的前提条件：1. 集成第三方辅助通道；2. StoreOffline参数设为true）
        pushRequest.setAndroidPopupTitle("Popup Title");
        pushRequest.setAndroidPopupBody("Popup Body");
        pushRequest.setAndroidExtParameters("{\"k1\":\"android\",\"k2\":\"v2\"}"); //设定通知的扩展属性。(注意 : 该参数要以 json map 的格式传入,否则会解析出错)

        return pushRequest;
    }
}

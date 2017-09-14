package com.gistandard.transport.tools.util;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author: xgw
 * @ClassName: ImPushUtil
 * @Date: 2016/7/15
 * @Description:
 */
public class ImPushUtil {
    private static final Logger logger = LoggerFactory.getLogger(ImPushUtil.class);

    @Value("#{customerOrderIM.imContentSystemUrl}")
    private static String imContentSystemUrl;
    private static Properties config = new Properties();
    static {
        try {
            InputStream is =null;
            if("develop".equals(SpringContextUtil.getApplicationContext().getEnvironment().getDefaultProfiles()[0])){
                is =    ImPushUtil.class.getClassLoader().getResourceAsStream("develop/customerOrderIM_develop.properties");
            }
            if("preRelease".equals(SpringContextUtil.getApplicationContext().getEnvironment().getDefaultProfiles()[0])){
                is =    ImPushUtil.class.getClassLoader().getResourceAsStream("preRelease/customerOrderIM_preRelease.properties");
            }
            if("production".equals(SpringContextUtil.getApplicationContext().getEnvironment().getDefaultProfiles()[0])){
                is =    ImPushUtil.class.getClassLoader().getResourceAsStream("production/customerOrderIM_production.properties");
            }
            if(is !=null) {
                config.load(is);
                is.close();
            }
            if(imContentSystemUrl == null){
                imContentSystemUrl = config.getProperty("imContentSystemUrl");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * IM系统消息推送
     * @param sysCode      当前系统的 系统号码
     * @param acctUsername 登录账号
     * @param sysTag       需要推送到的 IM系统标示
     * @param mapObjecte   推送的Body体
     * @param remindCode   业务标识,定义的消息类别
     * @return
     */
    public static String sendMessageIM(String sysCode, String acctUsername, String sysTag, String remindCode, Map<String, String> mapObjecte) {
        String url = imContentSystemUrl;
        Map<String, Object> req = createRequest(sysCode, acctUsername, sysTag, remindCode,mapObjecte);
        logger.info("req={}", JSONObject.toJSONString(req));
        String resultStr = HttpClientUtil.URLPost(url, req,
                HeadAuthentication.setIMHead(sysTag));
        logger.info("result={}", resultStr);
        return resultStr;
    }

    /**
     * 创建IM推送消息内容
     *
     * @param sysCode      当前系统的 系统号码
     * @param acctUsername 登录账号
     * @param sysTag       需要推送到的 IM系统标示
     * @param mapObjecte   推送的Body体
     * @param remindCode   业务标识,定义的消息类别
     * @return
     */
    private static Map<String, Object> createRequest(String sysCode, String acctUsername, String sysTag, String remindCode, Map<String, String> mapObjecte) {
        Map<String, Object> req = new HashMap<>();
        req.put("sysCode", sysCode);
        req.put("platAccounts", acctUsername);
        req.put("sysTag", sysTag);
        req.put("remindCode", remindCode);
        req.put("body", JSONObject.toJSON(mapObjecte).toString());
        return req;
    }

}

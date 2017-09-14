package com.gistandard.transport.oauth2.bean;


import com.gistandard.platform.pojo.res.AppBasePagerResult;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.io.IOException;

/**
 * 转换异常信息为自定义对象AppBasePagerResult
 * Created by shenzhijun on 2016/10/10.
 */
public class OauthJsonMessageConverter extends MappingJackson2HttpMessageConverter {
    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        super.writeInternal(transformObject(object), outputMessage);
    }

    protected Object transformObject(Object object) {
        AppBasePagerResult response = new AppBasePagerResult();
        response.setRetCode(-1);
        if(object instanceof InvalidTokenException){
            response.setRetCode(40001);
            response.setRetMsg(((OAuth2Exception) object).getMessage());
        }else if(object instanceof OAuth2Exception) {
            response.setRetMsg(((OAuth2Exception) object).getMessage());
        }
        return response;
    }
}

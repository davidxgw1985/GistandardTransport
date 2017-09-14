package com.gistandard.transport.app.system.common.spring;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.base.bean.app.ValidTokenMark;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.oauth2.SecurityUser;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * 在请求到达controller之前获取用户信息AppLoginInfo放入请求中
 * Created by m on 2016/11/8.
 */
public class MyHttpMessageConverter extends FastJsonHttpMessageConverter {
    @Override
    public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        Object read = super.read(type, contextClass, inputMessage);
        if (read instanceof AppBaseRequest){
            AppBaseRequest request = (AppBaseRequest) read;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                SecurityUser<AppLoginInfo> principal = (SecurityUser<AppLoginInfo>) authentication.getPrincipal();
                if (principal != null) {
                    AppLoginInfo appLoginInfo = principal.getInfo();
                    if (appLoginInfo != null) {
                        request.setAccountId(appLoginInfo.getAccountId());
                        request.setAcctUsername(appLoginInfo.getAcctUsername());
                        request.setAppLoginInfo(appLoginInfo);
                    }
                }
            }
            if (read instanceof ValidTokenMark) {
                if (request.getAccountId() == null){
                    throw new HttpMessageNotReadableException(SysResult.invalidTokenMark.getName());
                }
            }
        }
        return read;
    }

}

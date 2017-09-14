package com.gistandard.transport.app.system.common.spring;

import org.springframework.cache.interceptor.KeyGenerator;

import java.lang.reflect.Method;

/**
 * Created by m on 2016/11/18.
 */
public class MyKeyGenerator implements KeyGenerator {
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder sb = new StringBuilder();
        sb.append(target.getClass().getName());
        sb.append(".");
        sb.append(method.getName());
        sb.append("(");
        for (Object obj : params) {
            sb.append(obj != null ? obj.toString() : "");
            sb.append(",");
        }
        sb.append(")");
        return sb.toString();
    }
}

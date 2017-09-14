package com.gistandard.transport.gps.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

import java.io.Serializable;

/**
 * Created by m on 2016/9/30.
 */
public class TokenReq extends AppBaseRequest implements Serializable {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}

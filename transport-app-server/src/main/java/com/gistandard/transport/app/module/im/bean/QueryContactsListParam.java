package com.gistandard.transport.app.module.im.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * Created by yujie on 2016/9/30.
 */
public class QueryContactsListParam extends AppBasePagerRequest{

    private String param;//查询条件

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}

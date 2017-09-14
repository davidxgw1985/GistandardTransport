package com.gistandard.transport.app.module.account.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by m on 2016/10/7.
 */
public class QueryAccountByTelephoneResult extends AppBaseResult {

    public QueryAccountByTelephoneResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

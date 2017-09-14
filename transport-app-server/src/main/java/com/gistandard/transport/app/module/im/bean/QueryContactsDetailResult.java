package com.gistandard.transport.app.module.im.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by yujie on 2016/9/30.
 */
public class QueryContactsDetailResult extends AppBaseResult {

    private QueryContactsDetailData data;

    public QueryContactsDetailData getData() {
        return data;
    }

    public void setData(QueryContactsDetailData data) {
        this.data = data;
    }
}

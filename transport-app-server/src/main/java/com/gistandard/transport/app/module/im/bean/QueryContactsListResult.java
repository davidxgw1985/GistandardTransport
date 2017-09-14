package com.gistandard.transport.app.module.im.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;

/**
 * Created by yujie on 2016/9/30.
 */
public class QueryContactsListResult extends AppBasePagerResult {

    private List data;

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }
}

package com.gistandard.transport.app.module.applytobe.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.system.common.bean.ResultBean;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/3/16.
 */
public class AppBaseResultRes extends AppBaseResult implements Serializable {
    private ResultBean data;

    public ResultBean getData() {
        return data;
    }

    public void setData(ResultBean data) {
        this.data = data;
    }
}

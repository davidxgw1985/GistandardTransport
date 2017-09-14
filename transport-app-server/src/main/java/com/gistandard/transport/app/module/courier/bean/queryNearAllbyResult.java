package com.gistandard.transport.app.module.courier.bean;

import com.gistandard.platform.pojo.res.AppBasePagerResult;

import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/10/7.
 */
public class queryNearAllbyResult  extends AppBasePagerResult {
    private Map<String, List> data;

    public Map<String, List> getData() {
        return data;
    }

    public void setData(Map<String, List> data) {
        this.data = data;
    }
}

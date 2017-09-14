package com.gistandard.transport.app.dubbo.order.bean;


import com.gistandard.transport.app.dubbo.pojo.res.MsDubboResBean;

import java.util.List;

/**
 * @author: xgw
 * @ClassName: BatchAssignOrderResult
 * @Date: 2017/1/24
 * @Description:
 */
public class BatchAssignOrderResult extends MsDubboResBean {
    private List<AssignOrderResBean> data;

    public List<AssignOrderResBean> getData() {
        return data;
    }

    public void setData(List<AssignOrderResBean> data) {
        this.data = data;
    }
}

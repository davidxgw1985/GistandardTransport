package com.gistandard.transport.app.dubbo.pojo.res;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.io.Serializable;

/**
 * Created by yujie on 2017/3/1.
 */
public class MsDubboPagerResBean extends MsDubboResBean implements Serializable {

    private static final long serialVersionUID = 3075814540735771161L;

    public MsDubboPagerResBean(MsDubboReqBean appBaseRequest) {
        super(appBaseRequest);
    }

    public MsDubboPagerResBean() {
        super();
    }

    private int recordCount;//总条数

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }
}

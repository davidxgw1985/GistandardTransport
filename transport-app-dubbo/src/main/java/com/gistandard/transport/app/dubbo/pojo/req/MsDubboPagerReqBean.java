package com.gistandard.transport.app.dubbo.pojo.req;

import java.io.Serializable;

/**
 * Created by yujie on 2017/3/1.
 */
public class MsDubboPagerReqBean extends MsDubboReqBean implements Serializable {
    private static final long serialVersionUID = -7452469295158995023L;

    private int startRecord;//开始条数

    private int pageSize = 10;//每页条数(默认10)

    public int getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(int startRecord) {
        this.startRecord = startRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}

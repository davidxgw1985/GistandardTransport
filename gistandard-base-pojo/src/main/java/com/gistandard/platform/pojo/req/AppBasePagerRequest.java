package com.gistandard.platform.pojo.req;

import java.io.Serializable;

/**
 * Created by yujie on 2016/9/30.
 */
public class AppBasePagerRequest extends  AppBaseRequest implements Serializable{

    private static final long serialVersionUID = -8470260932709668862L;
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

package com.gistandard.transport.base.bean.app;

/**
 * @author: xgw
 * @ClassName: BaseReqPageBean
 * @Date: 2015/12/24
 * @Description: 列表请求基类
 */
public class BaseReqPageBean extends BaseReqBean{
    private static final long serialVersionUID = 9061177493319866440L;

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

package com.gistandard.transport.system.common.bean.table;

import com.gistandard.transport.system.common.bean.BaseReqParamBean;

/**
 * @author yujie
 * @ClassName TableBaseBean
 * @Description
 * @Version 1.0
 * @Date 2016-01-11
 */
public class TableBaseBean extends BaseReqParamBean {
    private  int offset = 0;

    private int limit = 10;

    private int currentPage = 1;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

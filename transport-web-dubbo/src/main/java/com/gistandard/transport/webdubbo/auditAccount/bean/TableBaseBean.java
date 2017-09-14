package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;

/**
 * 列表分页参数
 */
public class TableBaseBean implements Serializable {

    private int start = 0;

    private int limit = 10;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return this.limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}

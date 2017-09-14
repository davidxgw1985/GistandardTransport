package com.gistandard.transport.webdubbo.auditAccount.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 列表结果
 */
public class TableResultBean implements Serializable {

    private List rows;

    private int total;

    public List getRows() {
        return this.rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

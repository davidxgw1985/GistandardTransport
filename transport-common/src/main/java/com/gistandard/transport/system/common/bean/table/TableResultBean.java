package com.gistandard.transport.system.common.bean.table;

import com.gistandard.transport.system.common.bean.BaseReturnValueBean;

import java.util.List;

/**
 * @author yujie
 * @ClassName TableResultBean
 * @Description
 * @Version 1.0
 * @Date 2016-01-11
 */
public class TableResultBean extends BaseReturnValueBean {

    private List rows;

    private int total;

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}

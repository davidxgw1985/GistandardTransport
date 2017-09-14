package com.gistandard.transport.app.dubbo.pojo.res;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class TableResultDubboBean extends BaseReturnDubboBean implements Serializable {

    private static final long serialVersionUID = 3075814540735571261L;

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

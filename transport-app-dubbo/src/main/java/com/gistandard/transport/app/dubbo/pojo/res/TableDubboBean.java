package com.gistandard.transport.app.dubbo.pojo.res;

import java.io.Serializable;

/**
 * Created by zhuming on 2017/6/20 0020.
 */
public class TableDubboBean implements Serializable {

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

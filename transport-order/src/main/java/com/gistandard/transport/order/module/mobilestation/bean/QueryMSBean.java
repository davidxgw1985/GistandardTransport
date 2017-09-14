package com.gistandard.transport.order.module.mobilestation.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yjf on 2016/5/6.
 */
public class QueryMSBean implements Serializable {
    private static final long serialVersionUID = 4955233022311665867L;
    private Integer recordCount;//总条数
    private List<QueryMSInAndOutBean> allQueryMSInAndOutBean=new ArrayList<QueryMSInAndOutBean>();//分页详情

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }

    public List<QueryMSInAndOutBean> getAllQueryMSInAndOutBean() {
        return allQueryMSInAndOutBean;
    }

    public void setAllQueryMSInAndOutBean(List<QueryMSInAndOutBean> allQueryMSInAndOutBean) {
        this.allQueryMSInAndOutBean = allQueryMSInAndOutBean;
    }
}

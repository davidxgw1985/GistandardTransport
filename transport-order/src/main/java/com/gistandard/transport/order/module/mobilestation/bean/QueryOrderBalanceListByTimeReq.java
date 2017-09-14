package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by yjf on 2016/4/29.
 */
public class QueryOrderBalanceListByTimeReq extends AppBaseRequest {
    private static final long serialVersionUID = -241308514937352931L;
    private String gFUserCode;//gifi账户code
    private String beginDate;//查询开始日期
    private String endDate;//查询结束日期
    private Integer startRecord;
    private Integer pageSize;
    private String payFlag;//0所有支付状态，1支付完成状态

    public String getgFUserCode() {
        return gFUserCode;
    }

    public void setgFUserCode(String gFUserCode) {
        this.gFUserCode = gFUserCode;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStartRecord() {
        return startRecord;
    }

    public void setStartRecord(Integer startRecord) {
        this.startRecord = startRecord;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getPayFlag() {
        return payFlag;
    }

    public void setPayFlag(String payFlag) {
        this.payFlag = payFlag;
    }
}

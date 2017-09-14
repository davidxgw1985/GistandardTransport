package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author: xgw
 * @ClassName: QueryFinishOrderReq
 * @Date: 2016/4/13
 * @Description:
 */
public class QueryFinishOrderReq extends AppBasePagerRequest{
    private static final long serialVersionUID = 5384484621692048551L;

    private String startDate;//开始时间 yyyy-MM-dd
    private String endDate;//结束时间 yyyy-MM-dd
    private String busiNo;//订单编号 模糊查询
    private Boolean failFlag;//是否是失败单 true 是 false 否

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getBusiNo() {
        return busiNo;
    }

    public void setBusiNo(String busiNo) {
        this.busiNo = busiNo;
    }

    public Boolean isFailFlag() {
        return failFlag;
    }

    public void setFailFlag(Boolean failFlag) {
        this.failFlag = failFlag;
    }
}

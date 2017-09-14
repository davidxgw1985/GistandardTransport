package com.gistandard.transport.order.module.mobilestation.bean.stock;

import com.gistandard.platform.pojo.req.AppBasePagerRequest;

/**
 * @author: xgw
 * @ClassName: QueryStockDetailListReq
 * @Date: 2016/2/1
 * @Description: 查询出入库历史
 */
public class QueryStockDetailListReq extends AppBasePagerRequest {
    private static final long serialVersionUID = 982417891283028074L;
    private Integer stockType;//出入库类型 0入库 1出库
    private String startDate;//开始日期 2016-01-30
    private String endDate;//结束日期
    private String busiBookNo;//订单号

    public Integer getStockType() {
        return stockType;
    }

    public void setStockType(Integer stockType) {
        this.stockType = stockType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }
}

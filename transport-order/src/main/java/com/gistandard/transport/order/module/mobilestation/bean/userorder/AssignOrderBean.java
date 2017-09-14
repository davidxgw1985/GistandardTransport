package com.gistandard.transport.order.module.mobilestation.bean.userorder;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: AssignOrderBean
 * @Date: 2016/6/11
 * @Description: 转单中心 - 指派Bean
 */
public class AssignOrderBean implements Serializable{
    private static final long serialVersionUID = 1267667492103390003L;

    private Integer orderId;//订单ID
    private String busiBookNo;//订单号
    private String comQuoteId;//报价单号
    private BigDecimal predictValue;//预估运费
    private String predictCurr;//运费币值

    public BigDecimal getPredictValue() {
		return predictValue;
	}

	public void setPredictValue(BigDecimal predictValue) {
		this.predictValue = predictValue;
	}

	public String getPredictCurr() {
		return predictCurr;
	}

	public void setPredictCurr(String predictCurr) {
		this.predictCurr = predictCurr;
	}

	public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(String comQuoteId) {
        this.comQuoteId = comQuoteId;
    }
}

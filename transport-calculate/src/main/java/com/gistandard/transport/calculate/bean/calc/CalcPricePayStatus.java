package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.transport.base.bean.app.BaseReqBean;
import com.gistandard.transport.base.bean.im.MsgIMReq;

/**
 * @Author zxnui
 * @Date 8/9/16.
 */
public class CalcPricePayStatus extends BaseReqBean {
    public static final String validCode = "82098f5ab036412d9042837036b02c72";

    private String busiBookNo;//订单号
    private String dispatchId;//签派单号
    private String scheducarno;//派车单号
    private int orderType;//0:O单 1：I单
    private MsgIMReq pushMessageIMBean;
    private String validBillno;//结算对账单号

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getDispatchId() {
        return dispatchId;
    }

    public void setDispatchId(String dispatchId) {
        this.dispatchId = dispatchId;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public MsgIMReq getPushMessageIMBean() {
        return pushMessageIMBean;
    }

    public void setPushMessageIMBean(MsgIMReq pushMessageIMBean) {
        this.pushMessageIMBean = pushMessageIMBean;
    }

    public String getValidBillno() {
        return validBillno;
    }

    public void setValidBillno(String validBillno) {
        this.validBillno = validBillno;
    }
}

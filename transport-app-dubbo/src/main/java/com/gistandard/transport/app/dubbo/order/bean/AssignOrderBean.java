package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: xgw
 * @ClassName: AssignOrderBean
 * @Date: 2017/2/7
 * @Description:
 */
public class AssignOrderBean implements Serializable{
    private static final long serialVersionUID = 1106456705771724069L;
    private String busiBookNo;//订单号
    private String scheducarno;//实派车单号
    private Integer orderFrom;//2:运输指派单，4:市场指派单, 5:运输广播,6:市场广播,7:MS指派,8:MS广播
    private String transportType;//0物流  1运输  2快递
    private Date pushDate;//HUB广播单的推送日期或者HUB指派单的创建日期
    private String errMsg;

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Date getPushDate() {
        return pushDate;
    }

    public void setPushDate(Date pushDate) {
        this.pushDate = pushDate;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}

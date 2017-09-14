package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;


/**
 * @author: xgw
 * @ClassName: MobileAssignOrderDetailReq
 * @Date: 2016/1/21
 * @Description: MS3.0 商管中心 查询订单详细请求Bean
 */
public class MobileAssignOrderDetailReq extends MsDubboReqBean {

    private static final long serialVersionUID = -7929909063358718079L;

    private Integer orderId;//订单编号
    private String busiBookNo;//订单号
    private Integer orderFrom;//订单来源
    private String scheducarno;//派车单号
    private Integer assignStatue;//订单指派状态 0未指派，1已指派

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

    public Integer getOrderFrom() {
        return orderFrom;
    }

    public void setOrderFrom(Integer orderFrom) {
        this.orderFrom = orderFrom;
    }

    public String getScheducarno() {
        return scheducarno;
    }

    public void setScheducarno(String scheducarno) {
        this.scheducarno = scheducarno;
    }

    public Integer getAssignStatue() {
        return assignStatue;
    }

    public void setAssignStatue(Integer assignStatue) {
        this.assignStatue = assignStatue;
    }
}

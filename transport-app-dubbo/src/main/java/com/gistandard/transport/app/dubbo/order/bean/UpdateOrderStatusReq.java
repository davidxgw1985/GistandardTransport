package com.gistandard.transport.app.dubbo.order.bean;

import java.io.Serializable;

/**
 * @author: xgw
 * @ClassName: UpdateOrderStatusReq
 * @Date: 2017/5/18
 * @Description: 蛙站收货后，更新快递员或司机的订单状态为已送达
 */
public class UpdateOrderStatusReq implements Serializable {
    private static final long serialVersionUID = -3346438348275621313L;

    private String busiBookNo;//订单号
    private String scheducarno;//派车单号
    private String custTtl;//蛙站简称

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

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }
}

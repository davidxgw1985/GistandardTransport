package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

/**
 * @author: 员伟
 * @ClassName: FleetCancleOrderReq
 * @Date: 2017/7/17
 * @Description: 车队取消订单模型
 */
public class FleetCancleOrderReq extends MsDubboReqBean {

    private static final long serialVersionUID = 3407401629322004776L;
    private String busiBookNo;//订单编号
    private String fleetCode;//报价的车队或者取消订单的车队
    private String loginCode;//报价的车队操作员工
    private String productType;//订单的产品类型

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public String getFleetCode() {
        return fleetCode;
    }

    public void setFleetCode(String fleetCode) {
        this.fleetCode = fleetCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}

package com.gistandard.transport.app.dubbo.fleet.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

/**
 * @author: xgw
 * @ClassName: FleetAcceptOrderReq
 * @Date: 2017/6/28
 * @Description: 车队接单 同城专送请求bean
 */
public class FleetAcceptOrderReq extends MsDubboReqBean{
    private static final long serialVersionUID = 3407401629322004776L;
    private String busiBookNo;
    private String fleetCode;//报价的车队
    private String loginCode;//报价的车队操作员工

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

}

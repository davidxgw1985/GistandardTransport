package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboPagerReqBean;

/**
 * @author: xgw
 * @ClassName: QueryBusRegisterUserReq
 * @Date: 2017/7/25
 * @Description: 商管中心 业务注册用户请求bean
 */
public class QueryBusRegisterUserReq extends MsDubboPagerReqBean{
    private static final long serialVersionUID = -515294311907248870L;

    private Boolean selfRegister;//是否自有注册 true是自有注册 false 业务推荐注册
    private String busCode;//业务员姓名或账号
    private String userCode;//用户姓名或账号
    private String startDate;//开始日期 格式2017-07-06
    private String endDate;//结束日期 格式2017-07-06

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryBusRegisterUserReq{");
        sb.append("selfRegister=").append(selfRegister);
        sb.append(", busCode='").append(busCode).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Boolean isSelfRegister() {
        return selfRegister;
    }

    public void setSelfRegister(Boolean selfRegister) {
        this.selfRegister = selfRegister;
    }

    public String getBusCode() {
        return busCode;
    }

    public void setBusCode(String busCode) {
        this.busCode = busCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
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
}

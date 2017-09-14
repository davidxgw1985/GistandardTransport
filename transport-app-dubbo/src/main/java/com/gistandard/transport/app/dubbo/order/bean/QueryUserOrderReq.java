package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboPagerReqBean;

/**
 * @author: xgw
 * @ClassName: QueryUserOrderReq
 * @Date: 2017/7/25
 * @Description: 商管中心 用户下单请求对象
 */
public class QueryUserOrderReq extends MsDubboPagerReqBean{
    private static final long serialVersionUID = 6421682986920385733L;

    private Boolean selfRegister;//是否自有注册 true是自有注册 false 业务推荐注册
    private String busCode;//业务员姓名或账号
    private String productType;//产品类型
    private String startDate;//开始日期 格式2017-07-06
    private String endDate;//结束日期 格式2017-07-06
    private String registerStartDate;//用户注册开始日期 格式2017-07-06
    private String registerEndDate;//用户注册结束日期 格式2017-07-06

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("QueryUserOrderReq{");
        sb.append("selfRegister=").append(selfRegister);
        sb.append(", busCode='").append(busCode).append('\'');
        sb.append(", productType='").append(productType).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append(", registerStartDate='").append(registerStartDate).append('\'');
        sb.append(", registerEndDate='").append(registerEndDate).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getRegisterStartDate() {
        return registerStartDate;
    }

    public void setRegisterStartDate(String registerStartDate) {
        this.registerStartDate = registerStartDate;
    }

    public String getRegisterEndDate() {
        return registerEndDate;
    }

    public void setRegisterEndDate(String registerEndDate) {
        this.registerEndDate = registerEndDate;
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

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

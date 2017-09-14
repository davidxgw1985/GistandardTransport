package com.gistandard.transport.app.dubbo.order.bean;

import com.gistandard.transport.app.dubbo.pojo.req.MsDubboReqBean;

import java.math.BigDecimal;

/**
 * @author: xgw
 * @ClassName: UpdateErrOrderSourceReq
 * @Date: 2017/7/31
 * @Description: 更新错误订单地址信息请求bean
 */
public class UpdateErrOrderSourceReq extends MsDubboReqBean {
    private static final long serialVersionUID = 5482699361753465141L;

    private String busiBookNo;//订单号
    private Boolean startErr;//起始地点坐标是否有误
    private String startProvideName;//起始省名称  江苏省
    private String startCityName;//起始市名称     南京市
    private String startCountyName;//起始区名称   雨花台区
    private BigDecimal startLng;//起始经度
    private BigDecimal startLat;//起始维度
    private Boolean destnErr;//目的地点坐标是否有误
    private String destnProvideName;//目的地省名称  江苏省
    private String destnCityName;//目的地市名称     南京市
    private String destnCountyName;//目的地区名称   雨花台区
    private BigDecimal destnLng;//目的地经度
    private BigDecimal destnLat;//目的地维度
    public BigDecimal mileage;//起点到终点的公里数(同城专送时需要)

    public String getStartProvideName() {
        return startProvideName;
    }

    public void setStartProvideName(String startProvideName) {
        this.startProvideName = startProvideName;
    }

    public String getStartCityName() {
        return startCityName;
    }

    public void setStartCityName(String startCityName) {
        this.startCityName = startCityName;
    }

    public String getStartCountyName() {
        return startCountyName;
    }

    public void setStartCountyName(String startCountyName) {
        this.startCountyName = startCountyName;
    }

    public String getDestnProvideName() {
        return destnProvideName;
    }

    public void setDestnProvideName(String destnProvideName) {
        this.destnProvideName = destnProvideName;
    }

    public String getDestnCityName() {
        return destnCityName;
    }

    public void setDestnCityName(String destnCityName) {
        this.destnCityName = destnCityName;
    }

    public String getDestnCountyName() {
        return destnCountyName;
    }

    public void setDestnCountyName(String destnCountyName) {
        this.destnCountyName = destnCountyName;
    }

    public BigDecimal getMileage() {
        return mileage;
    }

    public void setMileage(BigDecimal mileage) {
        this.mileage = mileage;
    }

    public String getBusiBookNo() {
        return busiBookNo;
    }

    public void setBusiBookNo(String busiBookNo) {
        this.busiBookNo = busiBookNo;
    }

    public Boolean isStartErr() {
        return startErr;
    }

    public void setStartErr(Boolean startErr) {
        this.startErr = startErr;
    }

    public BigDecimal getStartLng() {
        return startLng;
    }

    public void setStartLng(BigDecimal startLng) {
        this.startLng = startLng;
    }

    public BigDecimal getStartLat() {
        return startLat;
    }

    public void setStartLat(BigDecimal startLat) {
        this.startLat = startLat;
    }

    public Boolean isDestnErr() {
        return destnErr;
    }

    public void setDestnErr(Boolean destnErr) {
        this.destnErr = destnErr;
    }

    public BigDecimal getDestnLng() {
        return destnLng;
    }

    public void setDestnLng(BigDecimal destnLng) {
        this.destnLng = destnLng;
    }

    public BigDecimal getDestnLat() {
        return destnLat;
    }

    public void setDestnLat(BigDecimal destnLat) {
        this.destnLat = destnLat;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UpdateErrOrderSourceReq{");
        sb.append("busiBookNo='").append(busiBookNo).append('\'');
        sb.append(", startErr=").append(startErr);
        sb.append(", startProvideName='").append(startProvideName).append('\'');
        sb.append(", startCityName='").append(startCityName).append('\'');
        sb.append(", startCountyName='").append(startCountyName).append('\'');
        sb.append(", startLng=").append(startLng);
        sb.append(", startLat=").append(startLat);
        sb.append(", destnErr=").append(destnErr);
        sb.append(", destnProvideName='").append(destnProvideName).append('\'');
        sb.append(", destnCityName='").append(destnCityName).append('\'');
        sb.append(", destnCountyName='").append(destnCountyName).append('\'');
        sb.append(", destnLng=").append(destnLng);
        sb.append(", destnLat=").append(destnLat);
        sb.append(", mileage=").append(mileage);
        sb.append('}');
        return sb.toString();
    }
}

package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单坐标地址信息
 * @author 员伟
 * @date 2017-09-07
 */
public class WeChatOrderCdntAddr implements Serializable {

    private static final long serialVersionUID = -8878361000070954113L;

    private BigDecimal longitude;//经度
    private BigDecimal latitude;//纬度
    private String address;//地址
    private String imgUrl;//图片地址
    private Integer roleId;//当前角色ID
    private String name;//发件人姓名、收件人姓名、快递员姓名、咪站姓名、蛙站姓名
    private String linkName;//蛙站负责人姓名
    private String linkTel;//联系电话

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkName() {
        return linkName;
    }

    public void setLinkName(String linkName) {
        this.linkName = linkName;
    }

    public String getLinkTel() {
        return linkTel;
    }

    public void setLinkTel(String linkTel) {
        this.linkTel = linkTel;
    }
}

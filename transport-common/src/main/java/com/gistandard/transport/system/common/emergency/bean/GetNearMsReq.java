package com.gistandard.transport.system.common.emergency.bean;


import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: GetNearMsReq
 * @Date: 2016/6/11
 * @Description: 获取距离范围内附近的MS
 */
public class GetNearMsReq extends AppBaseRequest {
    private static final long serialVersionUID = -2191615872176708796L;

    private double longitude;//经度
    private double latitude;//纬度
    private double radius;//半径
    private String appTag;//应用标示
    private int scope;//1: 快递员, 2: 司机, 3: Hub 0：同城外卖
    private String productType;
    private String roleId;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getAppTag() {
        return appTag;
    }

    public void setAppTag(String appTag) {
        this.appTag = appTag;
    }

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
}

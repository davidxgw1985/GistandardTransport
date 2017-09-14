package com.gistandard.transport.app.module.station.bean;

import com.gistandard.transport.base.bean.gps.GiPoint;

import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/10/8.
 */
public class CustomerListBean {
    public static final String M = "M";
    public static final String W = "W";

    private String id;
    private Integer userAccountId;//用户编号
    private String userImg;  //头像
    private String custTtl; //W站简称
    private String userCode;
    private String userName;
    private String telephone;
    private String type;            //站点类型 M:咪站，W:蛙站(即HUB站点)
    private Integer category;       //层级，1，2，3分别对应蛙1，蛙2，蛙3
    private Integer stationCategoryAttr;            //蛙站性质：1：固定站点，2：移动站点
    private GiPoint giPoint;

    private Date tsResynced;
    private Date tsCreated;

    private String province;
    private String city;
    private String district;
    private String address;

    private String idGiMiRoutePlan; //咪站线路id--如果是移动咪站,
    private List<GiMiRouteStop> allGiMiRouteStop; //如果是移动咪站, 则返回其所有停靠点

    private List<GiMiRoutePoint> allGiMiRoutePoint;             //如果是移动咪站, 则返回其所有路线点

    private String pyUserName;

    private String pyProvince;

    private String pyCity;

    private String pyDistrict;

    private String pyAddress;

    //用于查询返回
    private Integer seq;
    private Double distance; //单位：米

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(Integer userAccountId) {
        this.userAccountId = userAccountId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getTsResynced() {
        return tsResynced;
    }

    public void setTsResynced(Date tsResynced) {
        this.tsResynced = tsResynced;
    }

    public Date getTsCreated() {
        return tsCreated;
    }

    public void setTsCreated(Date tsCreated) {
        this.tsCreated = tsCreated;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPyUserName() {
        return pyUserName;
    }

    public void setPyUserName(String pyUserName) {
        this.pyUserName = pyUserName;
    }

    public String getPyProvince() {
        return pyProvince;
    }

    public void setPyProvince(String pyProvince) {
        this.pyProvince = pyProvince;
    }

    public String getPyCity() {
        return pyCity;
    }

    public void setPyCity(String pyCity) {
        this.pyCity = pyCity;
    }

    public String getPyDistrict() {
        return pyDistrict;
    }

    public void setPyDistrict(String pyDistrict) {
        this.pyDistrict = pyDistrict;
    }

    public String getPyAddress() {
        return pyAddress;
    }

    public void setPyAddress(String pyAddress) {
        this.pyAddress = pyAddress;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public GiPoint getGiPoint() {
        return giPoint;
    }

    public void setGiPoint(GiPoint giPoint) {
        this.giPoint = giPoint;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public Integer getStationCategoryAttr() {
        return stationCategoryAttr;
    }

    public void setStationCategoryAttr(Integer stationCategoryAttr) {
        this.stationCategoryAttr = stationCategoryAttr;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIdGiMiRoutePlan() {
        return idGiMiRoutePlan;
    }

    public void setIdGiMiRoutePlan(String idGiMiRoutePlan) {
        this.idGiMiRoutePlan = idGiMiRoutePlan;
    }

    public List<GiMiRouteStop> getAllGiMiRouteStop() {
        return allGiMiRouteStop;
    }

    public void setAllGiMiRouteStop(List<GiMiRouteStop> allGiMiRouteStop) {
        this.allGiMiRouteStop = allGiMiRouteStop;
    }

    public List<GiMiRoutePoint> getAllGiMiRoutePoint() {
        return allGiMiRoutePoint;
    }

    public void setAllGiMiRoutePoint(List<GiMiRoutePoint> allGiMiRoutePoint) {
        this.allGiMiRoutePoint = allGiMiRoutePoint;
    }
}

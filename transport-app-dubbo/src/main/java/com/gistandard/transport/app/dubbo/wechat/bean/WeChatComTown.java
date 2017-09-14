package com.gistandard.transport.app.dubbo.wechat.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by m on 2017/2/7.
 */
public class WeChatComTown implements Serializable {
    private static final long serialVersionUID = -5255100023510207257L;
    private Integer id;

    private String townName;

    private String shortName;

    private Integer countyId;

    private Integer status;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Integer sortNo;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

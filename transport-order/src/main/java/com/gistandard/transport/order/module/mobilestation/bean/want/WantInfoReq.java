package com.gistandard.transport.order.module.mobilestation.bean.want;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.tools.util.DateUtil;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class WantInfoReq extends AppBaseRequest{

    private static final long serialVersionUID = -1276180303504179702L;
    public static final int STATUS_YES = 1;//订单状态，1为有效
    public static final int STATUS_NO = 0;//订单状态，0为无效

    private Integer id;
    private String lineStart;//发车地点
    private String lineDest;//到货地点
    private String departTime;//发车时间
    private String arriveTime;//抵达时间
    private List<Line> lineList;//多条线路

    private Integer respondentUser;//0：所有站点 1：指定站点
    private List<Station> respondentUserList;//多个站点

    private BigDecimal restLoad;//剩余载重
    private BigDecimal restSpace;//剩余空间
    private BigDecimal heavyPrice;//重货报价
    private BigDecimal lightPrice;//轻货报价
    private BigDecimal lowestVote;//最低一票价格
    private BigDecimal perTicket;//每票价格
    private String currency;//币制
    private Integer wantType;//1：我要接单 2：我要运输

    private String createTime;//创建时间
    private Integer status;//状态，1：有效 0：删除
    private String station;//站点

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLineStart() {
        return lineStart;
    }

    public void setLineStart(String lineStart) {
        this.lineStart = lineStart;
    }

    public String getLineDest() {
        return lineDest;
    }

    public void setLineDest(String lineDest) {
        this.lineDest = lineDest;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = DateUtil.formatDate2Str(departTime, "yyyy-MM-dd");
    }

    public String getArriveTime() {
        return arriveTime;
    }

    public void setArriveTime(Date arriveTime) {
        this.arriveTime = DateUtil.formatDate2Str(arriveTime,"yyyy-MM-dd");
    }

    public List<Line> getLineList() {
        return lineList;
    }

    public void setLineList(List<Line> lineList) {
        this.lineList = lineList;
    }

    public Integer getRespondentUser() {
        return respondentUser;
    }

    public void setRespondentUser(Integer respondentUser) {
        this.respondentUser = respondentUser;
    }

    public List<Station> getRespondentUserList() {
        return respondentUserList;
    }

    public void setRespondentUserList(List<Station> respondentUserList) {
        this.respondentUserList = respondentUserList;
    }

    public BigDecimal getRestLoad() {
        return restLoad;
    }

    public void setRestLoad(BigDecimal restLoad) {
        this.restLoad = restLoad;
    }

    public BigDecimal getRestSpace() {
        return restSpace;
    }

    public void setRestSpace(BigDecimal restSpace) {
        this.restSpace = restSpace;
    }

    public BigDecimal getHeavyPrice() {
        return heavyPrice;
    }

    public void setHeavyPrice(BigDecimal heavyPrice) {
        this.heavyPrice = heavyPrice;
    }

    public BigDecimal getLightPrice() {
        return lightPrice;
    }

    public void setLightPrice(BigDecimal lightPrice) {
        this.lightPrice = lightPrice;
    }

    public BigDecimal getLowestVote() {
        return lowestVote;
    }

    public void setLowestVote(BigDecimal lowestVote) {
        this.lowestVote = lowestVote;
    }

    public BigDecimal getPerTicket() {
        return perTicket;
    }

    public void setPerTicket(BigDecimal perTicket) {
        this.perTicket = perTicket;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Integer getWantType() {
        return wantType;
    }

    public void setWantType(Integer wantType) {
        this.wantType = wantType;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtil.formatDate2Str(createTime,"yyyy-MM-dd");;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }
}

package com.gistandard.transport.app.module.quote.bean;


import com.gistandard.transport.base.bean.app.BaseReqPageBean;

/**
 * @author xgw
 * @ClassName QueryQuoteListReq
 * @Description 我的服务、我的产品 列表查询 请求Bean
 * @Version 1.0
 * @Date 2016-2-25
 */
public class QueryQuoteListReq extends BaseReqPageBean {
    private static final long serialVersionUID = -187993801664157824L;

    private Integer itemId;//条目编号
    private Integer quoteType;//（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
    private Integer itemCategory;//报价类型，1我的产品，2我的服务
    private Integer itemType;//1、物流，2、运输，3、快递
    private Integer startRoute;//产品线路起始地
    private Integer endRoute;//产品线路目的地
    private Integer startStation;//开始站点
    private Integer endStation;//结束站点
    private String acctUsername;//账号名称
    private String userinfoId;//用户ID
    private String customerId;//站点ID
    private Boolean manage = false;
    //是否公开
    private Boolean publicFlag;
    //1、取件，2、派件
    private Integer kdOperateType;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getStartRoute() {
        return startRoute;
    }

    public void setStartRoute(Integer startRoute) {
        this.startRoute = startRoute;
    }

    public Integer getEndRoute() {
        return endRoute;
    }

    public void setEndRoute(Integer endRoute) {
        this.endRoute = endRoute;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public String getAcctUsername() {
        return acctUsername;
    }

    public void setAcctUsername(String acctUsername) {
        this.acctUsername = acctUsername;
    }

    public String getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(String userinfoId) {
        this.userinfoId = userinfoId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    public Boolean getManage() {
        return manage;
    }

    public void setManage(Boolean manage) {
        this.manage = manage;
    }

    public Boolean getPublicFlag() {
        return publicFlag;
    }

    public void setPublicFlag(Boolean publicFlag) {
        this.publicFlag = publicFlag;
    }

    public Integer getKdOperateType() {
        return kdOperateType;
    }

    public void setKdOperateType(Integer kdOperateType) {
        this.kdOperateType = kdOperateType;
    }
}

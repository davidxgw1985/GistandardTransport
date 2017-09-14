package com.gistandard.transport.quote.system.common.bean;


import com.gistandard.transport.base.bean.grid.GridBean;
import com.gistandard.transport.quote.system.common.define.QuoteStatus;

import java.io.Serializable;

/**
 * Created by shenzhijun on 2016/2/24.
 */
public class ProductQueryBean extends GridBean implements Serializable{
    private static final long serialVersionUID = -8321369731597287312L;
    //条目类型ID（COM_PRODUCT_ITEM表主键）
    private Integer itemId ;

    //条目类别（1、产品，2、服务）
    private Integer itemCategory;

    //（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
    private Integer quoteType;

    //（1、物流，2、运输，3、快递）
    private Integer itemType;

    //产品线路起始地
    private Integer startRoute;

    //产品线路目的地
    private Integer endRoute;

    //开始站点
    private Integer startStation;

    //结束站点
    private Integer endStation;


    //帐号
    private String account;

    //商户
    private String customerId;

    private String userinfoId;

    //COM_QUOTE 主键
    private Integer comQuoteId;

    //状态
    private QuoteStatus quoteStatus;

    //产品类型（CI 整车，CA 零担）
    private String productType;

    private Boolean isHide;

    private String quoteNo;

    //私密客户ID
    private Integer clientAccountId;

    //
    private Integer accountId;

    //
    private Integer belongAccountId;

    private String accountName;

    //是否管理查询（管理查询可以查询禁用 报价，其他只能查询启用报价）
    private Boolean isManage = false;

    //开始线路类型
    private String startRouteType;

    //结束线路类型
    private String endRouteType;

    //角色查询
    private Integer roleId;

    //角色类型
    private Integer roleType;

    // 1、仅公开报价 2、仅私密报价 3、公开及对我私密报价 4、公开和私密报价
    private String publicState = "1";

    //排除某个帐号的报价
    private Integer excludeAccountId;

    private Integer kdOperateType;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public Integer getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(Integer quoteType) {
        this.quoteType = quoteType;
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



    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getComQuoteId() {
        return comQuoteId;
    }

    public void setComQuoteId(Integer comQuoteId) {
        this.comQuoteId = comQuoteId;
    }

    public QuoteStatus getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(QuoteStatus quoteStatus) {
        this.quoteStatus = quoteStatus;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }


    public Boolean getIsHide() {
        return isHide;
    }

    public void setIsHide(Boolean isHide) {
        this.isHide = isHide;
    }

    public String getUserinfoId() {
        return userinfoId;
    }

    public void setUserinfoId(String userinfoId) {
        this.userinfoId = userinfoId;
    }

    public String getQuoteNo() {
        return quoteNo;
    }

    public void setQuoteNo(String quoteNo) {
        this.quoteNo = quoteNo;
    }

    public Integer getClientAccountId() {
        return clientAccountId;
    }

    public void setClientAccountId(Integer clientAccountId) {
        this.clientAccountId = clientAccountId;
    }

    public Boolean getIsManage() {
        return isManage;
    }

    public void setIsManage(Boolean isManage) {
        this.isManage = isManage;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getStartRouteType() {
        return startRouteType;
    }

    public void setStartRouteType(String startRouteType) {
        this.startRouteType = startRouteType;
    }

    public String getEndRouteType() {
        return endRouteType;
    }

    public void setEndRouteType(String endRouteType) {
        this.endRouteType = endRouteType;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleType() {
        return roleType;
    }

    public void setRoleType(Integer roleType) {
        this.roleType = roleType;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getPublicState() {
        return publicState;
    }

    public void setPublicState(String publicState) {
        this.publicState = publicState;
    }


    public Integer getBelongAccountId() {
        return belongAccountId;
    }

    public void setBelongAccountId(Integer belongAccountId) {
        this.belongAccountId = belongAccountId;
    }

    public Integer getExcludeAccountId() {
        return excludeAccountId;
    }

    public void setExcludeAccountId(Integer excludeAccountId) {
        this.excludeAccountId = excludeAccountId;
    }

    public Integer getKdOperateType() {
        return kdOperateType;
    }

    public void setKdOperateType(Integer kdOperateType) {
        this.kdOperateType = kdOperateType;
    }
}

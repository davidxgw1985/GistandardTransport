package com.gistandard.transport.quote.system.common.bean;

import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.base.entity.bean.ComQuoteItem;

/**
 * Created by shenzhijun on 2016/3/1.
 */
public class ComQuoteBean  extends ComQuote {
    private String createTimeStr;
    //条目名称
    private String itemName;
    //条目类型（0、物流，1、运输，2、快递）

    private Integer itemType;

    private String itemTypeName;

    private Integer itemCategory;

    private String itemCode;

    private ComQuoteItem comQuoteItem;

    private String custTtl;

    private Integer userInfoAccountId;

    //这个字段针对条目类型中，类型是运输的。（1、整车，2、零担，3、按重量分段，4、按公里，5、按公里分段）
    private String quoteTypeName;

    //路径
    private String route;

    //GPS路线名称
    private String routeName;

    //站点
    private String station;
    //地点
    private String addressName;

    private String startStationName;

    private String endStationName;

    //币制名称
    private String currencyCh;

    //开始路线
    private String startRouteName;

    //结束路线
    private String endRouteName;

    //关区
    private String customsNa;


    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTypeName() {
        return itemTypeName;
    }

    public void setItemTypeName(String itemTypeName) {
        this.itemTypeName = itemTypeName;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public String getQuoteTypeName() {
        return quoteTypeName;
    }

    public void setQuoteTypeName(String quoteTypeName) {
        this.quoteTypeName = quoteTypeName;
    }

    public String getCurrencyCh() {
        return currencyCh;
    }

    public void setCurrencyCh(String currencyCh) {
        this.currencyCh = currencyCh;
    }

    public String getStartRouteName() {
        return startRouteName;
    }

    public void setStartRouteName(String startRouteName) {
        this.startRouteName = startRouteName;
    }

    public String getEndRouteName() {
        return endRouteName;
    }

    public void setEndRouteName(String endRouteName) {
        this.endRouteName = endRouteName;
    }



    public String getStartStationName() {
        return startStationName;
    }

    public void setStartStationName(String startStationName) {
        this.startStationName = startStationName;
    }

    public String getEndStationName() {
        return endStationName;
    }

    public void setEndStationName(String endStationName) {
        this.endStationName = endStationName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public Integer getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(Integer itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getCustTtl() {
        return custTtl;
    }

    public void setCustTtl(String custTtl) {
        this.custTtl = custTtl;
    }

    public ComQuoteItem getComQuoteItem() {
        return comQuoteItem;
    }

    public void setComQuoteItem(ComQuoteItem comQuoteItem) {
        this.comQuoteItem = comQuoteItem;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getCustomsNa() {
        return customsNa;
    }

    public void setCustomsNa(String customsNa) {
        this.customsNa = customsNa;
    }


    public Integer getUserInfoAccountId() {
        return userInfoAccountId;
    }

    public void setUserInfoAccountId(Integer userInfoAccountId) {
        this.userInfoAccountId = userInfoAccountId;
    }

}

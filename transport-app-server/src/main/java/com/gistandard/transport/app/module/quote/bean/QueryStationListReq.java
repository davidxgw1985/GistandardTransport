package com.gistandard.transport.app.module.quote.bean;


import com.gistandard.transport.base.bean.app.BaseReqPageBean;

/**
 * Created by m on 2016/3/7.
 */
public class QueryStationListReq extends BaseReqPageBean {

    private String areaCode;//城市，地区，省份代码
    private String keywords;//关键字

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
}

package com.gistandard.transport.app.module.address.bean;

import com.gistandard.transport.base.bean.app.BaseReqBean;


/**
 * @author kongxm
 * @ClassName AddressReq
 * @Description 区县查询街道信息
 * @Version 1.0
 * @Date 2016/1/26
 */
public class TownCountyReq extends BaseReqBean{
    private Integer countyId;

    public Integer getCountyId() {
        return countyId;
    }

    public void setCountyId(Integer countyId) {
        this.countyId = countyId;
    }
}
package com.gistandard.transport.app.module.address.bean;

import com.gistandard.transport.base.bean.app.BaseReqBean;
import com.gistandard.transport.base.bean.app.ValidTokenMark;


/**
 * @author kongxm
 * @ClassName AddressReq
 * @Description 客户下单地址管理请求
 * @Version 1.0
 * @Date 2016/1/26
 */
public class AddressDeleteReq extends BaseReqBean implements ValidTokenMark{
    private Integer[] ids;

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }
}
package com.gistandard.transport.app.module.login.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * Created by yujie on 2016/9/29.
 */
public class SelectCompanyRequest extends AppBaseRequest{

   private Integer companyId;


    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

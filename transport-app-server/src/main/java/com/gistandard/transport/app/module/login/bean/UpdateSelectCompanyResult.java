package com.gistandard.transport.app.module.login.bean;

import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * @author: xgw
 * @ClassName: UpdateSelectCompanyResult
 * @Date: 2017/8/7
 * @Description:
 */
public class UpdateSelectCompanyResult extends AppBaseResult{
    private static final long serialVersionUID = 3517695776497836974L;

    private UpdateSelectCompanyResBean data;

    public UpdateSelectCompanyResBean getData() {
        return data;
    }

    public void setData(UpdateSelectCompanyResBean data) {
        this.data = data;
    }
}

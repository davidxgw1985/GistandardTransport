package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;

/**
 * @author: xgw
 * @ClassName: GetExpressScheduCarInfoRes
 * @Date: 2016/9/26
 * @Description:
 */
public class GetExpressScheduCarInfoRes extends AppBaseRequest {

    private static final long serialVersionUID = -7836715257440948172L;

    private GetExpressScheduCarInfoBean data;

    public GetExpressScheduCarInfoBean getData() {
        return data;
    }

    public void setData(GetExpressScheduCarInfoBean data) {
        this.data = data;
    }
}

package com.gistandard.transport.calculate.bean.calc;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * @author: xgw
 * @ClassName: PurchaseRes
 * @Date: 2017/7/28
 * @Description:
 */
public class PurchaseRes extends AppBaseResult {
    private static final long serialVersionUID = -4665774311140685538L;

    public PurchaseRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private String billNo;

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }
}

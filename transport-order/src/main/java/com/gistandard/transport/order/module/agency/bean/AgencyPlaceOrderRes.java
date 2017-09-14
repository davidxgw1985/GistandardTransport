package com.gistandard.transport.order.module.agency.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.customer.bean.PlaceAnOrderRes;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;

/**
 * @author: xgw
 * @ClassName: AgencyPlaceOrderRes
 * @Date: 2016/10/6
 * @Description:
 */
public class AgencyPlaceOrderRes extends AppBaseResult{
    public AgencyPlaceOrderRes(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private PlaceAnOrderRes data;

    //after
    private CalcForPriceReq calcForPriceReq;  //结算数据

    public CalcForPriceReq getCalcForPriceReq() {
        return calcForPriceReq;
    }

    public void setCalcForPriceReq(CalcForPriceReq calcForPriceReq) {
        this.calcForPriceReq = calcForPriceReq;
    }

    public PlaceAnOrderRes getData() {
        return data;
    }

    public void setData(PlaceAnOrderRes data) {
        this.data = data;
    }
}

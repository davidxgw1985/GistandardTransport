package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.BookingForm;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * Created by m on 2017/3/10.
 */
public class BatchModifyGoodsInfoResult extends AppBaseResult {

    public BatchModifyGoodsInfoResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    @ApiModelProperty(value = "订单对象信息")
    private BookingForm data;

    public BookingForm getData() {
        return data;
    }

    public void setData(BookingForm data) {
        this.data = data;
    }
}

package com.gistandard.transport.order.module.mobilestation.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by m on 2016/12/14.
 */
public class MobileStationBatchAcceptOrderResult extends AppBaseResult implements Serializable {

    @ApiModelProperty(value = "接单结果", position = 1)
    private MobileStationAcceptOrderCustomResult data;

    public MobileStationBatchAcceptOrderResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    public MobileStationAcceptOrderCustomResult getData() {
        return data;
    }

    public void setData(MobileStationAcceptOrderCustomResult data) {
        this.data = data;
    }
}

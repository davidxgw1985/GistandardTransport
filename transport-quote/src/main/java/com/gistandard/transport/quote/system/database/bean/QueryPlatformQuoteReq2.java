package com.gistandard.transport.quote.system.database.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/10/21.
 */
@ApiModel
public class QueryPlatformQuoteReq2 {
    @ApiModelProperty(value = "系统标识", required = false, position = 1)
    String systemFlag;
    @ApiModelProperty(value = "订单号", required = true, position = 2)
    String busibookno;
    @ApiModelProperty(value = "startLocus", required = true, position = 3)
    String startLocus;
    @ApiModelProperty(value = "destLocus", required = true, position = 4)
    String destLocus;

    public String getSystemFlag() {
        return systemFlag;
    }

    public void setSystemFlag(String systemFlag) {
        this.systemFlag = systemFlag;
    }

    public String getStartLocus() {
        return startLocus;
    }

    public void setStartLocus(String startLocus) {
        this.startLocus = startLocus;
    }

    public String getDestLocus() {
        return destLocus;
    }

    public void setDestLocus(String destLocus) {
        this.destLocus = destLocus;
    }

    public String getBusibookno() {
        return busibookno;
    }

    public void setBusibookno(String busibookno) {
        this.busibookno = busibookno;
    }
}

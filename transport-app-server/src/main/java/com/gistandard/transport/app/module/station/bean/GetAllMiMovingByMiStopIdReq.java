package com.gistandard.transport.app.module.station.bean;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2016/12/30.
 */
public class GetAllMiMovingByMiStopIdReq {
    @ApiModelProperty(value = "咪站停靠点code", required = true, position = 1)
    private String miStopId;
    @ApiModelProperty(value = "咪站路线ID", required = true, position = 2)
    private String idGiMiRoutePlan;

    public String getMiStopId() {
        return miStopId;
    }

    public void setMiStopId(String miStopId) {
        this.miStopId = miStopId;
    }

    public String getIdGiMiRoutePlan() {
        return idGiMiRoutePlan;
    }

    public void setIdGiMiRoutePlan(String idGiMiRoutePlan) {
        this.idGiMiRoutePlan = idGiMiRoutePlan;
    }
}

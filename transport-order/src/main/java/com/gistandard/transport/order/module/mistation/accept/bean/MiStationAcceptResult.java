package com.gistandard.transport.order.module.mistation.accept.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;

/**
 * Created by m on 2017/4/19.
 */
public class MiStationAcceptResult extends AppBaseResult {

    private static final long serialVersionUID = -7529299406290703374L;

    public MiStationAcceptResult() {
        super();
    }

    public MiStationAcceptResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private Integer newRouteId;

    private Integer oldRouteId;

    //是否打印
    private boolean rePrintOrder;

    public boolean getRePrintOrder() {
        return rePrintOrder;
    }

    public void setRePrintOrder(boolean rePrintOrder) {
        this.rePrintOrder = rePrintOrder;
    }

    public Integer getNewRouteId() {
        return newRouteId;
    }

    public void setNewRouteId(Integer newRouteId) {
        this.newRouteId = newRouteId;
    }

    public Integer getOldRouteId() {
        return oldRouteId;
    }

    public void setOldRouteId(Integer oldRouteId) {
        this.oldRouteId = oldRouteId;
    }
}

package com.gistandard.transport.order.module.mistation.stock.bean;

import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.platform.pojo.res.AppBaseResult;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by m on 2017/4/20.
 */
public class MiStationStockResult extends AppBaseResult {

    private static final long serialVersionUID = 6834729549043292805L;

    public MiStationStockResult() {
        super();
    }

    public MiStationStockResult(AppBaseRequest appBaseRequest) {
        super(appBaseRequest);
    }

    private Integer newRouteId;

    private Integer oldRouteId;

    //是否打印
    private boolean rePrintOrder;

    //路由线路信息
    private String routePathInfo;

    private String cneeCustLinkMan;

    private String cneeCustLinkTel;

    public String getCneeCustLinkMan() {
        return cneeCustLinkMan;
    }

    public void setCneeCustLinkMan(String cneeCustLinkMan) {
        this.cneeCustLinkMan = cneeCustLinkMan;
    }

    public String getCneeCustLinkTel() {
        return cneeCustLinkTel;
    }

    public void setCneeCustLinkTel(String cneeCustLinkTel) {
        this.cneeCustLinkTel = cneeCustLinkTel;
    }

    public String getRoutePathInfo() {
        return routePathInfo;
    }

    public void setRoutePathInfo(String routePathInfo) {
        this.routePathInfo = routePathInfo;
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

    public boolean getRePrintOrder() {
        return rePrintOrder;
    }

    public void setRePrintOrder(boolean rePrintOrder) {
        this.rePrintOrder = rePrintOrder;
    }
}

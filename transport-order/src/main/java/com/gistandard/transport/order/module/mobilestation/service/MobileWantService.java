package com.gistandard.transport.order.module.mobilestation.service;


import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.bean.app.BaseResBean;
import com.gistandard.transport.base.bean.app.BaseResPageBean;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.StationReq;
import com.gistandard.transport.order.module.mobilestation.bean.want.*;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantReq;
import com.gistandard.transport.order.webservice.server.mobilestation.bean.MobileWantSjReq;

/**
 * @author xgw
 * @ClassName MobileWantService
 * @Description
 * @Version 1.0
 * @Date 2016-7-20
 */
public interface MobileWantService {

    /**
     * 我要接单和我要运输删除接口
     * @param wantInfoReq
     * @return
     */
    AppBaseResult deleteWantInfo(WantInfoReq wantInfoReq) throws MobileStationBizException;

    /**
     * 我要接单、我要运输新增接口
     *
     * @param wantInfoReq
     * @throws Exception
     */
    AppBaseResult saveWantInfo(WantInfoReq wantInfoReq) throws MobileStationBizException;

    /**
     * 我要接单、我要运输列表查询
     *
     * @param queryWantListReq
     * @throws Exception
     */
    QueryWantListResult queryWantList(QueryWantListReq queryWantListReq) throws MobileStationBizException;

    /**
     * 我要接单webservice查询
     * @param mobileWantReq
     * @return
     */
    GetByLineStartAndLineDestAndUserNameAndStationNameResult getByLineStartAndLineDestAndUserNameAndStationName(MobileWantReq mobileWantReq) throws MobileStationBizException;

    /**
     * 我要运输webservice查询
     * @param mobileWantSjReq
     * @return
     */
    GetByLineStartAndLineDestAndUserNameAndStationNameRes getByLineStartAndLineDestAndUserNameAndStationName(MobileWantSjReq mobileWantSjReq) throws MobileStationBizException;

    /**
     * 我要接单站点查询
     * @return
     * @throws MobileStationBizException
     */
    BaseResPageBean getStation(StationReq stationReq) throws MobileStationBizException;
}

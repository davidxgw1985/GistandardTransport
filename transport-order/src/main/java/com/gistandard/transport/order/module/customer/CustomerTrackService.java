package com.gistandard.transport.order.module.customer;

import com.gistandard.transport.order.module.customer.bean.track.QueryOrderCurrInfoReq;
import com.gistandard.transport.order.module.customer.bean.track.QueryOrderCurrInfoRes;
import com.gistandard.transport.order.module.customer.bean.track.TrackQueryRes;
import com.gistandard.transport.base.exception.MobileStationBizException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by yjf on 2016/3/31.
 */
public interface CustomerTrackService {
    /**
     * 根据busi号查运单历史
     * @param busiBookNo
     * @return
     */
    List<TrackQueryRes> query(String busiBookNo) throws MobileStationBizException;

    /**
     * 根据busi号查运单历史
     * @param busiBookNo
     * @return
     */
    List<TrackQueryRes> queryWithoutCnee(String busiBookNo) throws MobileStationBizException;

    /**
     * 根据订单号查询订单坐标信息
     * @param req
     * @return
     */
    QueryOrderCurrInfoRes queryOrderCurrInfo(@RequestBody QueryOrderCurrInfoReq req);
}

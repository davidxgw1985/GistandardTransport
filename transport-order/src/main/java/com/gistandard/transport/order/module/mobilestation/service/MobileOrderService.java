package com.gistandard.transport.order.module.mobilestation.service;

import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.ordermanage.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @author xgw
 * @ClassName MobileOrderService
 * @Description 订单管理 接口
 * @Version 1.0
 * @Date 2016/12/9
 */
public interface MobileOrderService {

    /**
     * MS3.0
     * 获取订单管理-我的订单列表
     *
     * @param mobileOrderListReq
     * @throws Exception
     */
    MobileOrderListResult queryOrderList(MobileOrderListReq mobileOrderListReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 获取订单管理-快递员订单详细
     *
     * @param mobileOrderDetailReq
     * @throws Exception
     */
    MobileOrderDetailResult queryOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 获取订单管理-咪站订单详细
     *
     * @param mobileOrderDetailReq
     * @throws Exception
     */
    MobileOrderDetailResult queryMiOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException;

    /**
     * MS3.0
     * 获取订单管理-司机订单详细
     *
     * @param mobileOrderDetailReq
     * @throws Exception
     */
    MobileOrderDetailResult queryDriverOrderDetail(MobileOrderDetailReq mobileOrderDetailReq) throws MobileStationBizException;

    /**
     * MS3.0批量发车
     *
     * @param batchDepartReq
     * @return
     */
    BatchOperateResult batchDepart(BatchOperateReq batchDepartReq) throws MobileStationBizException;

    /**
     * MS3.0批量发车
     *
     * @param batchDepartReq
     * @return
     */
    BatchOperateResult batchDeliveryConfirm(BatchOperateReq batchDepartReq) throws MobileStationBizException;

    /**
     * 订单照片文件上传
     * @param request
     * @return
     * @throws Exception
     */
    OrderPicUploadResult orderPicUpload(MultipartHttpServletRequest request) throws Exception;

    /**
     * 修改订单支付方式
     * @param updatePayInfoReq
     * @return
     * @throws Exception
     */
    AppBaseResult modifyOrderPayInfo(UpdatePayInfoReq updatePayInfoReq) throws Exception;

}

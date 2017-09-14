package com.gistandard.transport.app.module.order.mobilestation.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileStationOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author xgw
 * @ClassName MobileStationOrderController
 * @Description MobileStation订单操作
 * @Version 1.0
 * @Date 2016-01-21
 */
@Controller
@RequestMapping(value = AppServerDefine.MS_ORDER_URL)
public class MobileStationOrderController extends BaseController {

    private static final String TAGS_DESC = "MS订单操作";
    private static final Logger logger = LoggerFactory.getLogger(MobileStationOrderController.class);
    @Autowired
    private MobileStationOrderService mobileStationOrderService;
    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    /**
     * 根据订单编号和货物编号，获取货物信息
     *
     * @param getGoodsInfoReq
     * @throws Exception
     */
    @RequestMapping(value = "/getGoodsInfo", method = RequestMethod.POST)
    @ResponseBody
    public void getGoodsInfo(@RequestBody
                             GetGoodsInfoReq getGoodsInfoReq) throws Exception {
        logger.info("获取货物信息 getGoodsInfo:{}", JSON.toJSONString(getGoodsInfoReq));
        writeJson(mobileStationOrderService.getGoodsInfo(getGoodsInfoReq));
    }

    /**
     * 获取某段时间所有单据的收支列表
     *
     * @param queryOrderBalanceListByTimeReq
     * @throws Exception
     */
    @RequestMapping(value = "/queryOrderBalanceListByTime", method = RequestMethod.POST)
    @ResponseBody
    public void queryOrderBalanceListByTime(@RequestBody QueryOrderBalanceListByTimeReq queryOrderBalanceListByTimeReq) throws Exception {
        logger.info("获取某段时间所有单据的收支列表 queryOrderBalanceListByTime:{}", JSON.toJSONString(queryOrderBalanceListByTimeReq));
        AppBaseResult res;
        if (queryOrderBalanceListByTimeReq != null) {
            res = mobileStationOrderService.queryOrderBalanceListByTime(queryOrderBalanceListByTimeReq);
        } else {
            res = new AppBaseResult();
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("请求参数不能为空！");
        }
        writeJson(res);
    }

    /**
     * 修改订单线路信息
     *
     * @param updateLineInfoReq
     * @throws Exception
     */
    @ApiOperation(value = "修改订单线路信息", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC, produces = "application/json", notes = "")
    @RequestMapping(value = "/modifyLineInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult modifyLineInfo(@RequestBody UpdateLineInfoReq updateLineInfoReq) throws Exception {
        logger.info("修改订单线路信息 modifyLineInfo:{}", JSON.toJSONString(updateLineInfoReq));
        ModifyLineInfoResult modifyLineInfoResult = new ModifyLineInfoResult(updateLineInfoReq);
        if (StringUtils.isNotBlank(updateLineInfoReq.getCneeCustAddr()) || StringUtils.isNotBlank(updateLineInfoReq.getShipCustAddr())) {
            modifyLineInfoResult = mobileStationOrderService.modifyLineInfo(updateLineInfoReq);
            if (modifyLineInfoResult.getData() != null && !StringUtil.isEmpty(modifyLineInfoResult.getData().getBusiBookNo())) {
                //更新路由
                if (modifyLineInfoResult.getData().getTransportType() != null && !MobileStationDefine.PRODUCT_TYPE_TCZS.equals(modifyLineInfoResult.getData().getTransportType())) {
                    BaseRequestResult result = mobileRecOrderWebService.updateOrderRouteId(modifyLineInfoResult.getData().getBusiBookNo());
                    if (result == null || result.getStatus() != 1) {
                        throw new MobileStationBizException(result == null ? "调用签派更新路由失败" : (result.getMesasge() == null ? "调用签派更新路由失败" : result.getMesasge()));
                    }
                }
            }
        }

        return modifyLineInfoResult;
    }

}

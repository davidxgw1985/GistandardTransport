package com.gistandard.transport.app.module.track.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.order.module.customer.CustomerTrackService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.service.ComWaybillTraceService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.customer.bean.track.*;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author yjf
 * @ClassName TrackController
 * @Description 运单跟踪
 * @Version 1.0
 * @Date 2016/3/31
 */
@Controller
@RequestMapping(value = AppServerDefine.TRACK_URL)
public class TrackController extends BaseController {
    private static final String TAGS_DESC = "运送历史模块";
    private final Logger logger = LoggerFactory.getLogger(TrackController.class);
    @Autowired
    public CustomerTrackService customerTrackService;

    @Autowired
    public ComWaybillTraceService comWaybillTraceService;
    



    @ApiOperation(value = "客户下单运送历史查询接口-V1.0.1", httpMethod = "POST", response = TrackQueryResult.class,   tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单运送历史查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public TrackQueryResult query(@RequestBody TrackQueryReq req) {
        logger.info("客户下单运送历史查询接口 query{}", JSON.toJSONString(req));
        TrackQueryResult res = new TrackQueryResult();
        try {
            List<TrackQueryRes> comWaybillTraceList = customerTrackService.query(req.getBusiBookNo());
            res.setData(comWaybillTraceList);
        } catch (MobileStationBizException e) {
            e.printStackTrace();
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }

        return res;
    }

    @ApiOperation(value = "运送历史查询接口-V1.0.1", httpMethod = "POST", response = WaybillTraceResult.class,   tags = TAGS_DESC,
            produces = "application/json", notes = "运送历史查询")
    @RequestMapping(value = "/queryWabillList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public WaybillTraceResult queryWabillList(@RequestBody TrackQueryReq req) {
        WaybillTraceResult res = new WaybillTraceResult();
        try {
            ComWaybillTrace comWaybillTrace=new ComWaybillTrace();
            comWaybillTrace.setHubNo(req.getBusiBookNo());
            List<ComWaybillTrace> comWaybillTraceList = comWaybillTraceService.queryWaybillListByCondition(comWaybillTrace);
            res.setData(comWaybillTraceList);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ApiOperation(value = "订单当前坐标位置查询接口-V1.0.1", httpMethod = "POST", response = QueryOrderCurrInfoRes.class,   tags = TAGS_DESC,
            produces = "application/json", notes = "订单当前坐标位置")
    @RequestMapping(value = "/queryOrderCurrInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryOrderCurrInfoRes queryOrderCurrInfo(@RequestBody QueryOrderCurrInfoReq req) {
        logger.info("订单当前坐标位置查询接口 queryOrderCurrInfo{}", JSON.toJSONString(req));
        QueryOrderCurrInfoRes res = customerTrackService.queryOrderCurrInfo(req);
        logger.info("订单当前坐标位置查询接口 queryOrderCurrInfo res{}", JSON.toJSONString(res));
        return res;
    }
}

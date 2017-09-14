package com.gistandard.transport.app.module.track.action;

import com.gistandard.transport.order.module.customer.bean.track.TrackQueryReq;
import com.gistandard.transport.order.module.customer.bean.track.TrackQueryRes;
import com.gistandard.transport.order.module.customer.bean.track.TrackQueryResult;
import com.gistandard.transport.order.module.customer.CustomerTrackService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by m on 2016/10/7.
 */
@Controller
@RequestMapping(value = AppServerDefine.CUSTOMER_TRACK_URL)
public class CustomerTrackController extends BaseController {
    private static final String TAGS_DESC = "客户下单运送历史模块";
    @Autowired
    public CustomerTrackService customerTrackService;

    @ApiOperation(value = "客户下单运送历史查询接口-V1.0.1", httpMethod = "POST", response = TrackQueryResult.class,   tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单运送历史查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public TrackQueryResult query(@RequestBody TrackQueryReq req) {
        TrackQueryResult res = new TrackQueryResult();
        try {
            List<TrackQueryRes> comWaybillTraceList = customerTrackService.query(req.getBusiBookNo());
            res.setData(comWaybillTraceList);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }
}


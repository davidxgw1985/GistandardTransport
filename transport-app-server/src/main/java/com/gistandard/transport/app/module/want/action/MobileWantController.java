package com.gistandard.transport.app.module.want.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.order.module.mobilestation.bean.StationReq;
import com.gistandard.transport.order.module.mobilestation.bean.userorder.GetNearStationReq;
import com.gistandard.transport.order.module.mobilestation.bean.want.QueryWantListReq;
import com.gistandard.transport.order.module.mobilestation.bean.want.QueryWantListResult;
import com.gistandard.transport.order.module.mobilestation.bean.want.WantInfoReq;
import com.gistandard.transport.order.module.mobilestation.service.MobileUserOrderService;
import com.gistandard.transport.order.module.mobilestation.service.MobileWantService;
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

/**
 * @author xgw
 * @ClassName MobileWantController
 * @Description 我要接单、我要运输
 * @Version 1.0
 * @Date 2016-7-20
 */
@Controller
@RequestMapping(value = AppServerDefine.WANT_URL)
public class MobileWantController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MobileWantController.class);
    @Autowired
    private MobileWantService mobileWantService;
    @Autowired
    private MobileUserOrderService mobileUserOrderService;

    /**
     * 我要接单、我要运输列表查询
     *
     * @param queryWantListReq
     * @throws Exception
     */
    @ApiOperation(value = "我要接单、我要运输列表查询-V1.0.1", httpMethod = "POST", response = QueryWantListResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryWantList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryWantListResult queryWantList(@RequestBody QueryWantListReq queryWantListReq) throws Exception {
        return mobileWantService.queryWantList(queryWantListReq);
    }

    /**
     * 我要接单、我要运输新增接口
     *
     * @param wantInfoReq
     * @throws Exception
     */
    @ApiOperation(value = "我要接单、我要运输新增接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/saveWantInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult saveWantInfo(@RequestBody WantInfoReq wantInfoReq) throws Exception {
        return mobileWantService.saveWantInfo(wantInfoReq);
    }

    /**
     * 我要接单和我要运输删除接口
     *
     * @param wantInfoReq
     */
    @ApiOperation(value = "我要接单和我要运输删除接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/deleteWantInfo", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult deleteWantInfo(@RequestBody WantInfoReq wantInfoReq) throws Exception {
        return mobileWantService.deleteWantInfo(wantInfoReq);
    }

    /**
     * 查询站点
     *
     * @param jsonStr
     */
    @ApiOperation(value = "查询站点-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/getStation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public void getStation(@RequestBody String jsonStr) throws Exception{
        logger.info("我要接单和我要运输，查询站点" + jsonStr);
        StationReq stationReq = JSON.parseObject(jsonStr, StationReq.class);
        Object res = null;
        if (stationReq.getAddressId()==null||stationReq.getAddressId().length()==0){
            GetNearStationReq getNearStationReq = JSON.parseObject(jsonStr, GetNearStationReq.class);
            res = mobileUserOrderService.getNearStation(getNearStationReq);
        }else{
            res = mobileWantService.getStation(stationReq);
        }
        writeJson(res);
    }

}

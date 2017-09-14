package com.gistandard.transport.app.module.gps.action;

import com.alibaba.dubbo.common.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.module.gps.bean.GpsTokenResult;
import com.gistandard.transport.app.module.gps.bean.GpsTrackData;
import com.gistandard.transport.app.module.gps.bean.GpsTrackDataResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.gps.bean.GpsTrackReq;
import com.gistandard.transport.gps.bean.TokenReq;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.gps.service.GpsLogService;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
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
 * Created by yjf on 2016/9/30.
 */
@Controller
@RequestMapping(value = AppServerDefine.GPS_URL)
public class GpsController extends BaseController {
    private static final String TAGS_DESC = "GPS模块";

    private static final Logger logger = LoggerFactory.getLogger(GpsController.class);
    @Autowired
    private PnWebService pnWebService;

    @Autowired
    private GpsLogService  gpsLogService;


    @ApiOperation(value = "获取GPS Token接口-V1.0.1", httpMethod = "POST", response = GpsTokenResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "根据帐号获取GPS Token，帐号必填")
    @RequestMapping(value = "/getGpsToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GpsTokenResult query(@RequestBody TokenReq req) {
        logger.debug("getGpsToken Req={}",  JSON.toJSONString(req));
        GpsTokenResult gpsTokenResult = new GpsTokenResult();
        gpsTokenResult.setRetCode(AppServerDefine.FAILURE);
        String getGpsTokenResult = pnWebService.getGpsToken(req.getCode());
        gpsTokenResult.setData(getGpsTokenResult);
        gpsTokenResult.setRetCode(AppServerDefine.SUCCESS);

        return gpsTokenResult;
    }

    @ApiOperation(value = "获取GPS 轨迹接口-V1.0.1", httpMethod = "POST", response = GpsTrackDataResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "根据查询条件获取GPS 轨迹，查询条件必填")
    @RequestMapping(value = "/getGpsTrack", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GpsTrackDataResult getGpsTrack(@RequestBody GpsTrackReq req) {
        GpsTrackDataResult res = new GpsTrackDataResult();
        String gpsTrackResult = pnWebService.getAllGiLocationByAllMapOfCodeApptagTime(req.getEvents());
        GpsTrackData gpsTrackData = new GpsTrackData();
        gpsTrackData.setData(gpsTrackResult);
        res.setData(gpsTrackData);
        res.setRetCode(AppServerDefine.SUCCESS);
        return res;
    }

    @ApiOperation(value = "gps位置上传接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "gps位置上传")
    @RequestMapping(value = "/gpsDataDeal/androidUploadGiLocationBd2", method = {RequestMethod.GET,RequestMethod.POST}, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult androidUploadGiLocationBd2(String param) {
//        logger.debug("androidUploadGiLocationBd2 Req={}",  param);
        AppBaseResult res = new AppBaseResult();
        if (param==null || param.trim().length()==0){
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("传入的参数param为 null !");
        } else {
            gpsLogService.androidUploadGiLocationBd2(param);
        }
        return res;
    }
}

package com.gistandard.transport.app.module.courier.action;

import com.gistandard.platform.pojo.login.app.AppLoginInfo;
import com.gistandard.transport.app.module.courier.bean.*;
import com.gistandard.transport.app.module.gps.bean.GpsTokenResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.oauth2.SecurityUser;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.common.courier.bean.*;
import com.gistandard.transport.system.common.courier.service.CourierService;
import com.gistandard.transport.system.common.station.bean.Station;
import com.gistandard.transport.system.common.station.bean.StationQueryNearbyReq;
import com.gistandard.transport.system.common.station.bean.StationQueryReq;
import com.gistandard.transport.system.common.station.service.CustomerStationService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kongxm
 * @ClassName CourierController
 * @Description 快递员查询管理
 * @Version 1.0
 * @Date 2016/1/26
 */
@Controller
@RequestMapping(value = AppServerDefine.COURIER_URL)
public class CourierController extends BaseController {

    private static final String TAGS_DESC = "快递员管理模块";

    @Autowired
    public CourierService courierService;

    @Autowired
    public CustomerStationService customerStationService;

    @ResponseBody
    @ApiOperation(value = "客户下单APP关注的快递员接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP关注的快递员")
    @RequestMapping(value = "/follow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult follow(@RequestBody CourierFollowReq req, @AuthenticationPrincipal SecurityUser securityUser) {
        AppBaseResult res = new AppBaseResult(req);
        if(securityUser!=null) {
            req.setAcctUsername(((AppLoginInfo)securityUser.getInfo()).getAcctUsername());
            req.setAccountId(((AppLoginInfo)securityUser.getInfo()).getAccountId());
        }
        try {
            courierService.follow(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP取消关注快递员接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP取消关注快递员")
    @RequestMapping(value = "/unfollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult unfollow(@RequestBody CourierUnFollowReq req, @AuthenticationPrincipal SecurityUser securityUser) {
        AppBaseResult res = new AppBaseResult(req);
        if(securityUser!=null) {
            req.setAcctUsername(((AppLoginInfo)securityUser.getInfo()).getAcctUsername());
            req.setAccountId(((AppLoginInfo)securityUser.getInfo()).getAccountId());
        }
        try {
            courierService.unfollow(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP查询关注快递员接口-V1.0.1", httpMethod = "POST", response = queryFollowResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP查询关注快递员")
    @RequestMapping(value = "/queryFollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public queryFollowResult queryFollow(@RequestBody CourierQueryFllowReq req, @AuthenticationPrincipal SecurityUser securityUser) {
        queryFollowResult res = new queryFollowResult(req);
        if(securityUser!=null) {
            req.setAcctUsername(((AppLoginInfo)securityUser.getInfo()).getAcctUsername());
            req.setAccountId(((AppLoginInfo)securityUser.getInfo()).getAccountId());
        }
        try {
            List<Courier> CourierList = courierService.queryFollow(req);
            res.setData(CourierList);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }
}

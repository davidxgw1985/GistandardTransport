package com.gistandard.transport.app.module.station.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.req.AppBasePagerRequest;
import com.gistandard.transport.app.module.station.bean.*;
import com.gistandard.transport.app.module.station.service.StationService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.CustomerDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.common.courier.bean.Courier;
import com.gistandard.transport.system.common.courier.bean.CourierQueryReq;
import com.gistandard.transport.system.common.courier.service.CourierService;
import com.gistandard.transport.system.common.station.bean.*;
import com.gistandard.transport.system.common.station.service.CustomerStationService;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kongxm
 * @ClassName StationController
 * @Description 供应商管理
 * @Version 1.0
 * @Date 2016/1/26
 */
@Controller
@RequestMapping(value = AppServerDefine.STATION_URL)
public class CustomerStationController extends BaseController {
    private static final String TAGS_DESC = "供应商管理模块";
    private static final Logger logger = LoggerFactory.getLogger(CustomerStationController.class);
    @Autowired
    public CustomerStationService customerStationService;

    @Autowired
    public CourierService courierService;

    @Autowired
    public StationService stationService;

    /**
     * 下单自理交件 咪站列表查询
     * 下单寄件 快递员、咪站列表查询
     * 同城专送下单寄件 快递员、司机列表查询
     * @param req
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "客户下单APP供应商查询接口-V1.0.1", httpMethod = "POST", response = StationQueryResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP供应商查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public StationQueryResult query(@RequestBody StationQueryReq req) {
        logger.info("客户下单APP供应商查询 {}", JSON.toJSONString(req));
        StationQueryResult res = new StationQueryResult(req);
        try {
            StationQueryData data = new StationQueryData();
            //快递员/司机(个体车主)
            int recordCount = 0;
            CourierQueryReq reqCourier = new CourierQueryReq();
            BeanUtils.copyProperties(reqCourier, req);
            reqCourier.setRoleId(req.getRole());
            List<Courier> CourierList = courierService.query(reqCourier);
            data.setCourier(CourierList);
            if (CourierList != null && CourierList.size() > 0) {
                recordCount = CourierList.size();
            }
            res.setRecordCount(recordCount);
            res.setData(data);
        } catch (IllegalAccessException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        } catch (InvocationTargetException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP查询附近的供应商接口-V1.0.1", httpMethod = "POST", response = QueryNearbyResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP查询附近的供应商")
    @RequestMapping(value = "/queryNearby", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryNearbyResult queryNearby(@RequestBody StationQueryNearbyReq req) {
        QueryNearbyResult res = new QueryNearbyResult();
        List<Station> stationList = customerStationService.queryNearby(req);
        res.setData(stationList);
        int recordCount = customerStationService.queryNearbyCount(req);
        res.setRecordCount(recordCount);
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP查询附近的供应商接口-V1.0.1", httpMethod = "POST", response = QueryMandWstationResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP查询附近的供应商")
    @RequestMapping(value = "/queryMandWstation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryMandWstationResult queryMandWstation(@RequestBody QueryMandWstationReq req) throws MobileStationBizException {
        QueryMandWstationResult res = new QueryMandWstationResult();
        List<CustomerListBean> customerListBeanList = stationService.queryMandWstation(req);
        res.setData(customerListBeanList);
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP查询会经过咪站停靠点的所有咪站-V1.0.1", httpMethod = "POST", response = QueryMandWstationResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP查询附近的供应商")
    @RequestMapping(value = "/getAllMiMovingByMiStopId", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryMandWstationResult getAllMiMovingByMiStopId(@RequestBody GetAllMiMovingByMiStopIdReq req) throws MobileStationBizException {
        QueryMandWstationResult res = new QueryMandWstationResult();
        List<CustomerListBean> customerListBeanList = stationService.getAllMiMovingByMiStopId(req);
        res.setData(customerListBeanList);
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP关注供应商接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP关注供应商")
    @RequestMapping(value = "/follow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult follow(@RequestBody StationFollowReq req) {
        AppBaseResult res = new AppBaseResult(req);
        try {
            customerStationService.follow(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP取消关注供应商接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP取消关注供应商")
    @RequestMapping(value = "/unfollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult unfollow(@RequestBody StationUnFollowReq req) {
        AppBaseResult res = new AppBaseResult(req);
        try {
            customerStationService.unfollow(req);
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户下单APP查询关注供应商接口-V1.0.1", httpMethod = "POST", response = QueryFollowResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单APP查询关注供应商")
    @RequestMapping(value = "/queryFollow", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryFollowResult queryFollow(@RequestBody StationQueryFollowReq req) {
        QueryFollowResult res = new QueryFollowResult(req);

        try {
            List<Station> stationList = null;//= customerStationService.queryFollow(req);
            List<Courier> CourierList = courierService.queryFollow(req);
            Map<String, Object> map = new HashMap();
            map.put("station", stationList);
            map.put("courier", CourierList);
            res.setData(map);
            res.setRecordCount((stationList == null ? 0 : stationList.size()) + (CourierList == null ? 0 : CourierList.size()));
        } catch (MobileStationBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ResponseBody
    @ApiOperation(value = "客户找人下单 商业中心业务员查询接口-V1.0.1", httpMethod = "POST", response = QueryBusinessRelationResult.class,
            tags = TAGS_DESC,
            produces = "application/json", notes = "客户下单商业中心业务员查询")
    @RequestMapping(value = "/queryBusinessRelation", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public QueryBusinessRelationResult queryBusinessRelation(@RequestBody QueryBusinessRelationReq req) {
        return customerStationService.queryBusinessRelation(req);
    }
}


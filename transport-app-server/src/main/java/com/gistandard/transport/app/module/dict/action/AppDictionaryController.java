package com.gistandard.transport.app.module.dict.action;

import com.gistandard.transport.app.module.dict.bean.*;
import com.gistandard.transport.app.module.dict.service.AppDictionaryService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.req.AppBaseRequest;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by yujie on 2016/9/29.
 */
@Controller
@RequestMapping(value = AppServerDefine.DICTIONARY_URL)
@Api(value = "数据字典模块", tags = "数据字典模块" )
public class AppDictionaryController extends BaseController{

    @Autowired
    private AppDictionaryService appDictionaryService;

    @RequestMapping(value = "/queryDictionary", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询数据字典-V1.0.1", httpMethod = "POST", response = QueryDictionaryResult.class,
            produces = "application/json", notes = "参数: reqId{请求reqId,Android请求需要IOS不需要}<br>" +
            "dicType{字典类型 1放弃订单理由;2订单失败理由;3;货物类型;4货物数量单位;5价格单位;6银行}<br>" +
            "characterType{简繁体类型:1简体；2繁体}")
    @ResponseBody
    public QueryDictionaryResult queryDictionary(@RequestBody QueryDictionaryParam queryDictionaryParam) {
        QueryDictionaryResult baseResBean = new QueryDictionaryResult();
        try {
            if (queryDictionaryParam != null) {
                baseResBean = appDictionaryService.queryDictionary(queryDictionaryParam);
            }
        } catch (Exception e) {
            baseResBean.setRetCode(AppServerDefine.FAILURE);
            baseResBean.setRetMsg(e.getMessage());
        }
        return baseResBean;
    }

    @RequestMapping(value = "/queryReason", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询理由字典-V1.0.1", httpMethod = "POST", response = QueryReasonResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryReasonResult queryReason(@RequestBody QueryReasonParam QueryReasonParam) {
        return appDictionaryService.queryReason(QueryReasonParam);
    }

    @RequestMapping(value = "/queryGoodsType", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询货物类型-V1.0.1", httpMethod = "POST", response = QueryGoodsTypeResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryGoodsTypeResult queryGoodsType(@RequestBody QueryGoodsTypeParam queryGoodsTypeParam) {
        return appDictionaryService.queryGoodsType(queryGoodsTypeParam);
    }

    @RequestMapping(value = "/queryUnit", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询单位字典-V1.0.1", httpMethod = "POST", response = QueryUnitResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryUnitResult queryUnit(@RequestBody QueryUnitParam queryUnitParam) {
        return appDictionaryService.queryUnit(queryUnitParam);
    }

    @RequestMapping(value = "/queryCurrency", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询币制-V1.0.1", httpMethod = "POST", response = QueryCurrencyResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryCurrencyResult queryCurrency(@RequestBody AppBaseRequest appBaseRequest) {
        return appDictionaryService.queryCurrency(appBaseRequest);
    }

    @RequestMapping(value = "/queryTackoutGoodsType", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询同城外卖货物类型-V1.0.1", httpMethod = "POST", response = QueryTackoutGoodsTypeResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryTackoutGoodsTypeResult queryTackoutGoodsType(@RequestBody AppBaseRequest appBaseRequest) {
        return appDictionaryService.queryTackoutGoodsType(appBaseRequest);
    }

    @RequestMapping(value = "/queryAllType", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "查询普通字典-V1.0.1", httpMethod = "POST", response = QueryAllTypeResult.class,
            produces = "application/json")
    @ResponseBody
    public QueryAllTypeResult queryAllType(@RequestBody QueryAllTypeParam queryAllTypeParam) {
        return appDictionaryService.queryAllType(queryAllTypeParam);
    }

}

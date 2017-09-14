package com.gistandard.transport.app.module.address.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.MobileStationRetCode;
import com.gistandard.transport.system.common.bean.QueryAddressReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.bind.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gistandard.transport.app.module.address.bean.AddressAddResultBean;
import com.gistandard.transport.app.module.address.bean.AddressDeleteReq;
import com.gistandard.transport.app.module.address.bean.AddressQueryResultBean;
import com.gistandard.transport.app.module.address.bean.AddressRes;
import com.gistandard.transport.app.module.address.service.CustomerAddressInfoService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.operatelog.annotation.SysControllerLog;
import com.gistandard.transport.system.common.bean.AddressQueryRes;
import com.gistandard.transport.system.common.bean.AddressReq;
import com.gistandard.transport.system.common.controller.BaseController;

/**
 * @author kongxm
 * @ClassName AddressController
 * @Description 用户地址管理
 * @Version 1.0
 * @Date 2016/1/26
 */
@Controller
@RequestMapping(value = AppServerDefine.ADDRESS_URL)
@Api(value = "地址簿模块", tags = "地址簿模块")
public class CustomerAddressInfoController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerAddressInfoController.class);
    @Autowired
    public CustomerAddressInfoService customerAddressInfoService;

    @ApiOperation(value = "客户下单APP地址新增-V1.0.1", httpMethod = "POST", response = AddressAddResultBean.class, produces = "application/json;charset=UTF-8", notes = "客户下单APP地址新增，其中reqId安卓必填、账户ID、姓名、手机、国家、省、市、区、发货地址、详细地址、经度、纬度、地址类型必填！")
    @SysControllerLog(description = "客户下单APP地址新增")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public AddressAddResultBean add(@RequestBody AddressReq addressReq) {
        logger.info("客户下单APP地址新增 add {}", JSON.toJSONString(addressReq));
        AddressAddResultBean res = new AddressAddResultBean();
        res.setReqId(addressReq.getReqId());
        // 设置操作结果
        try {
            AddressRes addressRes = customerAddressInfoService.addAddress(addressReq);
            res.setData(addressRes);
        } catch (CustomerBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;

    }

    @ApiOperation(value = "客户下单APP地址删除-V1.0.1", httpMethod = "POST", response = AppBaseResult.class, produces = "application/json;charset=UTF-8", notes = "客户下单APP地址删除，删除地址的id必填！")
    @SysControllerLog(description = "客户下单APP地址删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public AppBaseResult delete(@RequestBody
                                AddressDeleteReq addressDeleteReq) {
        AppBaseResult res = new AppBaseResult();
        res.setReqId(addressDeleteReq.getReqId());
        int size = customerAddressInfoService.delete(addressDeleteReq.getIds());
        res.setRetMsg("已删除" + size + "条数据");
        return res;
    }

    @ApiOperation(value = "客户下单APP地址更新-V1.0.1", httpMethod = "POST", response = AppBaseResult.class, produces = "application/json;charset=UTF-8", notes = "客户下单APP地址更新，更新地址的id必填！")
    @SysControllerLog(description = "客户下单APP地址更新")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public AppBaseResult update(@RequestBody AddressReq addressReq) {
        AppBaseResult res = new AppBaseResult();
        res.setReqId(addressReq.getReqId());
        try {
            customerAddressInfoService.update(addressReq);
        } catch (CustomerBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }
        return res;
    }

    @ApiOperation(value = "客户下单APP地址查询-V1.0.1", httpMethod = "POST", response = AddressQueryResultBean.class, produces = "application/json;charset=UTF-8", notes = "客户下单APP地址查询，accountId必填！")
    @SysControllerLog(description = "客户下单APP地址查询")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @ResponseBody
    public AddressQueryResultBean query(@RequestBody QueryAddressReq addressReq) {
        AddressQueryResultBean res = new AddressQueryResultBean();
        Integer accountId = addressReq.getAccountId();
        if(addressReq.getCompanyAccountId() != null){
            //如果企业号不为空，说明是企业方式登录
            accountId = addressReq.getCompanyAccountId();
        }
        if (accountId == null) {
            res.setRetCode(MobileStationRetCode.QUERY_ADDRESS_ERROR_INVALID_ACCOUNT.getValue());
            res.setRetMsg(MobileStationRetCode.QUERY_ADDRESS_ERROR_INVALID_ACCOUNT.getName());
            return res;
        }
        res.setReqId(addressReq.getReqId());
        // 设置操作结果
        try {
            List<AddressQueryRes> addressList = customerAddressInfoService.queryByAccountId(addressReq);
            res.setData(addressList);
            Integer totalCount = customerAddressInfoService.recordCount(addressReq);
            res.setRecordCount(totalCount);
        } catch (CustomerBizException e) {
            res.setRetCode(-1);
            res.setRetMsg(e.getMessage());
        }

        return res;
    }

}

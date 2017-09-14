package com.gistandard.transport.app.module.address.action;

import com.gistandard.transport.app.module.address.bean.*;
import com.gistandard.transport.app.module.address.service.TownService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.entity.bean.ComTown;
import com.gistandard.transport.base.operatelog.annotation.SysControllerLog;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author kongxm
 * @ClassName AddressController
 * @Description 街道查询模块
 * @Version 1.0
 * @Date 2016/1/26
 */
@Controller
@RequestMapping(value = AppServerDefine.COMMON_TOWN)
@Api(value = "街道查询模块", tags = "街道查询模块")
public class TownController extends BaseController {

    @Autowired
    public TownService townService;

    @ApiOperation(value = "区县查询街道-V1.0.1", httpMethod = "POST", response = AddressAddResultBean.class, produces = "application/json;charset=UTF-8", notes = "客户下单APP地址新增，其中reqId安卓必填、账户ID、姓名、手机、国家、省、市、区、发货地址、详细地址、经度、纬度、地址类型必填！")
    @SysControllerLog(description = "区县查询街道")
    @RequestMapping(value = "/getTownList", method = RequestMethod.POST)
    @ResponseBody
    public TownCountyResultBean getTownList(@RequestBody TownCountyReq townCountyReq) {
        TownCountyResultBean res = new TownCountyResultBean();
        List<ComTown> comTownList = townService.getTownListByConutyId(townCountyReq.getCountyId());
        if(comTownList == null){
            res.setRetCode(SysResult.FAILED.getValue());
            res.setRetMsg("当前区域没有街道！");
        }
        res.setData(comTownList);
        return res;

    }



}

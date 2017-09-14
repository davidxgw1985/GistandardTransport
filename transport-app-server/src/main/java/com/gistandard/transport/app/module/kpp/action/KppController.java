package com.gistandard.transport.app.module.kpp.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.module.kpp.service.KppService;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.entity.bean.MobileMoudleRel;
import com.gistandard.transport.app.module.kpp.bean.QueryKppParam;
import com.gistandard.transport.app.module.kpp.bean.QueryKppResult;
import com.gistandard.transport.base.entity.service.MobileMoudleRelService;
import com.gistandard.transport.system.common.controller.BaseController;
import io.swagger.annotations.Api;
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

import java.util.List;

/**
 * Created by yujie on 2016/9/30.
 */
@Controller
@RequestMapping(value = AppServerDefine.KPP_URL)
@Api(value = "KPP管理模块", tags = "KPP管理模块" )
public class KppController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(KppController.class);
    @Autowired
    private MobileMoudleRelService mobileMoudleRelService;

    @Autowired
    private KppService kppService;
    /**
     * 查询mobile用户的KPP模块
     */
    @RequestMapping(value = "/queryMobileMoudleRel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户KPP查询接口-V1.0.1", httpMethod = "POST", response = QueryKppResult.class,
            produces = "application/json", notes = "查询参数 moudleCode {对应KPP的模块代码}<br>" +
            "moudleName {对应KPP的模块名称} <br>" +
            "moudleStatus {模块接单状态,1接单状态 0拒绝接单}<br>" +
            "accountId {账号ID}<br>" +
            "acctUsername {账号用户名}")
    @ResponseBody
    public QueryKppResult queryMobileMoudleRel(@RequestBody QueryKppParam queryKppParam) throws Exception {
        QueryKppResult res = new QueryKppResult(queryKppParam);
        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
        BeanUtils.copyProperties(mobileMoudleRel, queryKppParam);
        mobileMoudleRel.setCompanyCode(queryKppParam.getCompanyAcctUsername());
        mobileMoudleRel.setCompanyId(queryKppParam.getCompanyAccountId());
        List<MobileMoudleRel> mobileMoudleRels = mobileMoudleRelService.queryMobileMoudleRel(mobileMoudleRel);
        res.setData(mobileMoudleRels);
        return res;
    }

    @RequestMapping(value = "/addMobileMoudleRel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户添加KPP模块接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "参数 moudleCode {对应KPP的模块代码}<br>" +
            "moudleName {对应KPP的模块名称} <br>" +
            "moudleStatus {模块接单状态,1接单状态 0拒绝接单}<br>" +
            "accountId {账号ID}<br>" +
            "acctUsername {账号用户名}")
    @ResponseBody
    public AppBaseResult addMobileMoudleRel(@RequestBody QueryKppParam queryKppParam) throws Exception{
        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
        BeanUtils.copyProperties(mobileMoudleRel, queryKppParam);
        mobileMoudleRel.setCompanyCode(queryKppParam.getCompanyAcctUsername());
        mobileMoudleRel.setCompanyId(queryKppParam.getCompanyAccountId());
        AppBaseResult res = kppService.addMobileMoudleRel(mobileMoudleRel);
        res.setReqId(queryKppParam.getReqId());
        return res;
    }

    /**
     * 用户修改模块接单状态
     * 用户修改共享位置状态
     */
    @RequestMapping(value = "/updateMobileMoudleRel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户修改模块接单状态-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "参数 moudleCode {对应KPP的模块代码}<br>" +
            "moudleName {对应KPP的模块名称} <br>" +
            "moudleStatus {模块接单状态,1接单状态 0拒绝接单}<br>" +
            "accountId {账号ID}<br>" +
            "acctUsername {账号用户名}")
    @ResponseBody
    public AppBaseResult updateMobileMoudleRel(@RequestBody QueryKppParam queryKppParam) throws Exception{
        logger.debug("用户修改模块接单状态、共享位置状态 updateMobileMoudleRel param:{}", JSON.toJSONString(queryKppParam));
        AppBaseResult res = kppService.updateMobileMoudleRel(queryKppParam);
        return res;
    }

    /**
     * 修改mobile用户的KPP登录状态
     */
    @RequestMapping(value = "/updateMobileMoudleRelStatus", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户修改KPP模块接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "参数 moudleCode {对应KPP的模块代码}<br>" +
            "moudleName {对应KPP的模块名称} <br>" +
            "moudleStatus {模块接单状态,1接单状态 0拒绝接单}<br>" +
            "accountId {账号ID}<br>" +
            "acctUsername {账号用户名}")
    @ResponseBody
    public AppBaseResult updateMobileMoudleRelStatus(@RequestBody QueryKppParam queryKppParam) throws Exception{
        AppBaseResult res = new AppBaseResult(queryKppParam);
//        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
//        BeanUtils.copyProperties(mobileMoudleRel, queryKppParam);
        mobileMoudleRelService.updateMobileMoudleRelStatus(queryKppParam.getAcctUsername(), queryKppParam.getCompanyAcctUsername());
        return res;
    }

    /**
     * 删除mobile用户的KPP模块
     */
    @RequestMapping(value = "/deleteMobileMoudleRel", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "用户删除KPP模块接口-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "参数 moudleCode {对应KPP的模块代码，必填}<br>" +
            "acctUsername {账号用户名，必填}")
    @ResponseBody
    public AppBaseResult deleteMobileMoudleRel(@RequestBody QueryKppParam queryKppParam) throws Exception{
        AppBaseResult res = new AppBaseResult(queryKppParam);
        MobileMoudleRel mobileMoudleRel = new MobileMoudleRel();
        BeanUtils.copyProperties(mobileMoudleRel, queryKppParam);
        mobileMoudleRel.setCompanyCode(queryKppParam.getCompanyAcctUsername());
        mobileMoudleRel.setCompanyId(queryKppParam.getCompanyAccountId());
        mobileMoudleRelService.deleteMobileMoudleRel(mobileMoudleRel);
        return res;
    }

}

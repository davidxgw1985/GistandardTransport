package com.gistandard.transport.app.module.mistation.accept.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.base.define.BusinessDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.dao.ComWaybillTraceDao;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.accept.service.MiStationAcceptService;
import com.gistandard.transport.order.module.mobilestation.bean.*;
import com.gistandard.transport.order.module.mobilestation.service.MobileAcceptOrderService;
import com.gistandard.transport.order.webservice.client.merchant.order.BaseRequestResult;
import com.gistandard.transport.order.webservice.client.merchant.order.MobileRecOrderWebService;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.gps.bean.*;
import com.gistandard.transport.system.gps.bean.DataResult;
import com.gistandard.transport.system.gps.service.GpsOrderService;
import com.gistandard.transport.tools.util.StringUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;


/**
 * @author xgw
 * @ClassName MiStationAcceptController
 * @Description 咪站接单模块订单操作
 * @Version 3.0
 * @Date 2016-10-11
 */
@Controller
@RequestMapping(value = AppServerDefine.MI_ACCEPT_ORDER_URL)
public class MiStationAcceptController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(MiStationAcceptController.class);

    private static final String TAGS_DESC = "咪站-接单模块";

    @Autowired
    private MiStationAcceptService miStationAcceptService;
    @Autowired
    private GpsOrderService gpsOrderService;
    @Autowired
    private ComWaybillTraceDao comWaybillTraceDao;
    @Autowired
    private MobileAcceptOrderService mobileAcceptOrderService;

    @Autowired
    private MobileRecOrderWebService mobileRecOrderWebService;

    @ApiOperation(value = "咪站接单操作-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC,response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/acceptOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult acceptOrder(@RequestBody MobileStationAcceptOrderReq acceptOrderReq) throws Exception {
        logger.debug("咪站-接单 acceptOrder param:{}", JSON.toJSONString(acceptOrderReq));
        AppBaseResult res;
        //接单前，先判断是否有GPS坐标上传，如果没有，不允许接单
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            res = new AppBaseResult(acceptOrderReq);
//            res.setRetCode(SystemDefine.FAILURE);
//            res.setRetMsg(dataResult.getMessage());
//            return res;
//        }
        //接单前判断是否有商管指派单未完成，如果有未完成的，不允许接单
        int count = mobileAcceptOrderService.getMerchantOrderCount(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
        if (count > 0) {
            res = new AppBaseResult(acceptOrderReq);
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("您还有未处理的商管指派单，请处理完后再接单！");
            return res;
        }
        res = miStationAcceptService.acceptOrder(acceptOrderReq);
        if(res!=null && res.getRetCode()== SysResult.SUCCESS.getValue() && acceptOrderReq.getRoleId()!=null &&!StringUtil.isEmpty(acceptOrderReq.getBusiBookNo())){
            ComWaybillTrace tmp = new ComWaybillTrace();
            tmp.setAcctUsername(acceptOrderReq.getAcctUsername());
            tmp.setBusiBookNo(acceptOrderReq.getBusiBookNo());
            ComAccount account = acceptOrderReq.getAppLoginInfo().getComAccount();
            if(account!=null) {
                tmp.setRealName(account.getRealName());
                tmp.setRemark(SysAccountRole.getName(acceptOrderReq.getRoleId().intValue())+account.getRealName()+"已接单，联系电话："+account.getTelephone());
            }
            tmp.setGrade(BusinessDefine.GRADE);
            tmp.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
            tmp.setRoleId(acceptOrderReq.getRoleId());
            tmp.setStaDate(new Date());
            comWaybillTraceDao.insert(tmp);
        }
        return res;
    }

    @ApiOperation(value = "咪站批量接单操作-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC,response = MobileStationBatchAcceptOrderResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/batchAcceptOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public MobileStationBatchAcceptOrderResult batchAcceptOrder(@RequestBody MobileStationBatchAcceptOrderReq acceptOrderReq) throws Exception {
        logger.debug("咪站-批量接单 batchAcceptOrder param:{}", JSON.toJSONString(acceptOrderReq));
        MobileStationBatchAcceptOrderResult res = new MobileStationBatchAcceptOrderResult(acceptOrderReq);
        //接单前，先判断是否有GPS坐标上传，如果没有，不允许接单
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            res.setRetCode(SystemDefine.FAILURE);
//            res.setRetMsg(dataResult.getMessage());
//            return res;
//        }
        //接单前判断是否有商管指派单未完成，如果有未完成的，不允许接单
        int count = mobileAcceptOrderService.getMerchantOrderCount(acceptOrderReq.getAcctUsername(), acceptOrderReq.getRoleId());
        if (count > 0) {
            res.setRetCode(SystemDefine.FAILURE);
            res.setRetMsg("您还有未处理的商管指派单，请处理完后再接单！");
            return res;
        }
        res.setData(miStationAcceptService.batchAcceptOrder(acceptOrderReq));
        if(res.getData()!=null &&res.getData().getSuccessCount()>=1){
           for(MobileStationAcceptOrderCustomReq mobileStationAcceptOrderCustomReq: res.getData().getSuccessList()){
               if(!StringUtil.isEmpty(mobileStationAcceptOrderCustomReq.getBusiBookNo())&& acceptOrderReq.getRoleId()!=null) {
                   ComWaybillTrace tmp = new ComWaybillTrace();
                   tmp.setAcctUsername(acceptOrderReq.getAcctUsername());
                   tmp.setBusiBookNo(mobileStationAcceptOrderCustomReq.getBusiBookNo());
                   ComAccount account = acceptOrderReq.getAppLoginInfo().getComAccount();
                   if (account != null) {
                       tmp.setRealName(account.getRealName());
                       tmp.setRemark(SysAccountRole.getName(acceptOrderReq.getRoleId().intValue()) + account.getRealName() + "已接单，联系电话：" + account.getTelephone());
                   }
                   tmp.setGrade(BusinessDefine.GRADE);
                   tmp.setExecCode(WayBillStatusDefine.MS_AGREE_O.getIntValue());
                   tmp.setRoleId(acceptOrderReq.getRoleId());
                   tmp.setStaDate(new Date());
                   comWaybillTraceDao.insert(tmp);
               }
           }
        }
        logger.debug("咪站-批量接单 batchAcceptOrder param result:{}", JSON.toJSONString(res));
        return res;
    }

    @ApiOperation(value = "咪站拒绝订单-V1.0.1", httpMethod = "POST",
            tags = TAGS_DESC,response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/refuseOrder", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult refuseOrder(@RequestBody MobileStatusOperateReq mobileStatusOperateReq) throws Exception {
        logger.debug("咪站-拒绝订单 refuseOrder param:{}", JSON.toJSONString(mobileStatusOperateReq));
        return miStationAcceptService.refuseOrder(mobileStatusOperateReq);
    }

}

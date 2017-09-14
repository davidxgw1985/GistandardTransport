package com.gistandard.transport.app.module.stock.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.BusinessDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.entity.service.ComAccountService;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.stock.bean.*;
import com.gistandard.transport.order.stock.service.MobileStationStockService;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.system.common.controller.BaseController;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import com.gistandard.transport.system.gps.bean.DataResult;
import com.gistandard.transport.system.gps.service.GpsOrderService;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: xgw
 * @ClassName: MobileStationStockController
 * @Date: 2016/2/15
 * @Description: 库存管理
 */
@Controller
@RequestMapping(value = AppServerDefine.STOCK_URL)
public class MobileStationStockController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(MobileStationStockController.class);
    @Autowired
    private MobileStationStockService mobileStationStockService;
    @Autowired
    private ComAccountService comAccountService;
    @Autowired
    private GpsOrderService gpsOrderService;

    @ApiOperation(value = "MS出入库-V1.0.1", httpMethod = "POST", response = StockInOutResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/stockInOut", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public StockInOutResult stockInOut(@RequestBody StockInOutReq stockInOutReq) throws Exception {
        return mobileStationStockService.stockInOut(stockInOutReq);
    }

    @ApiOperation(value = "MS(批量)入库、同时做取件操作/(扫描集包入库)-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/batchStockIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public BatchStockInResult batchStockIn(@RequestBody BatchStockInReq batchStockInReq) throws Exception {
        logger.info("batchStockIn request param : {}", JSON.toJSONString(batchStockInReq));
        BatchStockInResult result = new BatchStockInResult();
        //收货前，先判断是否有GPS坐标上传，如果没有，不允许收货
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(batchStockInReq.getAcctUsername(), batchStockInReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            result.setRetCode(SystemDefine.FAILURE);
//            result.setRetMsg(dataResult.getMessage());
//            return result;
//        }
        List<BatchStockInResultBean> errorBeans = new ArrayList<>();
        List<BatchStockInReqBean> batchStockInReqBeans = batchStockInReq.getStockInReqs();
        List<ComWaybillTrace> tmps = new ArrayList<>();
        for (BatchStockInReqBean batchStockInReqBean : batchStockInReqBeans) {
            StockInReq stockInReq = OrderUtils.createStockInReq(batchStockInReq, batchStockInReqBean);
            stockInReq.setScanOrderFrom(1);
            try {
                AppBaseResult res;
                if (stockInReq.getScanBusiBookNo().startsWith("PKG")) {
                    res = mobileStationStockService.stockInPkg(stockInReq);
                } else {
                    res = mobileStationStockService.stockIn(stockInReq);
                    if (res != null && res.getRetCode() == SysResult.SUCCESS.getValue() && stockInReq.getRoleId() != null) {
                        ComWaybillTrace tmp = new ComWaybillTrace();
                        tmp.setAcctUsername(stockInReq.getAcctUsername());
                        tmp.setBusiBookNo(stockInReq.getScanBusiBookNo());
                        ComAccount account = stockInReq.getAppLoginInfo().getComAccount();
                        if (account != null) {
                            tmp.setRealName(account.getRealName());
                            tmp.setRemark(SysAccountRole.getName(stockInReq.getRoleId().intValue()) + account.getRealName() + "已揽件");
                        }
                        tmp.setGrade(BusinessDefine.GRADE);
                        tmp.setExecCode(WayBillStatusDefine.TAKE_SUCCESS.getIntValue());
                        tmp.setRoleId(stockInReq.getRoleId());
                        tmp.setStaDate(new Date());
                        tmps.add(tmp);
                    }
                }
                if (res.getRetCode() == SysResult.FAILED.getValue()) {
                    errorBeans.add((BatchStockInResultBean) new BatchStockInResultBean()
                            .setMsg(res.getRetMsg())
                            .setBusiBookNo(stockInReq.getScanBusiBookNo())
                            .setBusiNoTag(stockInReq.getBusiNoTag()));
                }
            } catch (MobileStationBizException e) {
                logger.error(e.getMessage());
                errorBeans.add((BatchStockInResultBean) new BatchStockInResultBean()
                        .setMsg(e.getMessage())
                        .setBusiBookNo(stockInReq.getScanBusiBookNo())
                        .setBusiNoTag(stockInReq.getBusiNoTag()));
            }
        }
        if (tmps.size() > 0) {
            DisruptorHelper.getInstance().producer(tmps);
        }
        result.setErrorBeans(errorBeans);
        return result;
    }

    @ApiOperation(value = "MS入库、同时做取件操作/(扫描集包入库)-V1.0.1", httpMethod = "POST", response = AppBaseResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/stockIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public AppBaseResult stockIn(@RequestBody StockInReq stockInReq) throws Exception {
        logger.info("stockIn request param : {}", JSON.toJSONString(stockInReq));
        AppBaseResult res;
        //收货前，先判断是否有GPS坐标上传，如果没有，不允许收货
//        DataResult dataResult = gpsOrderService.getGpsOnlineStatus(stockInReq.getAcctUsername(), stockInReq.getRoleId());
//        if (dataResult != null && !dataResult.isSucceed()) {
//            res = new AppBaseResult(stockInReq);
//            res.setRetCode(SystemDefine.FAILURE);
//            res.setRetMsg(dataResult.getMessage());
//            return res;
//        }
        if (stockInReq.getScanBusiBookNo().startsWith("PKG")) {
            res = mobileStationStockService.stockInPkg(stockInReq);
        } else {
            res = mobileStationStockService.stockIn(stockInReq);
            if (res != null && res.getRetCode() == SysResult.SUCCESS.getValue() && stockInReq.getRoleId() != null) {
                List<ComWaybillTrace> tmps = new ArrayList<>();
                ComWaybillTrace tmp = new ComWaybillTrace();
                tmp.setAcctUsername(stockInReq.getAcctUsername());
                tmp.setBusiBookNo(stockInReq.getScanBusiBookNo());
                ComAccount account = comAccountService.queryAccountByAcctUsername(stockInReq.getAcctUsername());
                if (account != null) {
                    tmp.setRealName(account.getRealName());
                    tmp.setRemark(SysAccountRole.getName(stockInReq.getRoleId().intValue()) + account.getRealName() + "已揽件");
                }
                tmp.setGrade(BusinessDefine.GRADE);
                tmp.setExecCode(WayBillStatusDefine.TAKE_SUCCESS.getIntValue());
                tmp.setRoleId(stockInReq.getRoleId());
                tmp.setStaDate(new Date());
                tmps.add(tmp);
                if (tmps.size() > 0) {
                    DisruptorHelper.getInstance().producer(tmps);
                }
            }
        }
        return res;
    }

    @ApiOperation(value = "查询集包详情-V1.0.1", httpMethod = "POST", response = QueryDetailByPkgResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryDetailByPkg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryDetailByPkgResult queryDetailByPkg(@RequestBody StockInPkgReq stockInPkgReq) throws Exception {
        logger.info("queryDetailByPkg request param : {}", JSON.toJSONString(stockInPkgReq));
        return mobileStationStockService.queryDetailByPkg(stockInPkgReq);
    }

    @ApiOperation(value = "查询集包下子订单列表-V1.0.1", httpMethod = "POST", response = QueryBusiBookNoByPkgResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryBusiBookNoByPkg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryBusiBookNoByPkgResult queryBusiBookNoByPkg(@RequestBody StockInPkgReq stockInPkgReq) throws Exception {
        logger.info("queryBusiBookNoByPkg request param : {}", JSON.toJSONString(stockInPkgReq));
        return mobileStationStockService.queryBusiBookNoByPkg(stockInPkgReq);
    }

    @ApiOperation(value = "查询当前库存-V1.0.1", httpMethod = "POST", response = QueryStockListResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryStockList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryStockListResult queryStockList(@RequestBody QueryStockListReq queryStockListReq) throws Exception {
        return mobileStationStockService.queryStockList(queryStockListReq);
    }

    @ApiOperation(value = "查询出入库历史-V1.0.1", httpMethod = "POST", response = QueryStockDetailListResult.class,
            produces = "application/json", notes = "")
    @RequestMapping(value = "/queryStockDetailList", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public QueryStockDetailListResult queryStockDetailList(@RequestBody QueryStockDetailListReq queryStockDetailListReq) throws Exception {
        return mobileStationStockService.queryStockDetailList(queryStockDetailListReq);
    }
}

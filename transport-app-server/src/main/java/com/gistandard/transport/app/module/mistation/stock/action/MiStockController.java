package com.gistandard.transport.app.module.mistation.stock.action;

import com.alibaba.fastjson.JSON;
import com.gistandard.platform.pojo.res.AppBaseResult;
import com.gistandard.transport.app.dubbo.order.bean.QueryMyOrderDetailResult;
import com.gistandard.transport.app.system.common.define.AppServerDefine;
import com.gistandard.transport.base.define.BusinessDefine;
import com.gistandard.transport.base.define.SysAccountRole;
import com.gistandard.transport.base.define.SysResult;
import com.gistandard.transport.base.entity.bean.ComAccount;
import com.gistandard.transport.base.entity.bean.ComWaybillTrace;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.order.module.mistation.stock.bean.MiStationStockResult;
import com.gistandard.transport.order.module.mistation.stock.service.MiStockService;
import com.gistandard.transport.order.module.mobilestation.bean.stock.*;
import com.gistandard.transport.order.utils.OrderUtils;
import com.gistandard.transport.system.common.define.WayBillStatusDefine;
import com.gistandard.transport.system.frame.disruptor.DisruptorHelper;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by m on 2016/11/11.
 */
@RestController
@RequestMapping(value = AppServerDefine.M_STOCK_URL)
public class MiStockController {
    private final Logger logger = LoggerFactory.getLogger(MiStockController.class);

    private static final String TAGS_DESC = "咪站库存";

    @Autowired
    private MiStockService miStockService;

    @ApiOperation(value = "咪站-收货取件-V1.0.1", httpMethod = "POST",
            response = AppBaseResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/miStockIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AppBaseResult miStockIn(@RequestBody StockInReq stockInReq) throws Exception {
        logger.info("miStockIn request param : {}", JSON.toJSONString(stockInReq));
        AppBaseResult result = miStockService.miStockIn(stockInReq);
        return result;
    }

    @ApiOperation(value = "咪站-收货取件(批量)-V1.0.1", httpMethod = "POST",
            response = BatchStockInResult.class,
            tags = TAGS_DESC,produces = "application/json", notes = "")
    @RequestMapping(value = "/miBatchStockIn", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BatchStockInResult miBatchStockIn(@RequestBody BatchStockInReq batchStockInReq) throws Exception {
        logger.info("miStockIn request param : {}", JSON.toJSONString(batchStockInReq));
        BatchStockInResult result = new BatchStockInResult();
        List<BatchStockInResultBean> errorList = new ArrayList<>();
        List<BatchStockInResultBean> successList = new ArrayList<>();
        List<BatchStockInReqBean> batchStockInReqBeans = batchStockInReq.getStockInReqs();
        List<ComWaybillTrace> tmps = new ArrayList<>();
        for (BatchStockInReqBean batchStockInReqBean : batchStockInReqBeans){
            BatchStockInResultBean batchStockInResultBean = new BatchStockInResultBean();
            batchStockInResultBean.setBusiBookNo(batchStockInReqBean.getBusiBookNo());
            batchStockInResultBean.setBusiNoTag(batchStockInReqBean.getBusiNoTag());
            try {
                StockInReq stockInReq = OrderUtils.createStockInReq(batchStockInReq, batchStockInReqBean);
                MiStationStockResult miStationStockResult = miStockService.miStockIn(stockInReq);
                if(miStationStockResult != null && miStationStockResult.getRetCode()== SysResult.SUCCESS.getValue() && stockInReq.getRoleId()!=null){
                    ComWaybillTrace tmp = new ComWaybillTrace();
                    tmp.setAcctUsername(stockInReq.getAcctUsername());
                    tmp.setBusiBookNo(stockInReq.getScanBusiBookNo());
                    ComAccount account = stockInReq.getAppLoginInfo().getComAccount();
                    if(account!=null) {
                        tmp.setRealName(account.getRealName());
                        tmp.setRemark(SysAccountRole.getName(stockInReq.getRoleId().intValue())+account.getRealName()+"已揽件");
                    }
                    tmp.setGrade(BusinessDefine.GRADE);
                    tmp.setExecCode(WayBillStatusDefine.TAKE_SUCCESS.getIntValue());
                    tmp.setRoleId(stockInReq.getRoleId());
                    tmp.setStaDate(new Date());
                    tmps.add(tmp);
                }
                batchStockInResultBean.setStockFlag(miStationStockResult.getRetCode() == SysResult.SUCCESS.getValue());
                batchStockInResultBean.setMsg(miStationStockResult.getRetMsg());
                batchStockInResultBean.setRePrintOrder(miStationStockResult.getRePrintOrder());
                batchStockInResultBean.setCneeCustLinkMan(miStationStockResult.getCneeCustLinkMan());
                batchStockInResultBean.setCneeCustLinkTel(miStationStockResult.getCneeCustLinkTel());
                batchStockInResultBean.setRoutePathInfo(miStationStockResult.getRoutePathInfo());
                if(batchStockInResultBean.getStockFlag()){
                    successList.add(batchStockInResultBean);
                }
                else {
                    errorList.add(batchStockInResultBean);
                }
            } catch (MobileStationBizException e) {
                logger.error(e.getMessage());
                batchStockInResultBean.setStockFlag(false);
                batchStockInResultBean.setMsg(e.getMessage());
                errorList.add(batchStockInResultBean);
            }
        }
        if (tmps.size() > 0) {
            //记录物流跟踪信息日志
            DisruptorHelper.getInstance().producer(tmps);
        }
        for(BatchStockInResultBean stockInResultBean : successList){

        }
        result.setErrorBeans(errorList);
        result.setSuccessBeans(successList);
        return result;
    }
}

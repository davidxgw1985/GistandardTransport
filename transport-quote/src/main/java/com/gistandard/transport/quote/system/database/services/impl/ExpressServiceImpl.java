package com.gistandard.transport.quote.system.database.services.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.bean.MobileScheduSubOrder;
import com.gistandard.transport.base.entity.dao.ex.MobileBookingFormDaoEx;
import com.gistandard.transport.base.entity.dao.ex.MobileScheduSubOrderDaoEx;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.base.exception.MobileStationBizException;
import com.gistandard.transport.quote.system.database.bean.CheckAssignOrderforbatchFailed;
import com.gistandard.transport.quote.system.database.bean.QueryBatchPlatformQuote2Result;
import com.gistandard.transport.quote.system.database.bean.QueryPlatformQuote2Req;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.utils.OrderUtil;
import com.gistandard.transport.system.webservice.client.calcWebService.CalcWebService;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuote;
import com.gistandard.transport.system.webservice.client.calcWebService.PlatformQuoteI;
import com.gistandard.transport.system.webservice.client.calcWebService.QueryPlatformQuoteIBatchResponse;
import com.gistandard.transport.system.webservice.client.payinfo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by m on 2016/10/21.
 */
@Service
public class ExpressServiceImpl implements ExpressService {
    private static final Logger logger = LoggerFactory.getLogger(ExpressServiceImpl.class);
    @Autowired
    private CalcWebService calcWebService;
    @Autowired
    private MobileBookingFormDaoEx mobileBookingFormDaoEx;
    @Autowired
    private MobileScheduSubOrderDaoEx mobileScheduSubOrderDaoEx;
    @Autowired
    private OrderUtil orderUtil;
    @Autowired
    private QueryCalcManagerWebService queryCalcManagerWebService;

    public PlatformQuote queryPlatformQuote2(String systemFlag, String busibookno, String startAccountId, String endAccountId, Integer roleId) throws MobileStationBizException {
        MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions2(busibookno, null, null);
        if (mobileBookingForm == null)
            throw new MobileStationBizException("订单不存在");
        Integer type;
        Integer size = 1;
        if (mobileBookingForm.getScheducarno() == null) {
            //订单号
            type = 2;
        } else {
            //派车单号
            type = 2;
            List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileBookingForm.getId());
            if (mobileScheduSubOrders.size() == 0)
                throw new MobileStationBizException("派车单子订单不存在，无法查询报价");
            busibookno = mobileScheduSubOrders.get(0).getBusiBookNo();
            size = mobileScheduSubOrders.size();
        }
        PlatformQuote platformQuote = calcWebService.queryPlatformQuote2(systemFlag, busibookno,
                orderUtil.getAccountId(startAccountId, roleId), orderUtil.getAccountId(endAccountId, roleId), type);
        if(platformQuote!=null && platformQuote.getPrice()!=null) {
            platformQuote.setPrice(platformQuote.getPrice().multiply(BigDecimal.valueOf(size)));
        }else {
            throw new MobileStationBizException("查询报价异常！");
        }
        return platformQuote;
    }

    /**
     * 批量查询报价信息列表
     * @param queryPlatformQuote2Reqs
     * @return
     */
    public QueryBatchPlatformQuote2Result queryBatchPlatformQuote2(List<QueryPlatformQuote2Req> queryPlatformQuote2Reqs) {
        List<PlatformQuoteI> platformQuoteIs = new ArrayList<>();
        //订单与子订单数量列表
        Map<String, Integer> sizeMap = new HashMap<>();
        //失败列表
        List<CheckAssignOrderforbatchFailed> faileds = new ArrayList<>();
        CheckAssignOrderforbatchFailed failed = new CheckAssignOrderforbatchFailed();
        for (QueryPlatformQuote2Req queryPlatformQuote2Req : queryPlatformQuote2Reqs) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDaoEx.selectByConditions2(queryPlatformQuote2Req.getBusibookno(), null, null);
            if (mobileBookingForm == null) {
//                throw new MobileStationBizException("订单不存在");
                failed.setBusibookno(queryPlatformQuote2Req.getBusibookno());
                failed.setMsg("订单不存在");
                faileds.add(failed);
                continue;
            }
            PlatformQuoteI platformQuoteI = new PlatformQuoteI();
            Integer type;
            Integer size = 1;
            if (mobileBookingForm.getScheducarno() == null) {
                //订单号
                type = 2;
                platformQuoteI.setOrderNo(queryPlatformQuote2Req.getBusibookno());
            } else {
                //派车单号
                type = 2;
                List<MobileScheduSubOrder> mobileScheduSubOrders = mobileScheduSubOrderDaoEx.selectMobileSubOrderByMobileId(mobileBookingForm.getId());
                if (mobileScheduSubOrders.size() == 0) {
//                    throw new MobileStationBizException("派车单子订单不存在，无法查询报价");
                    failed.setBusibookno(queryPlatformQuote2Req.getBusibookno());
                    failed.setMsg("派车单子订单不存在，无法查询报价");
                    faileds.add(failed);
                    continue;
                }
                platformQuoteI.setOrderNo(mobileScheduSubOrders.get(0).getBusiBookNo());
                size = mobileScheduSubOrders.size();
            }
            platformQuoteI.setSystemFlag(queryPlatformQuote2Req.getSystemFlag());
            try {
                platformQuoteI.setStartAccountId(orderUtil.getAccountId(queryPlatformQuote2Req.getStartAccountId(), queryPlatformQuote2Req.getRoleId()));
                platformQuoteI.setEndAccountId(orderUtil.getAccountId(queryPlatformQuote2Req.getEndAccountId(), queryPlatformQuote2Req.getRoleId()));
            } catch (MobileStationBizException e) {
                e.printStackTrace();
                failed.setBusibookno(queryPlatformQuote2Req.getBusibookno());
                failed.setMsg(e.getMessage());
                faileds.add(failed);
                continue;
            }
            //orderkey用于做映射的key，防止无法确认报价列表属于哪个订单
            platformQuoteI.setOrderKey(queryPlatformQuote2Req.getBusibookno());
            platformQuoteI.setOrderCatalog(type);
            platformQuoteIs.add(platformQuoteI);
            sizeMap.put(queryPlatformQuote2Req.getBusibookno(), size);
        }
        Map<String, PlatformQuote> platformQuoteMap = new HashMap<>();
        QueryPlatformQuoteIBatchResponse.Return aReturn = calcWebService.queryPlatformQuoteIBatch(platformQuoteIs);
        for (QueryPlatformQuoteIBatchResponse.Return.Entry entry : aReturn.getEntry()){
            String busino = entry.getKey();
            PlatformQuote platformQuote = entry.getValue();
            Integer size = sizeMap.get(busino) == null ? 1 : sizeMap.get(busino);
            platformQuote.setPrice(platformQuote.getPrice().multiply(BigDecimal.valueOf(size)));
            platformQuoteMap.put(entry.getKey(), platformQuote);
        }
//        if(platformQuote!=null && platformQuote.getPrice()!=null) {
//            platformQuote.setPrice(platformQuote.getPrice().multiply(BigDecimal.valueOf(size)));
//        }else {
//            throw new MobileStationBizException("查询报价异常！");
//        }
        QueryBatchPlatformQuote2Result result = new QueryBatchPlatformQuote2Result();
        result.setFaileds(faileds);
        result.setPlatformQuoteMap(platformQuoteMap);
        return result;
    }

    /**
     * 比价操作实现细节
     *
     * @param platFormInModel 平台数据模型Bean
     * @return PlatFormOutModWrap
     * @throws Exception_Exception  Exception_Exception
     * @throws CustomerBizException CustomerBizException
     */
    @Override
    public PlatFormOutModWrap priceCompareOperate(PlatFormInModel platFormInModel) throws Exception_Exception, CustomerBizException {
        logger.info("比价操作实现细节 priceCompareOperate {}", JSON.toJSONString(platFormInModel));
        //数据返回形式包装类
        PlatFormOutModWrap platFormOutModWrap = new PlatFormOutModWrap();
        //计算体积重
        if (platFormInModel.getVolume() != null && platFormInModel.getVolume().intValue() != 0) {
            BigDecimal volumeWht = platFormInModel.getVolume().divide(new BigDecimal(6000), 4, BigDecimal.ROUND_HALF_EVEN);
            if (volumeWht.compareTo(platFormInModel.getWeight()) > 0) {
                platFormInModel.setWeight(volumeWht);
            }
        }
        //比较细节,如果系统码是同城快递
        if (MobileStationDefine.PRODUCT_TYPE_TCKD.equals(platFormInModel.getItemCode())) {
            PlatFormOutModel requestTCKD = queryCalcManagerWebService.getQuote(platFormInModel);
            //如果是同城快递，此时就计算同城专送的预估价，设置同城快递的系统码和引用类型,以此来比较专送和快递的价格
            platFormInModel.setItemCode(MobileStationDefine.PRODUCT_TYPE_TCZS);
            platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_SPECIAL_DELIVERY);
            PlatFormOutModel requestTCZS = queryCalcManagerWebService.getQuote(platFormInModel);
            //同城快递页面计算
            platFormOutModWrap.calPrice4TCKD(requestTCKD, requestTCZS);

        } else if (MobileStationDefine.PRODUCT_TYPE_TCZS.equals(platFormInModel.getItemCode())) {
            PlatFormOutModel requestTCZS = queryCalcManagerWebService.getQuote(platFormInModel);
            //如果是同城专送，此时就计算同城快递的预估价，设置同城快递的系统码和引用类型，以此来比较专送和快递的价格
            platFormInModel.setItemCode(MobileStationDefine.PRODUCT_TYPE_TCKD);
            platFormInModel.setQuoteType(MobileStationDefine.QUOTE_TYPE_EXPRESS);
            PlatFormOutModel requestTCKD = queryCalcManagerWebService.getQuote(platFormInModel);
            //同城专送页面计算
            platFormOutModWrap.calPrice4TCZS(requestTCZS, requestTCKD);
        }
        return platFormOutModWrap;
    }


}

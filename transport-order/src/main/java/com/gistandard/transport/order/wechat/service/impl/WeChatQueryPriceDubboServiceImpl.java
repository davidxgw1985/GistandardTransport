package com.gistandard.transport.order.wechat.service.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.app.dubbo.wechat.bean.QueryPlatFormExpressPriceReq;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatPlatFormOutModWrap;
import com.gistandard.transport.app.dubbo.wechat.bean.WeChatPlatFormOutRes;
import com.gistandard.transport.app.dubbo.wechat.service.WeChatQueryPriceDubboService;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.exception.CustomerBizException;
import com.gistandard.transport.quote.system.database.services.ExpressService;
import com.gistandard.transport.system.webservice.client.payinfo.*;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by m on 2017/2/7.
 */
public class WeChatQueryPriceDubboServiceImpl implements WeChatQueryPriceDubboService {

    private static final Logger logger = LoggerFactory.getLogger(WeChatQueryPriceDubboServiceImpl.class);

    @Autowired
    private ExpressService expressService;

    @Override
    public WeChatPlatFormOutRes getQuote(QueryPlatFormExpressPriceReq queryPlatFormExpressPriceReq) {
        logger.debug("WeChatQueryPriceDubboServiceImpl getQuote req={}", JSON.toJSONString(queryPlatFormExpressPriceReq));
        WeChatPlatFormOutRes weChatPlatFormOutRes = new WeChatPlatFormOutRes();
        PlatFormInModel queryPlatFormExpressPriceModel = new PlatFormInModel();
        try {
            BeanUtils.copyProperties(queryPlatFormExpressPriceModel, queryPlatFormExpressPriceReq);
            //现在最新比价操作,校验参数
            if (queryPlatFormExpressPriceReq != null && queryPlatFormExpressPriceReq.validateRequestParam()) {
                PlatFormOutModWrap platFormOutModWrap = expressService.priceCompareOperate(queryPlatFormExpressPriceModel);
                WeChatPlatFormOutModWrap weChatPlatFormOutModWrap = JSON.parseObject(JSON.toJSONString(platFormOutModWrap), WeChatPlatFormOutModWrap.class);
                weChatPlatFormOutRes.setData(weChatPlatFormOutModWrap);
            } else {
                weChatPlatFormOutRes.setRetCode(-1);
                weChatPlatFormOutRes.setRetMsg("参数校验失败");
            }
        } catch (CustomerBizException e) {
            e.printStackTrace();
            weChatPlatFormOutRes.setRetCode(SystemDefine.FAILURE);
            weChatPlatFormOutRes.setRetMsg(e.getMessage());
            return weChatPlatFormOutRes;
        } catch (Exception_Exception e) {
            e.printStackTrace();
            weChatPlatFormOutRes.setRetCode(SystemDefine.FAILURE);
            weChatPlatFormOutRes.setRetMsg(e.getMessage());
            return weChatPlatFormOutRes;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            weChatPlatFormOutRes.setRetCode(SystemDefine.FAILURE);
            weChatPlatFormOutRes.setRetMsg(e.getMessage());
            return weChatPlatFormOutRes;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            weChatPlatFormOutRes.setRetCode(SystemDefine.FAILURE);
            weChatPlatFormOutRes.setRetMsg(e.getMessage());
            return weChatPlatFormOutRes;
        }
        return weChatPlatFormOutRes;
    }
}

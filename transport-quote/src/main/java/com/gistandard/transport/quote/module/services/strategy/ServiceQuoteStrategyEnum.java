package com.gistandard.transport.quote.module.services.strategy;



import com.gistandard.transport.quote.module.services.strategy.imp.*;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.tools.util.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenzhijun on 2016/7/5.
 */
public enum ServiceQuoteStrategyEnum {
    INSTANCE;
    private  Map<Integer, AbstractServiceQuoteStrategy> strategyInfoMap = new HashMap<>();

    ServiceQuoteStrategyEnum(){
        strategyInfoMap.put(QuoteTypeDefine.ZC.getValue(), (ZcServiceQuoteStrategy) SpringContextUtil.getBean("zcServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.LD.getValue(),(LdServiceQuoteStrategy)SpringContextUtil.getBean("ldServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZLFD.getValue(),(ZlfdServiceQuoteStrategy)SpringContextUtil.getBean("zlfdServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GL.getValue(),(GlServiceQuoteStrategy)SpringContextUtil.getBean("glServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GLFD.getValue(),(GlfdServiceQuoteStrategy)SpringContextUtil.getBean("glfdServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZDY.getValue(),(ZdyServiceQuoteStrategy)SpringContextUtil.getBean("zdyServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.AP.getValue(),(ApServiceQuoteStrategy)SpringContextUtil.getBean("apServiceQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.KD.getValue(),(KdServiceQuoteStrategy)SpringContextUtil.getBean("kdServiceQuoteStrategy"));
    }


    public AbstractServiceQuoteStrategy getQuoteStrategy(Integer quoteType){
        return strategyInfoMap.get(quoteType);
    }

}

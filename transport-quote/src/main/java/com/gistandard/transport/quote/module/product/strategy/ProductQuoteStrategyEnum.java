package com.gistandard.transport.quote.module.product.strategy;



import com.gistandard.transport.quote.module.product.strategy.impl.*;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.tools.util.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shenzhijun on 2016/7/5.
 */
public enum ProductQuoteStrategyEnum {
    INSTANCE;
    private  Map<Integer, AbstractProductQuoteStrategy> strategyInfoMap = new HashMap<>();
    ProductQuoteStrategyEnum(){
        strategyInfoMap.put(QuoteTypeDefine.ZC.getValue(), (ZcProductQuoteStrategy) SpringContextUtil.getBean("zcQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.LD.getValue(),(LdProductQuoteStrategy)SpringContextUtil.getBean("ldQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZLFD.getValue(),(ZlfdProductQuoteStrategy)SpringContextUtil.getBean("zlfdQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GL.getValue(),(GlProductQuoteStrategy)SpringContextUtil.getBean("glQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GLFD.getValue(),(GlfdProductQuoteStrategy)SpringContextUtil.getBean("glfdQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZDY.getValue(),(ZdyProductQuoteStrategy)SpringContextUtil.getBean("zdyQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.AP.getValue(),(ApProductQuoteStrategy)SpringContextUtil.getBean("apQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.KD.getValue(),(KdProductQuoteStrategy)SpringContextUtil.getBean("kdQuoteStrategy"));
    }

    public AbstractProductQuoteStrategy getQuoteStrategy(Integer quoteType){
        return strategyInfoMap.get(quoteType);
    }

}

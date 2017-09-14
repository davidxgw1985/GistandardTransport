package com.gistandard.transport.quote.module.calc.quote;

import com.gistandard.transport.quote.module.calc.quote.impl.*;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.tools.util.SpringContextUtil;

import java.util.HashMap;
import java.util.Map;

public enum QuoteStrategyEnum {
    INSTANCE {
        public AbstractQuoteStrategy getQuoteStrategy(Integer quoteType) {
            return this.getStrategyInfoMap().get(quoteType);
        }
    };

    private Map<Integer, AbstractQuoteStrategy> strategyInfoMap = new HashMap<>();

    public Map<Integer, AbstractQuoteStrategy> getStrategyInfoMap() {
        return strategyInfoMap;
    }

    QuoteStrategyEnum() {
        strategyInfoMap.put(QuoteTypeDefine.ZC.getValue(), (ZcQuoteStrategy) SpringContextUtil.getBean("zzcQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.LD.getValue(), (LdQuoteStrategy) SpringContextUtil.getBean("lldQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZLFD.getValue(), (ZlfdQuoteStrategy) SpringContextUtil.getBean("zzlfdQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GL.getValue(), (GlQuoteStrategy) SpringContextUtil.getBean("gglQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.GLFD.getValue(), (GlfdQuoteStrategy) SpringContextUtil.getBean("gglfdQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZDY.getValue(), (ZdyQuoteStrategy) SpringContextUtil.getBean("zzdyQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.AP.getValue(), (ApQuoteStrategy) SpringContextUtil.getBean("aapQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.KD.getValue(), (KdQuoteStrategy) SpringContextUtil.getBean("kkdQuoteStrategy"));
        strategyInfoMap.put(QuoteTypeDefine.ZS.getValue(), (TczsQuoteStrategy) SpringContextUtil.getBean("tczsQuoteStrategy"));
    }

    public AbstractQuoteStrategy getQuoteStrategy(Integer quoteType){
        throw new AbstractMethodError();
    }

}

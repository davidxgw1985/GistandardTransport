package com.gistandard.transport.quote.system.common.bean;


import com.gistandard.transport.base.entity.bean.ComQuotePrice;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by shenzhijun on 2016/3/2.
 */
public class ComQuotePriceBean extends ComQuotePrice implements Serializable{
    private static final long serialVersionUID = -984615614652831481L;
    //前一个值
    private BigDecimal prevPointValue;

    public BigDecimal getPrevPointValue() {
        return prevPointValue;
    }

    public void setPrevPointValue(BigDecimal prevPointValue) {
        this.prevPointValue = prevPointValue;
    }
}

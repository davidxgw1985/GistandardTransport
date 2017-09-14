package com.gistandard.transport.system.calc.service;

import com.gistandard.transport.giifiCalc.external.bean.ICalcForPrice;
import com.gistandard.transport.giifiCalc.external.bean.OCalcForPrice;

/**
 * 结算接口 发送消息Service
 */
public interface CalcJmsService {

    /**
     * O单结算发送消息接口
     * @param oCalcForPrice
     */
    void sendCalcForPriceMessage(final OCalcForPrice oCalcForPrice);

    /**
     * I单结算发送消息接口
     * @param iCalcForPrice
     */
    void sendCalcForPrice2Message(final ICalcForPrice iCalcForPrice);

}

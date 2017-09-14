package com.gistandard.transport.quote.module.calc.quote.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.define.MobileStationDefine;
import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComQuotePrice;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.system.center.search.bean.QueryDistanceByLatAndLonResult;
import com.gistandard.transport.system.center.search.service.SenderSearchService;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.module.calc.bean.FormulaParameters;
import com.gistandard.transport.quote.system.common.bean.ComQuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.courier.bean.RoutePlan;
import com.gistandard.transport.system.common.courier.service.GpsLocationService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 公里分段
 * Created by shenzhijun on 2016/7/5.
 */
@Service("gglfdQuoteStrategy")
public class GlfdQuoteStrategy extends AbstractQuoteStrategy {
    @Autowired
    private GpsLocationService gpsLocationService;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private SenderSearchService senderSearchService;

    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean, ResultBean resultBean) {
        if (StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())) {
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        List<ComQuotePrice> comQuotePriceList = quoteBean.getComQuotePriceList();
        if (comQuotePriceList == null) {
            resultBean.setState(false);
            resultBean.setMessage("报价不存在！");
            return false;
        }
        BigDecimal prevPointValue = new BigDecimal(0);
        for (int i = 0; i < comQuotePriceList.size(); i++) {
            ComQuotePrice comQuotePrice = comQuotePriceList.get(i);
            if (null == comQuotePrice.getUnitPrice()) {
                resultBean.setState(false);
                resultBean.setMessage("报价不能有空值！");
                return false;
            }
            if (comQuotePrice.getUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
                resultBean.setState(false);
                resultBean.setMessage("报价值过大！");
                return false;
            }
            if (null == comQuotePrice.getPointValue()) {
                resultBean.setState(false);
                resultBean.setMessage("单价不能有空值！");
                return false;
            }
            if (comQuotePrice.getPointValue().compareTo(StringUtil.toBig("99999999")) > 0) {
                resultBean.setState(false);
                resultBean.setMessage("单价值过大！");
                return false;
            }
            if (i > 0) {
                if (comQuotePrice.getPointValue().compareTo(prevPointValue) < 0) {
                    resultBean.setState(false);
                    resultBean.setMessage("大于" + prevPointValue + "且小于" + comQuotePrice.getPointValue() + ",关系错误！");
                    return false;
                }
            }
            prevPointValue = comQuotePrice.getPointValue();
        }
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        this.saveQuote(quoteBean);
        return this.saveQuotePrice(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception {
        //报价明细
        String priceArray = request.getParameter("priceArray");
        if (!StringUtils.isEmpty(priceArray)) {
            List<ComQuotePrice> comQuotePriceList = null;
            try {
                comQuotePriceList = JSON.parseArray(priceArray, ComQuotePrice.class);
            } catch (Exception e) {
                throw new RuntimeException("报价数据异常！");
            }
            quoteBean.setComQuotePriceList(comQuotePriceList);
        } else {
            throw new RuntimeException("重量分段报价信息不存在！");
        }
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        RoutePlan routePlan = gpsLocationService.getGiRoutePlanById(quoteResultBean.getComQuote().getRouteId());
        if (routePlan != null) {
            //获取线路里程
            BigDecimal mileage = routePlan.getDistance().divide(new BigDecimal("1000"), 4, BigDecimal.ROUND_HALF_UP);
            calcParamaters.setMileage(mileage);
        } else {
            //估算里程
            if (calcForPriceReq.getMileage() != null) {
                calcParamaters.setMileage(calcForPriceReq.getMileage());
            }
        }

        //按照公里报价，必须有公里数，如果没有，查询出起始点计算
        if (calcParamaters.getMileage() == null) {
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(calcForPriceReq.getOrderId());
            QueryDistanceByLatAndLonResult result = senderSearchService.queryDistanceByLatAndLon(mobileBookingForm.getShipCustCity(), mobileBookingForm.getCneeCustCity(), mobileBookingForm.getShipLatitude(),
                    mobileBookingForm.getShipLongitude(), mobileBookingForm.getCneeLatitude(), mobileBookingForm.getCneeLongitude());
            if (result.getRetCode() == SystemDefine.SUCCESS) {
                calcParamaters.setMileage((BigDecimal) result.getData());
            }
        }
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_MileageRange);

        //按重量、公里分段
        BigDecimal tempValue = new BigDecimal(0);
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
        final ComQuoteBean comQuote = quoteResultBean.getComQuote();
        FormulaParameters formulaParameters;
        for (ComQuotePrice comQuotePrice : quoteResultBean.getComQuotePriceList()) {
            formulaParameters = new FormulaParameters();
            formulaParameters.setStartRangeValue(tempValue);
            if (MobileStationDefine.QUOTE_RULE_TYPE_XYDY == comQuotePrice.getRuleType()) {
                formulaParameters.setEndRangeValue(comQuotePrice.getPointValue());
            } else if (MobileStationDefine.QUOTE_RULE_TYPE_DY == comQuotePrice.getRuleType()) {
                if (tempValue.compareTo(calcParamaters.getMileage()) < 0) {
                    formulaParameters.setEndRangeValue(calcParamaters.getMileage());
                }
            }
            formulaParameters.setRangeUnit(comQuotePrice.getUnitCode());
            formulaParameters.setPrice(comQuotePrice.getUnitPrice());
            formulaParameters.setCurrency(comQuote.getCurrencyCode());
            formulaParametersList.add(formulaParameters);
            tempValue = comQuotePrice.getPointValue();
        }
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}

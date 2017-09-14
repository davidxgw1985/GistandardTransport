package com.gistandard.transport.quote.module.calc.quote.impl;

import com.gistandard.transport.base.define.SystemDefine;
import com.gistandard.transport.base.entity.bean.ComQuote;
import com.gistandard.transport.base.entity.bean.MobileBookingForm;
import com.gistandard.transport.base.entity.dao.MobileBookingFormDao;
import com.gistandard.transport.system.center.search.bean.QueryDistanceByLatAndLonResult;
import com.gistandard.transport.system.center.search.service.SenderSearchService;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.module.calc.bean.FormulaParameters;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.system.common.courier.bean.RoutePlan;
import com.gistandard.transport.system.common.courier.service.GpsLocationService;
import com.gistandard.transport.system.webservice.client.gps.PnWebService;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 公里报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("gglQuoteStrategy")
public class GlQuoteStrategy extends AbstractQuoteStrategy {
    @Autowired
    private GpsLocationService gpsLocationService;

    @Autowired
    private PnWebService pnWebService;

    @Autowired
    private MobileBookingFormDao mobileBookingFormDao;

    @Autowired
    private SenderSearchService senderSearchService;

    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {

        if(StringUtils.isEmpty(quoteBean.getComQuote().getCurrencyCode())){
            resultBean.setState(false);
            resultBean.setMessage("请选择币别！");
            return false;
        }

        if (null == quoteBean.getComQuote().getUnitPrice()) {
            resultBean.setState(false);
            resultBean.setMessage("单价不能是空值！");
            return false;
        }
        if (quoteBean.getComQuote().getUnitPrice().compareTo(StringUtil.toBig("99999999")) > 0) {
            resultBean.setState(false);
            resultBean.setMessage("单价值过大！");
            return false;
        }
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        return this.saveQuote(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception{
        String unitPrice = request.getParameter("unitPrice");//单价
        quoteBean.getComQuote().setUnitPrice(StringUtil.toBig(unitPrice));
    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {
        ComQuote comQuote = quoteResultBean.getComQuote();
        calcParamaters.setQuotationType(CalcParamaters.QuotationType_Mileage);
        List<FormulaParameters> formulaParametersList = new ArrayList<>();
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
            //获取实际里程
            if (calcForPriceReq.getTruckCode() != null) {
                double mileage = pnWebService.getDistanceRunedByTruck(calcForPriceReq.getTruckCode(), null, null);
                calcParamaters.setMileage(BigDecimal.valueOf(mileage));
            }
        }
        //按照公里报价，必须有公里数，如果没有，查询出起始点计算
        if(calcParamaters.getMileage()==null){
            MobileBookingForm mobileBookingForm = mobileBookingFormDao.selectByPrimaryKey(calcForPriceReq.getOrderId());
            QueryDistanceByLatAndLonResult result = senderSearchService.queryDistanceByLatAndLon(mobileBookingForm.getShipCustCity(), mobileBookingForm.getCneeCustCity(), mobileBookingForm.getShipLatitude(),
                    mobileBookingForm.getShipLongitude(), mobileBookingForm.getCneeLatitude(), mobileBookingForm.getCneeLongitude());
            if(result.getRetCode() == SystemDefine.SUCCESS){
                calcParamaters.setMileage((BigDecimal)result.getData());
            }
        }
        //按公里
        FormulaParameters formulaParameters = new FormulaParameters();
        formulaParameters.setPrice(comQuote.getUnitPrice());
        formulaParameters.setCurrency(comQuote.getCurrencyCode());
        formulaParametersList.add(formulaParameters);
        calcParamaters.setFormualParameters(formulaParametersList);
    }
}

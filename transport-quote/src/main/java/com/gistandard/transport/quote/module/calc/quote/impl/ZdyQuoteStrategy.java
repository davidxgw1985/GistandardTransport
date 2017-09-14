package com.gistandard.transport.quote.module.calc.quote.impl;

import com.alibaba.fastjson.JSON;
import com.gistandard.transport.base.entity.bean.ComQuoteDetail;
import com.gistandard.transport.quote.module.calc.bean.CalcForPriceReq;
import com.gistandard.transport.quote.module.calc.bean.CalcParamaters;
import com.gistandard.transport.quote.system.common.bean.QuoteBean;
import com.gistandard.transport.quote.system.common.bean.QuoteResultBean;
import com.gistandard.transport.quote.system.common.define.QuoteDefine;
import com.gistandard.transport.quote.system.common.define.QuoteTypeDefine;
import com.gistandard.transport.system.common.bean.ResultBean;
import com.gistandard.transport.tools.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 自定义物流类型报价
 * Created by shenzhijun on 2016/7/5.
 */
@Service("zzdyQuoteStrategy")
public class ZdyQuoteStrategy extends AbstractQuoteStrategy {
    @Override
    public boolean checkQuoteParameter(QuoteBean quoteBean,ResultBean resultBean) {
        List<ComQuoteDetail> comQuoteDetailList = quoteBean.getComQuoteDetailList();
        for (int i = 0; i < comQuoteDetailList.size(); i++) {
            ComQuoteDetail comQuoteDetail = comQuoteDetailList.get(i);
            if (StringUtils.isEmpty(comQuoteDetail.getServiceType())) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 1列 请选择服务类型！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getServiceProject()))
                    && comQuoteDetail.getServiceProject().length() > 50) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 2列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getCalcUnit()))
                    && comQuoteDetail.getCalcUnit().length() > 50) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 3列 字数超限！");
                return false;
            }

            if (comQuoteDetail.getTableHead() != null && comQuoteDetail.getTableHead()) {
                if ((!StringUtils.isEmpty(comQuoteDetail.getPrice()))
                        && comQuoteDetail.getPrice().length() > QuoteDefine.QuoteDtailMaxLength) {
                    resultBean.setState(false);
                    resultBean.setMessage("第" + (i + 1) + "行 4列 字数超限！");
                    return false;
                }
            } else {
                if (StringUtils.isEmpty(comQuoteDetail.getPrice())
                        || !NumberUtils.isNumber(comQuoteDetail.getPrice())) {
                    resultBean.setState(false);
                    resultBean.setMessage("第" + (i + 1) + "行 4列 请输入数字！");
                    return false;
                }
            }

            if (comQuoteDetail.getTableHead() != null && comQuoteDetail.getTableHead()) {
                if ((!StringUtils.isEmpty(comQuoteDetail.getCurrencyCode()))
                        && comQuoteDetail.getCurrencyCode().length() > QuoteDefine.QuoteDtailMaxLength) {
                    resultBean.setState(false);
                    resultBean.setMessage("第" + (i + 1) + "行 5列 字数超限！");
                    return false;
                }
            } else {
                if (StringUtils.isEmpty(comQuoteDetail.getCurrencyCode())) {
                    resultBean.setState(false);
                    resultBean.setMessage("第" + (i + 1) + "行 5列 请选择币制！");
                    return false;
                }
            }

            if ((!StringUtils.isEmpty(comQuoteDetail.getText1()))
                    && comQuoteDetail.getText1().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 6列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText2()))
                    && comQuoteDetail.getText2().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 7列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText3()))
                    && comQuoteDetail.getText3().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 8列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText4()))
                    && comQuoteDetail.getText4().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 9列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText5()))
                    && comQuoteDetail.getText5().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 10列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText5()))
                    && comQuoteDetail.getText5().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 11列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText6()))
                    && comQuoteDetail.getText6().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 12列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText7()))
                    && comQuoteDetail.getText7().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 13列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText8()))
                    && comQuoteDetail.getText8().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 14列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText9()))
                    && comQuoteDetail.getText9().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 15列 字数超限！");
                return false;
            }
            if ((!StringUtils.isEmpty(comQuoteDetail.getText10()))
                    && comQuoteDetail.getText10().length() > QuoteDefine.QuoteDtailMaxLength) {
                resultBean.setState(false);
                resultBean.setMessage("第" + (i + 1) + "行 16列 字数超限！");
                return false;
            }
        }
        return true;
    }

    @Override
    public int saveQuoteBean(QuoteBean quoteBean) {
        this.saveQuote(quoteBean);
        return this.saveQuoteDetail(quoteBean);
    }

    @Override
    void setQuoteBean(HttpServletRequest request, QuoteBean quoteBean) throws Exception{
        String colNum = request.getParameter("colNum");//物流类型报价明细列数
        String productType = request.getParameter("productType");//整车 、零担
        quoteBean.getComQuote().setQuoteType(QuoteTypeDefine.ZDY.getValue());
        quoteBean.getComQuote().setColNum(StringUtil.createInteger(colNum));
        quoteBean.getComQuote().setProductType(productType);

        //物流报价明细
        String quoteDetail = request.getParameter("quoteDetail");
        if (!StringUtils.isEmpty(quoteDetail)) {
            List<ComQuoteDetail> comQuoteDetailList = null;
            try {
                comQuoteDetailList = JSON.parseArray(quoteDetail, ComQuoteDetail.class);
            } catch (Exception e) {
                throw new RuntimeException("自定义报价内容不正确");
            }

            if (comQuoteDetailList.size() < 2) {

                throw new RuntimeException("请填写报价");
            }
            quoteBean.setComQuoteDetailList(comQuoteDetailList);
        } else {
             throw new RuntimeException("请填写报价！");
        }

    }

    @Override
    public void setFormualParameters(CalcParamaters calcParamaters, QuoteResultBean quoteResultBean, CalcForPriceReq calcForPriceReq) {

    }
}

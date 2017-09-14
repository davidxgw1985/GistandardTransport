
package com.gistandard.transport.system.webservice.client.payinfo;

import com.alibaba.fastjson.JSONObject;
import com.gistandard.transport.base.exception.CustomerBizException;
import org.apache.commons.collections.CollectionUtils;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * <p>platFormOutModel complex type的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;complexType name="platFormOutModel"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="QuoteCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="QuoteDetailList" type="{http://webService.external.giifiCalc.transport.gistandard.com/}platFormDetailModel" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "platFormOutModWrap", propOrder = {
        "dataTCKD",
        "dataTCZS"
})
public class PlatFormOutModWrap {

    @XmlElement(name = "dataTCKD")
    private PlatFormOutModel dataTCKD;

    @XmlElement(name = "dataTCZS")
    private PlatFormOutModel dataTCZS;


    public com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel getDataTCKD() {
        return dataTCKD;
    }

    public void setDataTCKD(com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel dataTCKD) {
        this.dataTCKD = dataTCKD;
    }

    public com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel getDataTCZS() {
        return dataTCZS;
    }

    public void setDataTCZS(com.gistandard.transport.system.webservice.client.payinfo.PlatFormOutModel dataTCZS) {
        this.dataTCZS = dataTCZS;
    }

    /**
     * 在同城快递中比较数据
     * @param requestTCKD requestTCKD
     * @param requestTCZS requestTCZS
     * @throws Exception_Exception Exception_Exception
     * @throws CustomerBizException CustomerBizException
     */
    public void calPrice4TCKD(PlatFormOutModel requestTCKD, PlatFormOutModel requestTCZS) throws Exception_Exception, CustomerBizException {

        if ("0".equals(requestTCKD.getStatus()) && "0".equals(requestTCZS.getStatus())) {
            BigDecimal pfdmPriceTCKD;
            BigDecimal pfdmPriceTCZS;
            List<PlatFormDetailModel> pfdmTCKDList = requestTCKD.getQuoteDetailList();
            List<PlatFormDetailModel> pfdmTCZSList = requestTCZS.getQuoteDetailList();
            //集合均不为空才有比较价格的意义
            if (CollectionUtils.isNotEmpty(pfdmTCKDList) && CollectionUtils.isNotEmpty(pfdmTCZSList)) {
                sortPlatFormByPrice(pfdmTCKDList);
                pfdmPriceTCKD = pfdmTCKDList.get(0).getPredictValueAfterTax();
                sortPlatFormByPrice(pfdmTCZSList);
                pfdmPriceTCZS = pfdmTCZSList.get(0).getPredictValueAfterTax();
            } else {
                this.setDataTCKD(requestTCKD);
                return;
            }
            if (pfdmPriceTCZS.compareTo(pfdmPriceTCKD) == -1) {
                this.setDataTCKD(requestTCKD);
                this.setDataTCZS(requestTCZS);
            } else {
                this.setDataTCKD(requestTCKD);
            }
        } else {
            throw new CustomerBizException(requestTCKD.getMessage() + requestTCZS.getMessage());
        }
    }


    /**
     * 在同城专送中比较数据
     * @param requestTCZS requestTCZS
     * @param requestTCKD requestTCKD
     * @throws Exception_Exception Exception_Exception
     * @throws CustomerBizException CustomerBizException
     */
    public void calPrice4TCZS(PlatFormOutModel requestTCZS,
                                            PlatFormOutModel requestTCKD ) throws Exception_Exception, CustomerBizException {
        if ("0".equals(requestTCKD.getStatus()) && "0".equals(requestTCZS.getStatus())) {
            BigDecimal pfdmPriceTCKD;
            BigDecimal pfdmPriceTCZS;
            List<PlatFormDetailModel> pfdmTCKDList = requestTCKD.getQuoteDetailList();
            List<PlatFormDetailModel> pfdmTCZSList = requestTCZS.getQuoteDetailList();
            //集合均不为空才有比较价格的意义
            if (CollectionUtils.isNotEmpty(pfdmTCKDList) && CollectionUtils.isNotEmpty(pfdmTCZSList)) {
                sortPlatFormByPrice(pfdmTCKDList);
                pfdmPriceTCKD = pfdmTCKDList.get(0).getPredictValueAfterTax();
                sortPlatFormByPrice(pfdmTCZSList);
                pfdmPriceTCZS = pfdmTCZSList.get(0).getPredictValueAfterTax();
            } else {
                this.setDataTCZS(requestTCZS);
                return;
            }
            if (pfdmPriceTCKD.compareTo(pfdmPriceTCZS) == -1) {
                this.setDataTCKD(requestTCKD);
                this.setDataTCZS(requestTCZS);
            } else {
                this.setDataTCZS(requestTCZS);
            }

        } else {
            throw new CustomerBizException(requestTCKD.getMessage() + requestTCZS.getMessage());
        }
    }

    /**
     * 对返回数据单集合按照预估价从高到底排序
     * @param platFormList platFormList
     */
    private void sortPlatFormByPrice(List<PlatFormDetailModel> platFormList) {

        Collections.sort(platFormList, new Comparator<PlatFormDetailModel>() {

            public int compare(PlatFormDetailModel o1, PlatFormDetailModel o2) {

                return o2.getPredictValueAfterTax().compareTo(o1.getPredictValueAfterTax());
            }
        });
    }
}

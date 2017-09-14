
package com.gistandard.transport.calculate.webservice.generalAcctService;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>divideConnectWithAmount complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="divideConnectWithAmount"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="actualAmountSum" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="payType" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "divideConnectWithAmount", propOrder = {
    "actualAmountSum",
    "payType"
})
public class DivideConnectWithAmount {

    protected BigDecimal actualAmountSum;
    protected Integer payType;

    /**
     * 获取actualAmountSum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getActualAmountSum() {
        return actualAmountSum;
    }

    /**
     * 设置actualAmountSum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setActualAmountSum(BigDecimal value) {
        this.actualAmountSum = value;
    }

    /**
     * 获取payType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置payType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPayType(Integer value) {
        this.payType = value;
    }

}

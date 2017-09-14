
package com.gistandard.transport.calculate.webservice.generalAcctService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>orderConfirmPayBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="orderConfirmPayBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="orderInfoBeanNeedConfirmList" type="{http://server.webservice.gistandard.com/}confirmPayOrderInfoBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="orderInfoBeanO" type="{http://server.webservice.gistandard.com/}baseOrderInfoBean" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "orderConfirmPayBean", propOrder = {
    "orderInfoBeanNeedConfirmList",
    "orderInfoBeanO"
})
public class OrderConfirmPayBean {

    @XmlElement(nillable = true)
    protected List<ConfirmPayOrderInfoBean> orderInfoBeanNeedConfirmList;
    protected BaseOrderInfoBean orderInfoBeanO;

    /**
     * Gets the value of the orderInfoBeanNeedConfirmList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderInfoBeanNeedConfirmList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderInfoBeanNeedConfirmList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ConfirmPayOrderInfoBean }
     * 
     * 
     */
    public List<ConfirmPayOrderInfoBean> getOrderInfoBeanNeedConfirmList() {
        if (orderInfoBeanNeedConfirmList == null) {
            orderInfoBeanNeedConfirmList = new ArrayList<ConfirmPayOrderInfoBean>();
        }
        return this.orderInfoBeanNeedConfirmList;
    }

    public void setOrderInfoBeanNeedConfirmList(List<ConfirmPayOrderInfoBean> orderInfoBeanNeedConfirmList) {
        this.orderInfoBeanNeedConfirmList = orderInfoBeanNeedConfirmList;
    }

    /**
     * 获取orderInfoBeanO属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BaseOrderInfoBean }
     *     
     */
    public BaseOrderInfoBean getOrderInfoBeanO() {
        return orderInfoBeanO;
    }

    /**
     * 设置orderInfoBeanO属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BaseOrderInfoBean }
     *     
     */
    public void setOrderInfoBeanO(BaseOrderInfoBean value) {
        this.orderInfoBeanO = value;
    }

}

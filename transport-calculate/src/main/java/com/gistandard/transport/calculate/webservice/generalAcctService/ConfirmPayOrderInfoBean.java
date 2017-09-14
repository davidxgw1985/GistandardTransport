
package com.gistandard.transport.calculate.webservice.generalAcctService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>confirmPayOrderInfoBean complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="confirmPayOrderInfoBean"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://server.webservice.gistandard.com/}baseOrderInfoBean"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="multiCalc" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="orderInfoBeanOList" type="{http://server.webservice.gistandard.com/}baseOrderInfoBean" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "confirmPayOrderInfoBean", propOrder = {
    "multiCalc",
    "orderInfoBeanOList"
})
public class ConfirmPayOrderInfoBean
    extends BaseOrderInfoBean
{

    protected boolean multiCalc;
    @XmlElement(nillable = true)
    protected List<BaseOrderInfoBean> orderInfoBeanOList;

    /**
     * 获取multiCalc属性的值。
     * 
     */
    public boolean isMultiCalc() {
        return multiCalc;
    }

    /**
     * 设置multiCalc属性的值。
     * 
     */
    public void setMultiCalc(boolean value) {
        this.multiCalc = value;
    }

    /**
     * Gets the value of the orderInfoBeanOList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderInfoBeanOList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderInfoBeanOList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BaseOrderInfoBean }
     * 
     * 
     */
    public List<BaseOrderInfoBean> getOrderInfoBeanOList() {
        if (orderInfoBeanOList == null) {
            orderInfoBeanOList = new ArrayList<BaseOrderInfoBean>();
        }
        return this.orderInfoBeanOList;
    }

    public void setOrderInfoBeanOList(List<BaseOrderInfoBean> orderInfoBeanOList) {
        this.orderInfoBeanOList = orderInfoBeanOList;
    }
}

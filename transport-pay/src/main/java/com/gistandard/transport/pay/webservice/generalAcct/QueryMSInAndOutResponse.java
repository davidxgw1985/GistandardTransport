
package com.gistandard.transport.pay.webservice.generalAcct;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>queryMSInAndOutResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="queryMSInAndOutResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="queryMSInAndOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "queryMSInAndOutResponse", propOrder = {
    "queryMSInAndOut"
})
public class QueryMSInAndOutResponse {

    protected String queryMSInAndOut;

    /**
     * 获取queryMSInAndOut属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getQueryMSInAndOut() {
        return queryMSInAndOut;
    }

    /**
     * 设置queryMSInAndOut属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setQueryMSInAndOut(String value) {
        this.queryMSInAndOut = value;
    }

}

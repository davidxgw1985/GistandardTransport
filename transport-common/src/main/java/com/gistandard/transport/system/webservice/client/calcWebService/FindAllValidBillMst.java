
package com.gistandard.transport.system.webservice.client.calcWebService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>findAllValidBillMst complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="findAllValidBillMst"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="gFUserCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="pageSize" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="pageNum" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "findAllValidBillMst", propOrder = {
    "gfUserCode",
    "pageSize",
    "pageNum"
})
public class FindAllValidBillMst {

    @XmlElement(name = "gFUserCode")
    protected String gfUserCode;
    protected int pageSize;
    protected int pageNum;

    /**
     * 获取gfUserCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGFUserCode() {
        return gfUserCode;
    }

    /**
     * 设置gfUserCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGFUserCode(String value) {
        this.gfUserCode = value;
    }

    /**
     * 获取pageSize属性的值。
     * 
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置pageSize属性的值。
     * 
     */
    public void setPageSize(int value) {
        this.pageSize = value;
    }

    /**
     * 获取pageNum属性的值。
     * 
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * 设置pageNum属性的值。
     * 
     */
    public void setPageNum(int value) {
        this.pageNum = value;
    }

}

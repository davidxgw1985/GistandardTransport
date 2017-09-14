
package com.gistandard.transport.system.webservice.client.gps;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>getAllGiAdminConcernPcdByUserCodeResponse complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="getAllGiAdminConcernPcdByUserCodeResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="getAllGiAdminConcernPcdByUserCodeResult" type="{http://webservice.manage.gps.com/}giAdminResult" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllGiAdminConcernPcdByUserCodeResponse", propOrder = {
    "getAllGiAdminConcernPcdByUserCodeResult"
})
public class GetAllGiAdminConcernPcdByUserCodeResponse {

    protected GiAdminResult getAllGiAdminConcernPcdByUserCodeResult;

    /**
     * 获取getAllGiAdminConcernPcdByUserCodeResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link GiAdminResult }
     *     
     */
    public GiAdminResult getGetAllGiAdminConcernPcdByUserCodeResult() {
        return getAllGiAdminConcernPcdByUserCodeResult;
    }

    /**
     * 设置getAllGiAdminConcernPcdByUserCodeResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link GiAdminResult }
     *     
     */
    public void setGetAllGiAdminConcernPcdByUserCodeResult(GiAdminResult value) {
        this.getAllGiAdminConcernPcdByUserCodeResult = value;
    }

}

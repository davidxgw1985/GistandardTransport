
package com.gistandard.transport.system.webservice.client.gps;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>giAdminResult complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="giAdminResult"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="allGiAdminConcernPcd" type="{http://webservice.manage.gps.com/}giAdminConcernPcd" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="inGiAdmin" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "giAdminResult", propOrder = {
    "allGiAdminConcernPcd",
    "inGiAdmin"
})
public class GiAdminResult {

    @XmlElement(nillable = true)
    protected List<GiAdminConcernPcd> allGiAdminConcernPcd;
    protected boolean inGiAdmin;

    /**
     * Gets the value of the allGiAdminConcernPcd property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allGiAdminConcernPcd property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAllGiAdminConcernPcd().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GiAdminConcernPcd }
     * 
     * 
     */
    public List<GiAdminConcernPcd> getAllGiAdminConcernPcd() {
        if (allGiAdminConcernPcd == null) {
            allGiAdminConcernPcd = new ArrayList<GiAdminConcernPcd>();
        }
        return this.allGiAdminConcernPcd;
    }

    /**
     * 获取inGiAdmin属性的值。
     * 
     */
    public boolean isInGiAdmin() {
        return inGiAdmin;
    }

    /**
     * 设置inGiAdmin属性的值。
     * 
     */
    public void setInGiAdmin(boolean value) {
        this.inGiAdmin = value;
    }

}


package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>APRegResult的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="APRegResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="success"/&gt;
 *     &lt;enumeration value="illegalAP"/&gt;
 *     &lt;enumeration value="CMAbilityNotSup"/&gt;
 *     &lt;enumeration value="repeatedReg"/&gt;
 *     &lt;enumeration value="svcAddrMismatch"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "APRegResult")
@XmlEnum
public enum APRegResult {

    @XmlEnumValue("success")
    SUCCESS("success"),
    @XmlEnumValue("illegalAP")
    ILLEGAL_AP("illegalAP"),
    @XmlEnumValue("CMAbilityNotSup")
    CM_ABILITY_NOT_SUP("CMAbilityNotSup"),
    @XmlEnumValue("repeatedReg")
    REPEATED_REG("repeatedReg"),
    @XmlEnumValue("svcAddrMismatch")
    SVC_ADDR_MISMATCH("svcAddrMismatch"), ;
    private final String value;

    APRegResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static APRegResult fromValue(String v) {
        for (APRegResult c: APRegResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

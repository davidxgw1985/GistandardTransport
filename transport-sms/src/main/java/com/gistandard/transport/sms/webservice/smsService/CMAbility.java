
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>CMAbility的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="CMAbility"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SMSAbility"/&gt;
 *     &lt;enumeration value="MMSAbility"/&gt;
 *     &lt;enumeration value="WAPAbility"/&gt;
 *     &lt;enumeration value="USSDAbility"/&gt;
 *     &lt;enumeration value="LBSAbility"/&gt;
 *     &lt;enumeration value="GPRSAbility"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "CMAbility", namespace = "http://www.csapi.org/schema/common/v2_0")
@XmlEnum
public enum CMAbility {

    @XmlEnumValue("SMSAbility")
    SMS_ABILITY("SMSAbility"),
    @XmlEnumValue("MMSAbility")
    MMS_ABILITY("MMSAbility"),
    @XmlEnumValue("WAPAbility")
    WAP_ABILITY("WAPAbility"),
    @XmlEnumValue("USSDAbility")
    USSD_ABILITY("USSDAbility"),
    @XmlEnumValue("LBSAbility")
    LBS_ABILITY("LBSAbility"),
    @XmlEnumValue("GPRSAbility")
    GPRS_ABILITY("GPRSAbility");
    private final String value;

    CMAbility(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CMAbility fromValue(String v) {
        for (CMAbility c: CMAbility.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>APLogoutResult的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="APLogoutResult"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="success"/&gt;
 *     &lt;enumeration value="illegalAPid"/&gt;
 *     &lt;enumeration value="repeatedLogout"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "APLogoutResult")
@XmlEnum
public enum APLogoutResult {

    @XmlEnumValue("success")
    SUCCESS("success"),
    @XmlEnumValue("illegalAPid")
    ILLEGAL_A_PID("illegalAPid"),
    @XmlEnumValue("repeatedLogout")
    REPEATED_LOGOUT("repeatedLogout");
    private final String value;

    APLogoutResult(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static APLogoutResult fromValue(String v) {
        for (APLogoutResult c: APLogoutResult.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

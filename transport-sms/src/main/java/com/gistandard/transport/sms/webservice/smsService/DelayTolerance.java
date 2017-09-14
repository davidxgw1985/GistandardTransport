
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DelayTolerance的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="DelayTolerance"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="NoDelay"/&gt;
 *     &lt;enumeration value="LowDelay"/&gt;
 *     &lt;enumeration value="DelayTolerant"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DelayTolerance", namespace = "http://www.csapi.org/schema/location")
@XmlEnum
public enum DelayTolerance {

    @XmlEnumValue("NoDelay")
    NO_DELAY("NoDelay"),
    @XmlEnumValue("LowDelay")
    LOW_DELAY("LowDelay"),
    @XmlEnumValue("DelayTolerant")
    DELAY_TOLERANT("DelayTolerant");
    private final String value;

    DelayTolerance(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DelayTolerance fromValue(String v) {
        for (DelayTolerance c: DelayTolerance.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

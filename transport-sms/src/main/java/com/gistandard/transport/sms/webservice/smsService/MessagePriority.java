
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>MessagePriority的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="MessagePriority"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Default"/&gt;
 *     &lt;enumeration value="Low"/&gt;
 *     &lt;enumeration value="Normal"/&gt;
 *     &lt;enumeration value="High"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "MessagePriority", namespace = "http://www.csapi.org/schema/mms")
@XmlEnum
public enum MessagePriority {

    @XmlEnumValue("Default")
    DEFAULT("Default"),
    @XmlEnumValue("Low")
    LOW("Low"),
    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("High")
    HIGH("High");
    private final String value;

    MessagePriority(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static MessagePriority fromValue(String v) {
        for (MessagePriority c: MessagePriority.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}


package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DeliveryStatus的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="DeliveryStatus"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="DeliveredToNetwork"/&gt;
 *     &lt;enumeration value="DeliveryUncertain"/&gt;
 *     &lt;enumeration value="DeliveryImpossible"/&gt;
 *     &lt;enumeration value="MessageWaiting"/&gt;
 *     &lt;enumeration value="DeliveredToTerminal"/&gt;
 *     &lt;enumeration value="DeliveryNotificationNotSupported"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "DeliveryStatus", namespace = "http://www.csapi.org/schema/mms")
@XmlEnum
public enum DeliveryStatus2 {

    @XmlEnumValue("DeliveredToNetwork")
    DELIVERED_TO_NETWORK("DeliveredToNetwork"),
    @XmlEnumValue("DeliveryUncertain")
    DELIVERY_UNCERTAIN("DeliveryUncertain"),
    @XmlEnumValue("DeliveryImpossible")
    DELIVERY_IMPOSSIBLE("DeliveryImpossible"),
    @XmlEnumValue("MessageWaiting")
    MESSAGE_WAITING("MessageWaiting"),
    @XmlEnumValue("DeliveredToTerminal")
    DELIVERED_TO_TERMINAL("DeliveredToTerminal"),
    @XmlEnumValue("DeliveryNotificationNotSupported")
    DELIVERY_NOTIFICATION_NOT_SUPPORTED("DeliveryNotificationNotSupported");
    private final String value;

    DeliveryStatus2(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeliveryStatus2 fromValue(String v) {
        for (DeliveryStatus2 c: DeliveryStatus2 .values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

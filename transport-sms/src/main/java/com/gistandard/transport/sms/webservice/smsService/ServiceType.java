
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ServiceType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="ServiceType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="STANDARD"/&gt;
 *     &lt;enumeration value="EMERGENCY"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "ServiceType", namespace = "http://www.csapi.org/schema/location")
@XmlEnum
public enum ServiceType {

    STANDARD,
    EMERGENCY;

    public String value() {
        return name();
    }

    public static ServiceType fromValue(String v) {
        return valueOf(v);
    }

}

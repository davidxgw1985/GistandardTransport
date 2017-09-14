
package com.gistandard.transport.sms.webservice.smsService;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>LocType的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="LocType"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="CURRENT"/&gt;
 *     &lt;enumeration value="LAST"/&gt;
 *     &lt;enumeration value="CURRENT_OR_LAST"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "LocType", namespace = "http://www.csapi.org/schema/location")
@XmlEnum
public enum LocType {

    CURRENT,
    LAST,
    CURRENT_OR_LAST;

    public String value() {
        return name();
    }

    public static LocType fromValue(String v) {
        return valueOf(v);
    }

}

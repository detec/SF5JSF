
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for carrierFrequency.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="carrierFrequency">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Lower"/>
 *     &lt;enumeration value="Top"/>
 *     &lt;enumeration value="CRange"/>
 *     &lt;enumeration value="TopPie"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "carrierFrequency")
@XmlEnum
public enum CarrierFrequency {

    @XmlEnumValue("Lower")
    LOWER("Lower"),
    @XmlEnumValue("Top")
    TOP("Top"),
    @XmlEnumValue("CRange")
    C_RANGE("CRange"),
    @XmlEnumValue("TopPie")
    TOP_PIE("TopPie");
    private final String value;

    CarrierFrequency(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static CarrierFrequency fromValue(String v) {
        for (CarrierFrequency c: CarrierFrequency.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

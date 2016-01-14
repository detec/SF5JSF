
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for rangesOfDVB.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="rangesOfDVB">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Ku"/>
 *     &lt;enumeration value="C"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "rangesOfDVB")
@XmlEnum
public enum RangesOfDVB {

    @XmlEnumValue("Ku")
    KU("Ku"),
    C("C");
    private final String value;

    RangesOfDVB(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RangesOfDVB fromValue(String v) {
        for (RangesOfDVB c: RangesOfDVB.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

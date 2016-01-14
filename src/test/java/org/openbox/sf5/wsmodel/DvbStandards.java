
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dvbStandards.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="dvbStandards">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="DVBS"/>
 *     &lt;enumeration value="DVBS2"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "dvbStandards")
@XmlEnum
public enum DvbStandards {

    DVBS("DVBS"),
    @XmlEnumValue("DVBS2")
    DVBS_2("DVBS2");
    private final String value;

    DvbStandards(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DvbStandards fromValue(String v) {
        for (DvbStandards c: DvbStandards.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}

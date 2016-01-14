
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSatelliteById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSatelliteById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputSatelliteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSatelliteById", propOrder = {
    "inputSatelliteId"
})
public class GetSatelliteById {

    protected long inputSatelliteId;

    /**
     * Gets the value of the inputSatelliteId property.
     * 
     */
    public long getInputSatelliteId() {
        return inputSatelliteId;
    }

    /**
     * Sets the value of the inputSatelliteId property.
     * 
     */
    public void setInputSatelliteId(long value) {
        this.inputSatelliteId = value;
    }

}

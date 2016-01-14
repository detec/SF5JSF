
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTranspondersBySatelliteId complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTranspondersBySatelliteId">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputSatId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTranspondersBySatelliteId", propOrder = {
    "inputSatId"
})
public class GetTranspondersBySatelliteId {

    protected long inputSatId;

    /**
     * Gets the value of the inputSatId property.
     * 
     */
    public long getInputSatId() {
        return inputSatId;
    }

    /**
     * Sets the value of the inputSatId property.
     * 
     */
    public void setInputSatId(long value) {
        this.inputSatId = value;
    }

}

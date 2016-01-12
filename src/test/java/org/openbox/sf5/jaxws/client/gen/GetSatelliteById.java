
package org.openbox.sf5.jaxws.client.gen;

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
 *         &lt;element name="satelliteId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
    "satelliteId"
})
public class GetSatelliteById {

    protected long satelliteId;

    /**
     * Gets the value of the satelliteId property.
     * 
     */
    public long getSatelliteId() {
        return satelliteId;
    }

    /**
     * Sets the value of the satelliteId property.
     * 
     */
    public void setSatelliteId(long value) {
        this.satelliteId = value;
    }

}


package org.openbox.sf5.jaxws.client.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSatellitesByArbitraryFilterResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSatellitesByArbitraryFilterResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://sf5.openbox.org/satelliteservice/1.0}genericXMLListWrapper" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSatellitesByArbitraryFilterResponse", propOrder = {
    "_return"
})
public class GetSatellitesByArbitraryFilterResponse {

    @XmlElement(name = "return")
    protected GenericXMLListWrapper _return;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link GenericXMLListWrapper }
     *     
     */
    public GenericXMLListWrapper getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     * 
     * @param value
     *     allowed object is
     *     {@link GenericXMLListWrapper }
     *     
     */
    public void setReturn(GenericXMLListWrapper value) {
        this._return = value;
    }

}

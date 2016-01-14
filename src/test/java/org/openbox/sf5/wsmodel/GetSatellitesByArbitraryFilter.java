
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSatellitesByArbitraryFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSatellitesByArbitraryFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputFieldName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="inputFieldValue" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getSatellitesByArbitraryFilter", propOrder = {
    "inputFieldName",
    "inputFieldValue"
})
public class GetSatellitesByArbitraryFilter {

    protected String inputFieldName;
    protected String inputFieldValue;

    /**
     * Gets the value of the inputFieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputFieldName() {
        return inputFieldName;
    }

    /**
     * Sets the value of the inputFieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputFieldName(String value) {
        this.inputFieldName = value;
    }

    /**
     * Gets the value of the inputFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputFieldValue() {
        return inputFieldValue;
    }

    /**
     * Sets the value of the inputFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputFieldValue(String value) {
        this.inputFieldValue = value;
    }

}

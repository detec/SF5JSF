
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getTransponderById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getTransponderById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputTransponderId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getTransponderById", propOrder = {
    "inputTransponderId"
})
public class GetTransponderById {

    protected long inputTransponderId;

    /**
     * Gets the value of the inputTransponderId property.
     * 
     */
    public long getInputTransponderId() {
        return inputTransponderId;
    }

    /**
     * Sets the value of the inputTransponderId property.
     * 
     */
    public void setInputTransponderId(long value) {
        this.inputTransponderId = value;
    }

}

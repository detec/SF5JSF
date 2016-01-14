
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createSetting complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createSetting">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="arg0" type="{http://wsmodel.sf5.openbox.org/}settings" minOccurs="0"/>
 *         &lt;element name="inputLogin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createSetting", propOrder = {
    "arg0",
    "inputLogin"
})
public class CreateSetting {

    protected Settings arg0;
    protected String inputLogin;

    /**
     * Gets the value of the arg0 property.
     * 
     * @return
     *     possible object is
     *     {@link Settings }
     *     
     */
    public Settings getArg0() {
        return arg0;
    }

    /**
     * Sets the value of the arg0 property.
     * 
     * @param value
     *     allowed object is
     *     {@link Settings }
     *     
     */
    public void setArg0(Settings value) {
        this.arg0 = value;
    }

    /**
     * Gets the value of the inputLogin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInputLogin() {
        return inputLogin;
    }

    /**
     * Sets the value of the inputLogin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInputLogin(String value) {
        this.inputLogin = value;
    }

}


package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getSettingById complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getSettingById">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputSettingId" type="{http://www.w3.org/2001/XMLSchema}long"/>
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
@XmlType(name = "getSettingById", propOrder = {
    "inputSettingId",
    "inputLogin"
})
public class GetSettingById {

    protected long inputSettingId;
    protected String inputLogin;

    /**
     * Gets the value of the inputSettingId property.
     * 
     */
    public long getInputSettingId() {
        return inputSettingId;
    }

    /**
     * Sets the value of the inputSettingId property.
     * 
     */
    public void setInputSettingId(long value) {
        this.inputSettingId = value;
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

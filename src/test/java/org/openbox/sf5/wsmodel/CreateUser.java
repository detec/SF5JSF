
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createUser complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createUser">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inputUser" type="{http://wsmodel.sf5.openbox.org/}users" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createUser", propOrder = {
    "inputUser"
})
public class CreateUser {

    protected Users inputUser;

    /**
     * Gets the value of the inputUser property.
     * 
     * @return
     *     possible object is
     *     {@link Users }
     *     
     */
    public Users getInputUser() {
        return inputUser;
    }

    /**
     * Sets the value of the inputUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Users }
     *     
     */
    public void setInputUser(Users value) {
        this.inputUser = value;
    }

}

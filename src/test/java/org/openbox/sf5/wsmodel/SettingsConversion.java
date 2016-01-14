
package org.openbox.sf5.wsmodel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for settingsConversion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="settingsConversion">
 *   &lt;complexContent>
 *     &lt;extension base="{http://wsmodel.sf5.openbox.org/}abstractDbEntity">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="parent_id" type="{http://wsmodel.sf5.openbox.org/}settings" minOccurs="0"/>
 *         &lt;element name="LineNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Transponder" type="{http://wsmodel.sf5.openbox.org/}transponders" minOccurs="0"/>
 *         &lt;element name="Satindex" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Tpindex" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="Note" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TheLineOfIntersection" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "settingsConversion", propOrder = {
    "id",
    "parentId",
    "lineNumber",
    "transponder",
    "satindex",
    "tpindex",
    "note",
    "theLineOfIntersection"
})
public class SettingsConversion
    extends AbstractDbEntity
{

    protected long id;
    @XmlElement(name = "parent_id")
    protected Settings parentId;
    @XmlElement(name = "LineNumber")
    protected long lineNumber;
    @XmlElement(name = "Transponder")
    protected Transponders transponder;
    @XmlElement(name = "Satindex")
    protected long satindex;
    @XmlElement(name = "Tpindex")
    protected long tpindex;
    @XmlElement(name = "Note")
    protected String note;
    @XmlElement(name = "TheLineOfIntersection")
    protected long theLineOfIntersection;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the parentId property.
     * 
     * @return
     *     possible object is
     *     {@link Settings }
     *     
     */
    public Settings getParentId() {
        return parentId;
    }

    /**
     * Sets the value of the parentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Settings }
     *     
     */
    public void setParentId(Settings value) {
        this.parentId = value;
    }

    /**
     * Gets the value of the lineNumber property.
     * 
     */
    public long getLineNumber() {
        return lineNumber;
    }

    /**
     * Sets the value of the lineNumber property.
     * 
     */
    public void setLineNumber(long value) {
        this.lineNumber = value;
    }

    /**
     * Gets the value of the transponder property.
     * 
     * @return
     *     possible object is
     *     {@link Transponders }
     *     
     */
    public Transponders getTransponder() {
        return transponder;
    }

    /**
     * Sets the value of the transponder property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transponders }
     *     
     */
    public void setTransponder(Transponders value) {
        this.transponder = value;
    }

    /**
     * Gets the value of the satindex property.
     * 
     */
    public long getSatindex() {
        return satindex;
    }

    /**
     * Sets the value of the satindex property.
     * 
     */
    public void setSatindex(long value) {
        this.satindex = value;
    }

    /**
     * Gets the value of the tpindex property.
     * 
     */
    public long getTpindex() {
        return tpindex;
    }

    /**
     * Sets the value of the tpindex property.
     * 
     */
    public void setTpindex(long value) {
        this.tpindex = value;
    }

    /**
     * Gets the value of the note property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
    }

    /**
     * Gets the value of the theLineOfIntersection property.
     * 
     */
    public long getTheLineOfIntersection() {
        return theLineOfIntersection;
    }

    /**
     * Sets the value of the theLineOfIntersection property.
     * 
     */
    public void setTheLineOfIntersection(long value) {
        this.theLineOfIntersection = value;
    }

}

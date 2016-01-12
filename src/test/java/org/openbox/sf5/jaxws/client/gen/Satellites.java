
package org.openbox.sf5.jaxws.client.gen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Satellites", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Satellites {


    /**
     * 
     * @return
     *     returns org.openbox.sf5.jaxws.client.gen.GenericXMLListWrapper
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAllSatellites", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetAllSatellites")
    @ResponseWrapper(localName = "getAllSatellitesResponse", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetAllSatellitesResponse")
    public GenericXMLListWrapper getAllSatellites();

    /**
     * 
     * @param satelliteId
     * @return
     *     returns org.openbox.sf5.jaxws.client.gen.Satellites_Type
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSatelliteById", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetSatelliteById")
    @ResponseWrapper(localName = "getSatelliteByIdResponse", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetSatelliteByIdResponse")
    public Satellites_Type getSatelliteById(
        @WebParam(name = "satelliteId", targetNamespace = "")
        long satelliteId);

    /**
     * 
     * @param typeValue
     * @param type
     * @return
     *     returns org.openbox.sf5.jaxws.client.gen.GenericXMLListWrapper
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getSatellitesByArbitraryFilter", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetSatellitesByArbitraryFilter")
    @ResponseWrapper(localName = "getSatellitesByArbitraryFilterResponse", targetNamespace = "http://sf5.openbox.org/satelliteservice/1.0", className = "org.openbox.sf5.jaxws.client.gen.GetSatellitesByArbitraryFilterResponse")
    public GenericXMLListWrapper getSatellitesByArbitraryFilter(
        @WebParam(name = "type", targetNamespace = "")
        String type,
        @WebParam(name = "typeValue", targetNamespace = "")
        String typeValue);

}

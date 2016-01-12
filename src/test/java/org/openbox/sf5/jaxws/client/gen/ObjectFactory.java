
package org.openbox.sf5.jaxws.client.gen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openbox.sf5.jaxws.client.gen package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetSatellitesByArbitraryFilter_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getSatellitesByArbitraryFilter");
    private final static QName _Satellites_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "satellites");
    private final static QName _GetSatelliteById_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getSatelliteById");
    private final static QName _GetSatelliteByIdResponse_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getSatelliteByIdResponse");
    private final static QName _GetAllSatellitesResponse_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getAllSatellitesResponse");
    private final static QName _GetSatellitesByArbitraryFilterResponse_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getSatellitesByArbitraryFilterResponse");
    private final static QName _GetAllSatellites_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "getAllSatellites");
    private final static QName _Classstub_QNAME = new QName("http://sf5.openbox.org/satelliteservice/1.0", "classstub");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openbox.sf5.jaxws.client.gen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Satellites_Type }
     * 
     */
    public Satellites_Type createSatellites_Type() {
        return new Satellites_Type();
    }

    /**
     * Create an instance of {@link GetSatellitesByArbitraryFilter }
     * 
     */
    public GetSatellitesByArbitraryFilter createGetSatellitesByArbitraryFilter() {
        return new GetSatellitesByArbitraryFilter();
    }

    /**
     * Create an instance of {@link GetSatelliteById }
     * 
     */
    public GetSatelliteById createGetSatelliteById() {
        return new GetSatelliteById();
    }

    /**
     * Create an instance of {@link GetSatelliteByIdResponse }
     * 
     */
    public GetSatelliteByIdResponse createGetSatelliteByIdResponse() {
        return new GetSatelliteByIdResponse();
    }

    /**
     * Create an instance of {@link GenericXMLListWrapper }
     * 
     */
    public GenericXMLListWrapper createGenericXMLListWrapper() {
        return new GenericXMLListWrapper();
    }

    /**
     * Create an instance of {@link GetAllSatellitesResponse }
     * 
     */
    public GetAllSatellitesResponse createGetAllSatellitesResponse() {
        return new GetAllSatellitesResponse();
    }

    /**
     * Create an instance of {@link GetSatellitesByArbitraryFilterResponse }
     * 
     */
    public GetSatellitesByArbitraryFilterResponse createGetSatellitesByArbitraryFilterResponse() {
        return new GetSatellitesByArbitraryFilterResponse();
    }

    /**
     * Create an instance of {@link GetAllSatellites }
     * 
     */
    public GetAllSatellites createGetAllSatellites() {
        return new GetAllSatellites();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatellitesByArbitraryFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getSatellitesByArbitraryFilter")
    public JAXBElement<GetSatellitesByArbitraryFilter> createGetSatellitesByArbitraryFilter(GetSatellitesByArbitraryFilter value) {
        return new JAXBElement<GetSatellitesByArbitraryFilter>(_GetSatellitesByArbitraryFilter_QNAME, GetSatellitesByArbitraryFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Satellites_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "satellites")
    public JAXBElement<Satellites_Type> createSatellites(Satellites_Type value) {
        return new JAXBElement<Satellites_Type>(_Satellites_QNAME, Satellites_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatelliteById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getSatelliteById")
    public JAXBElement<GetSatelliteById> createGetSatelliteById(GetSatelliteById value) {
        return new JAXBElement<GetSatelliteById>(_GetSatelliteById_QNAME, GetSatelliteById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatelliteByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getSatelliteByIdResponse")
    public JAXBElement<GetSatelliteByIdResponse> createGetSatelliteByIdResponse(GetSatelliteByIdResponse value) {
        return new JAXBElement<GetSatelliteByIdResponse>(_GetSatelliteByIdResponse_QNAME, GetSatelliteByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSatellitesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getAllSatellitesResponse")
    public JAXBElement<GetAllSatellitesResponse> createGetAllSatellitesResponse(GetAllSatellitesResponse value) {
        return new JAXBElement<GetAllSatellitesResponse>(_GetAllSatellitesResponse_QNAME, GetAllSatellitesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatellitesByArbitraryFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getSatellitesByArbitraryFilterResponse")
    public JAXBElement<GetSatellitesByArbitraryFilterResponse> createGetSatellitesByArbitraryFilterResponse(GetSatellitesByArbitraryFilterResponse value) {
        return new JAXBElement<GetSatellitesByArbitraryFilterResponse>(_GetSatellitesByArbitraryFilterResponse_QNAME, GetSatellitesByArbitraryFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSatellites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "getAllSatellites")
    public JAXBElement<GetAllSatellites> createGetAllSatellites(GetAllSatellites value) {
        return new JAXBElement<GetAllSatellites>(_GetAllSatellites_QNAME, GetAllSatellites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericXMLListWrapper }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://sf5.openbox.org/satelliteservice/1.0", name = "classstub")
    public JAXBElement<GenericXMLListWrapper> createClassstub(GenericXMLListWrapper value) {
        return new JAXBElement<GenericXMLListWrapper>(_Classstub_QNAME, GenericXMLListWrapper.class, null, value);
    }

}

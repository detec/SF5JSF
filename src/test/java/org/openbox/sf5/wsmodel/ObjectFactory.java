
package org.openbox.sf5.wsmodel;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.openbox.sf5.wsmodel package. 
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

    private final static QName _GetSettingByIdResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingByIdResponse");
    private final static QName _GetSettingsByArbitraryFilter_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingsByArbitraryFilter");
    private final static QName _GetTransponders_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTransponders");
    private final static QName _IfSuchLoginExistsResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "ifSuchLoginExistsResponse");
    private final static QName _GetTranspondersByArbitraryFilter_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTranspondersByArbitraryFilter");
    private final static QName _GetSettingsByUserLogin_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingsByUserLogin");
    private final static QName _GetAllSatellites_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getAllSatellites");
    private final static QName _GetSatellitesByArbitraryFilter_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSatellitesByArbitraryFilter");
    private final static QName _GetTransponderById_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTransponderById");
    private final static QName _GetSettingsByUserLoginResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingsByUserLoginResponse");
    private final static QName _GetSettingById_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingById");
    private final static QName _GetUserByLogin_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getUserByLogin");
    private final static QName _GetTransponderByIdResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTransponderByIdResponse");
    private final static QName _GetUserByLoginResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getUserByLoginResponse");
    private final static QName _CreateSetting_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "createSetting");
    private final static QName _Transponders_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "transponders");
    private final static QName _GetTranspondersBySatelliteIdResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTranspondersBySatelliteIdResponse");
    private final static QName _CreateUser_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "createUser");
    private final static QName _IfSuchLoginExists_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "ifSuchLoginExists");
    private final static QName _GetAllSatellitesResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getAllSatellitesResponse");
    private final static QName _SettingsConversion_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "settingsConversion");
    private final static QName _CheckVerifyFault_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "CheckVerifyFault");
    private final static QName _GetTranspondersResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTranspondersResponse");
    private final static QName _Satellites_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "satellites");
    private final static QName _CreateSettingResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "createSettingResponse");
    private final static QName _Settings_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "settings");
    private final static QName _CreateUserResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "createUserResponse");
    private final static QName _GetSatelliteById_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSatelliteById");
    private final static QName _GetSatelliteByIdResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSatelliteByIdResponse");
    private final static QName _SatellitesTable_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "SatellitesTable");
    private final static QName _GetSettingsByArbitraryFilterResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSettingsByArbitraryFilterResponse");
    private final static QName _GetTranspondersBySatelliteId_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTranspondersBySatelliteId");
    private final static QName _GetSatellitesByArbitraryFilterResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getSatellitesByArbitraryFilterResponse");
    private final static QName _Users_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "users");
    private final static QName _GetTranspondersByArbitraryFilterResponse_QNAME = new QName("http://wsmodel.sf5.openbox.org/", "getTranspondersByArbitraryFilterResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.openbox.sf5.wsmodel
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTransponderById }
     * 
     */
    public GetTransponderById createGetTransponderById() {
        return new GetTransponderById();
    }

    /**
     * Create an instance of {@link GetSatellitesByArbitraryFilter }
     * 
     */
    public GetSatellitesByArbitraryFilter createGetSatellitesByArbitraryFilter() {
        return new GetSatellitesByArbitraryFilter();
    }

    /**
     * Create an instance of {@link GetSettingsByUserLoginResponse }
     * 
     */
    public GetSettingsByUserLoginResponse createGetSettingsByUserLoginResponse() {
        return new GetSettingsByUserLoginResponse();
    }

    /**
     * Create an instance of {@link GetSettingById }
     * 
     */
    public GetSettingById createGetSettingById() {
        return new GetSettingById();
    }

    /**
     * Create an instance of {@link GetUserByLogin }
     * 
     */
    public GetUserByLogin createGetUserByLogin() {
        return new GetUserByLogin();
    }

    /**
     * Create an instance of {@link GetUserByLoginResponse }
     * 
     */
    public GetUserByLoginResponse createGetUserByLoginResponse() {
        return new GetUserByLoginResponse();
    }

    /**
     * Create an instance of {@link GetTransponderByIdResponse }
     * 
     */
    public GetTransponderByIdResponse createGetTransponderByIdResponse() {
        return new GetTransponderByIdResponse();
    }

    /**
     * Create an instance of {@link IfSuchLoginExistsResponse }
     * 
     */
    public IfSuchLoginExistsResponse createIfSuchLoginExistsResponse() {
        return new IfSuchLoginExistsResponse();
    }

    /**
     * Create an instance of {@link GetSettingByIdResponse }
     * 
     */
    public GetSettingByIdResponse createGetSettingByIdResponse() {
        return new GetSettingByIdResponse();
    }

    /**
     * Create an instance of {@link GetSettingsByArbitraryFilter }
     * 
     */
    public GetSettingsByArbitraryFilter createGetSettingsByArbitraryFilter() {
        return new GetSettingsByArbitraryFilter();
    }

    /**
     * Create an instance of {@link GetTransponders }
     * 
     */
    public GetTransponders createGetTransponders() {
        return new GetTransponders();
    }

    /**
     * Create an instance of {@link GetTranspondersByArbitraryFilter }
     * 
     */
    public GetTranspondersByArbitraryFilter createGetTranspondersByArbitraryFilter() {
        return new GetTranspondersByArbitraryFilter();
    }

    /**
     * Create an instance of {@link GetSettingsByUserLogin }
     * 
     */
    public GetSettingsByUserLogin createGetSettingsByUserLogin() {
        return new GetSettingsByUserLogin();
    }

    /**
     * Create an instance of {@link GetAllSatellites }
     * 
     */
    public GetAllSatellites createGetAllSatellites() {
        return new GetAllSatellites();
    }

    /**
     * Create an instance of {@link Satellites }
     * 
     */
    public Satellites createSatellites() {
        return new Satellites();
    }

    /**
     * Create an instance of {@link CreateSettingResponse }
     * 
     */
    public CreateSettingResponse createCreateSettingResponse() {
        return new CreateSettingResponse();
    }

    /**
     * Create an instance of {@link Settings }
     * 
     */
    public Settings createSettings() {
        return new Settings();
    }

    /**
     * Create an instance of {@link GetTranspondersResponse }
     * 
     */
    public GetTranspondersResponse createGetTranspondersResponse() {
        return new GetTranspondersResponse();
    }

    /**
     * Create an instance of {@link CreateUserResponse }
     * 
     */
    public CreateUserResponse createCreateUserResponse() {
        return new CreateUserResponse();
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
     * Create an instance of {@link SettingsSatellites }
     * 
     */
    public SettingsSatellites createSettingsSatellites() {
        return new SettingsSatellites();
    }

    /**
     * Create an instance of {@link GetTranspondersBySatelliteId }
     * 
     */
    public GetTranspondersBySatelliteId createGetTranspondersBySatelliteId() {
        return new GetTranspondersBySatelliteId();
    }

    /**
     * Create an instance of {@link GetSettingsByArbitraryFilterResponse }
     * 
     */
    public GetSettingsByArbitraryFilterResponse createGetSettingsByArbitraryFilterResponse() {
        return new GetSettingsByArbitraryFilterResponse();
    }

    /**
     * Create an instance of {@link GetTranspondersByArbitraryFilterResponse }
     * 
     */
    public GetTranspondersByArbitraryFilterResponse createGetTranspondersByArbitraryFilterResponse() {
        return new GetTranspondersByArbitraryFilterResponse();
    }

    /**
     * Create an instance of {@link GetSatellitesByArbitraryFilterResponse }
     * 
     */
    public GetSatellitesByArbitraryFilterResponse createGetSatellitesByArbitraryFilterResponse() {
        return new GetSatellitesByArbitraryFilterResponse();
    }

    /**
     * Create an instance of {@link Users }
     * 
     */
    public Users createUsers() {
        return new Users();
    }

    /**
     * Create an instance of {@link CreateSetting }
     * 
     */
    public CreateSetting createCreateSetting() {
        return new CreateSetting();
    }

    /**
     * Create an instance of {@link Transponders }
     * 
     */
    public Transponders createTransponders() {
        return new Transponders();
    }

    /**
     * Create an instance of {@link GetTranspondersBySatelliteIdResponse }
     * 
     */
    public GetTranspondersBySatelliteIdResponse createGetTranspondersBySatelliteIdResponse() {
        return new GetTranspondersBySatelliteIdResponse();
    }

    /**
     * Create an instance of {@link CreateUser }
     * 
     */
    public CreateUser createCreateUser() {
        return new CreateUser();
    }

    /**
     * Create an instance of {@link IfSuchLoginExists }
     * 
     */
    public IfSuchLoginExists createIfSuchLoginExists() {
        return new IfSuchLoginExists();
    }

    /**
     * Create an instance of {@link WSException }
     * 
     */
    public WSException createWSException() {
        return new WSException();
    }

    /**
     * Create an instance of {@link GetAllSatellitesResponse }
     * 
     */
    public GetAllSatellitesResponse createGetAllSatellitesResponse() {
        return new GetAllSatellitesResponse();
    }

    /**
     * Create an instance of {@link SettingsConversion }
     * 
     */
    public SettingsConversion createSettingsConversion() {
        return new SettingsConversion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingByIdResponse")
    public JAXBElement<GetSettingByIdResponse> createGetSettingByIdResponse(GetSettingByIdResponse value) {
        return new JAXBElement<GetSettingByIdResponse>(_GetSettingByIdResponse_QNAME, GetSettingByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingsByArbitraryFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingsByArbitraryFilter")
    public JAXBElement<GetSettingsByArbitraryFilter> createGetSettingsByArbitraryFilter(GetSettingsByArbitraryFilter value) {
        return new JAXBElement<GetSettingsByArbitraryFilter>(_GetSettingsByArbitraryFilter_QNAME, GetSettingsByArbitraryFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTransponders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTransponders")
    public JAXBElement<GetTransponders> createGetTransponders(GetTransponders value) {
        return new JAXBElement<GetTransponders>(_GetTransponders_QNAME, GetTransponders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IfSuchLoginExistsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "ifSuchLoginExistsResponse")
    public JAXBElement<IfSuchLoginExistsResponse> createIfSuchLoginExistsResponse(IfSuchLoginExistsResponse value) {
        return new JAXBElement<IfSuchLoginExistsResponse>(_IfSuchLoginExistsResponse_QNAME, IfSuchLoginExistsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTranspondersByArbitraryFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTranspondersByArbitraryFilter")
    public JAXBElement<GetTranspondersByArbitraryFilter> createGetTranspondersByArbitraryFilter(GetTranspondersByArbitraryFilter value) {
        return new JAXBElement<GetTranspondersByArbitraryFilter>(_GetTranspondersByArbitraryFilter_QNAME, GetTranspondersByArbitraryFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingsByUserLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingsByUserLogin")
    public JAXBElement<GetSettingsByUserLogin> createGetSettingsByUserLogin(GetSettingsByUserLogin value) {
        return new JAXBElement<GetSettingsByUserLogin>(_GetSettingsByUserLogin_QNAME, GetSettingsByUserLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSatellites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getAllSatellites")
    public JAXBElement<GetAllSatellites> createGetAllSatellites(GetAllSatellites value) {
        return new JAXBElement<GetAllSatellites>(_GetAllSatellites_QNAME, GetAllSatellites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatellitesByArbitraryFilter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSatellitesByArbitraryFilter")
    public JAXBElement<GetSatellitesByArbitraryFilter> createGetSatellitesByArbitraryFilter(GetSatellitesByArbitraryFilter value) {
        return new JAXBElement<GetSatellitesByArbitraryFilter>(_GetSatellitesByArbitraryFilter_QNAME, GetSatellitesByArbitraryFilter.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTransponderById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTransponderById")
    public JAXBElement<GetTransponderById> createGetTransponderById(GetTransponderById value) {
        return new JAXBElement<GetTransponderById>(_GetTransponderById_QNAME, GetTransponderById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingsByUserLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingsByUserLoginResponse")
    public JAXBElement<GetSettingsByUserLoginResponse> createGetSettingsByUserLoginResponse(GetSettingsByUserLoginResponse value) {
        return new JAXBElement<GetSettingsByUserLoginResponse>(_GetSettingsByUserLoginResponse_QNAME, GetSettingsByUserLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingById")
    public JAXBElement<GetSettingById> createGetSettingById(GetSettingById value) {
        return new JAXBElement<GetSettingById>(_GetSettingById_QNAME, GetSettingById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByLogin }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getUserByLogin")
    public JAXBElement<GetUserByLogin> createGetUserByLogin(GetUserByLogin value) {
        return new JAXBElement<GetUserByLogin>(_GetUserByLogin_QNAME, GetUserByLogin.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTransponderByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTransponderByIdResponse")
    public JAXBElement<GetTransponderByIdResponse> createGetTransponderByIdResponse(GetTransponderByIdResponse value) {
        return new JAXBElement<GetTransponderByIdResponse>(_GetTransponderByIdResponse_QNAME, GetTransponderByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserByLoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getUserByLoginResponse")
    public JAXBElement<GetUserByLoginResponse> createGetUserByLoginResponse(GetUserByLoginResponse value) {
        return new JAXBElement<GetUserByLoginResponse>(_GetUserByLoginResponse_QNAME, GetUserByLoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSetting }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "createSetting")
    public JAXBElement<CreateSetting> createCreateSetting(CreateSetting value) {
        return new JAXBElement<CreateSetting>(_CreateSetting_QNAME, CreateSetting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Transponders }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "transponders")
    public JAXBElement<Transponders> createTransponders(Transponders value) {
        return new JAXBElement<Transponders>(_Transponders_QNAME, Transponders.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTranspondersBySatelliteIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTranspondersBySatelliteIdResponse")
    public JAXBElement<GetTranspondersBySatelliteIdResponse> createGetTranspondersBySatelliteIdResponse(GetTranspondersBySatelliteIdResponse value) {
        return new JAXBElement<GetTranspondersBySatelliteIdResponse>(_GetTranspondersBySatelliteIdResponse_QNAME, GetTranspondersBySatelliteIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUser }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "createUser")
    public JAXBElement<CreateUser> createCreateUser(CreateUser value) {
        return new JAXBElement<CreateUser>(_CreateUser_QNAME, CreateUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IfSuchLoginExists }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "ifSuchLoginExists")
    public JAXBElement<IfSuchLoginExists> createIfSuchLoginExists(IfSuchLoginExists value) {
        return new JAXBElement<IfSuchLoginExists>(_IfSuchLoginExists_QNAME, IfSuchLoginExists.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllSatellitesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getAllSatellitesResponse")
    public JAXBElement<GetAllSatellitesResponse> createGetAllSatellitesResponse(GetAllSatellitesResponse value) {
        return new JAXBElement<GetAllSatellitesResponse>(_GetAllSatellitesResponse_QNAME, GetAllSatellitesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SettingsConversion }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "settingsConversion")
    public JAXBElement<SettingsConversion> createSettingsConversion(SettingsConversion value) {
        return new JAXBElement<SettingsConversion>(_SettingsConversion_QNAME, SettingsConversion.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WSException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "CheckVerifyFault")
    public JAXBElement<WSException> createCheckVerifyFault(WSException value) {
        return new JAXBElement<WSException>(_CheckVerifyFault_QNAME, WSException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTranspondersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTranspondersResponse")
    public JAXBElement<GetTranspondersResponse> createGetTranspondersResponse(GetTranspondersResponse value) {
        return new JAXBElement<GetTranspondersResponse>(_GetTranspondersResponse_QNAME, GetTranspondersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Satellites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "satellites")
    public JAXBElement<Satellites> createSatellites(Satellites value) {
        return new JAXBElement<Satellites>(_Satellites_QNAME, Satellites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateSettingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "createSettingResponse")
    public JAXBElement<CreateSettingResponse> createCreateSettingResponse(CreateSettingResponse value) {
        return new JAXBElement<CreateSettingResponse>(_CreateSettingResponse_QNAME, CreateSettingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Settings }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "settings")
    public JAXBElement<Settings> createSettings(Settings value) {
        return new JAXBElement<Settings>(_Settings_QNAME, Settings.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "createUserResponse")
    public JAXBElement<CreateUserResponse> createCreateUserResponse(CreateUserResponse value) {
        return new JAXBElement<CreateUserResponse>(_CreateUserResponse_QNAME, CreateUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatelliteById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSatelliteById")
    public JAXBElement<GetSatelliteById> createGetSatelliteById(GetSatelliteById value) {
        return new JAXBElement<GetSatelliteById>(_GetSatelliteById_QNAME, GetSatelliteById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatelliteByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSatelliteByIdResponse")
    public JAXBElement<GetSatelliteByIdResponse> createGetSatelliteByIdResponse(GetSatelliteByIdResponse value) {
        return new JAXBElement<GetSatelliteByIdResponse>(_GetSatelliteByIdResponse_QNAME, GetSatelliteByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SettingsSatellites }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "SatellitesTable")
    public JAXBElement<SettingsSatellites> createSatellitesTable(SettingsSatellites value) {
        return new JAXBElement<SettingsSatellites>(_SatellitesTable_QNAME, SettingsSatellites.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSettingsByArbitraryFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSettingsByArbitraryFilterResponse")
    public JAXBElement<GetSettingsByArbitraryFilterResponse> createGetSettingsByArbitraryFilterResponse(GetSettingsByArbitraryFilterResponse value) {
        return new JAXBElement<GetSettingsByArbitraryFilterResponse>(_GetSettingsByArbitraryFilterResponse_QNAME, GetSettingsByArbitraryFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTranspondersBySatelliteId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTranspondersBySatelliteId")
    public JAXBElement<GetTranspondersBySatelliteId> createGetTranspondersBySatelliteId(GetTranspondersBySatelliteId value) {
        return new JAXBElement<GetTranspondersBySatelliteId>(_GetTranspondersBySatelliteId_QNAME, GetTranspondersBySatelliteId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSatellitesByArbitraryFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getSatellitesByArbitraryFilterResponse")
    public JAXBElement<GetSatellitesByArbitraryFilterResponse> createGetSatellitesByArbitraryFilterResponse(GetSatellitesByArbitraryFilterResponse value) {
        return new JAXBElement<GetSatellitesByArbitraryFilterResponse>(_GetSatellitesByArbitraryFilterResponse_QNAME, GetSatellitesByArbitraryFilterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Users }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "users")
    public JAXBElement<Users> createUsers(Users value) {
        return new JAXBElement<Users>(_Users_QNAME, Users.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTranspondersByArbitraryFilterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://wsmodel.sf5.openbox.org/", name = "getTranspondersByArbitraryFilterResponse")
    public JAXBElement<GetTranspondersByArbitraryFilterResponse> createGetTranspondersByArbitraryFilterResponse(GetTranspondersByArbitraryFilterResponse value) {
        return new JAXBElement<GetTranspondersByArbitraryFilterResponse>(_GetTranspondersByArbitraryFilterResponse_QNAME, GetTranspondersByArbitraryFilterResponse.class, null, value);
    }

}

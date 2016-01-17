# Openbox SF-5 settings editor, JSF version #

This pet project derives from my old 1C:Enterprise 8.2 (<http://1c-dn.com/>) tool, released in 2010, for satellite television gadget, Openbox SF-5.
- <http://openbox.ua/instruments/sf5/>   - official page of the gadget;
- <http://infostart.ru/public/76804/>	 - page of the initial 1C:Enterprise 8.2 project.

## Features ##

Leaving behind satellite television details, Openbox SF-5 settings editor is a representation of a typical full-cycle CRUD application. It is able to:

- import catalogue data from structured text files (refreshed transponder data from resources like <http://ru.kingofsat.net>) into relational database;
- create and edit own entities (gadget settings) using catalogue data, store them in database and reuse when needed;
- export gadget settings into structured XML files for exchange with vendor owned gadget application;
- output user composed gadget settings to a print form, so that a user can have a hard copy of settings when using Openbox SF-5.

When replicating this project from its 1C:Enterprise 8.2 original, I hardly tried to repeat in pure Java Server Faces 2.2 all GUI features that I had previously implemented in its original, 1C:Enterprise desktop version, to ensure good end-user experience. It includes:

- comfortable usage of transponder catalogue, single and multiple transponder selection;
- powerful feature of selection of settings lines from other settings while editing current setting;
- ability to move lines up and down in a setting.

## User authentication ##

This implementation of Openbox SF-5 settings editor provides simplified (without password) SQL-based user authentication and registration, so that it can be run in a cloud. Authentication has been implemented with custom developed servlet filter, which filters all xhtml requests, except /jaxrs/ endpoints and JAX-WS OpenboxSF5Service. Each user can register and access his/her SF-5 settings. Additionally users have a right to update common catalogue with transponder data, without the need for the administrator to do this routine job. The rights system is peer in this application.

## REST service ##

This Openbox SF-5 settings editor implementation provides JAX-RS 2.0 API for getting entities from database and posting new ones with the help of GlassFish's built-in Jersey 2. EclipseLink Moxy 2.6 is used for marshalling and unmarshalling both in JSON and XML. Output format is resolved by client's "Accept" HTTP header, "application/json" or "application/xml". Here is the list of supported endpoints, relative to application context path:

- Satellites
	- jaxrs/satellites/all/ GET 						- get all satellites;
	- jaxrs/satellites/filter/{type}/{typeValue} GET 	- get satellites, filtered by arbitrary field name and field value;
	- jaxrs/satellites/filter/id/{satelliteId} GET  	- get satellite by its ID.
	
- Transponders	
	- jaxrs/transponders/all/ GET 						- get all transponders;
	- jaxrs/transponders/filter;satId={satId} GET 		- get all transponders from specified satellite;
	- jaxrs/transponders/filter/id/{transponderId} GET 	- get transponder by its ID;
	- jaxrs/transponders/filter/{type}/{typeValue} GET 	- get transponders, filtered by arbitrary field name and field value;
	- jaxrs/transponders/upload POST 					- upload .ini file with transponders for further import. Content-type should be multipart/form-data.
	
- OpenBox SF-5 settings
	- jaxrs/usersettings/filter/id/{settingId};login={login} GET 		- get setting by its ID and user login;
	- jaxrs/usersettings/filter/id/{settingId}/sf5;login={login} GET	- get setting by its ID and user login in Openbox SF-5 XML format, only "application/xml" "Accept" HTTP header is supported;
	- jaxrs/usersettings/filter/{type}/{typeValue};login={login} GET 	- get user's settings, filtered by arbitrary field name and field value, with provided user login;
	- jaxrs/usersettings/filter/login/{typeValue} GET 					- get all user's settings;
	- jaxrs/usersettings/create;login={login} POST 						- send new setting to save in database for user specified. User should already exist, login in matrix parameter and in "User" field should coincide. New setting ID is returned in HTTP header "SettingId".
	
- Users
	- jaxrs/users/filter/login/{login} GET 				- get user by its login, if found;
	- jaxrs/users/exists/login/{login} GET 				- enables to check if the user with such login name exists, returning user ID;
	- jaxrs/users/create POST 							- send new user to save in database, new user ID is returned as response body.

## JAX-WS service ##

The service name is OpenboxSF5Service, its WSDL can be obtained at /application_context_path/OpenboxSF5Service?wsdl . It is built on standard library and uses all capabilities of JAX-RS service, that are responsible for data exchange, except Openbox SF-5 XML format output.
	
## Maven profiles ##

Different Maven profiles are required to use different database schemes and integration tests. Openbox SF-5 settings editor uses 3 maven profiles:

	- dev 	Default profile, database url is jdbc:h2:tcp://localhost/~/sf5jsfdev. 
			This database url is used in Eclipse-based container deploy.
	- test 	Profile for additional integration tests, run with Cargo Maven plugin in H2 in-memory mode;
	- prod 	Profile for production builds, database url is jdbc:h2:tcp://localhost/~/sf5jsf.

## Tests notice ##

There are several JUnit tests, run in H2 in-memory mode. They check if Hibernate works with the database engine specified, if backend data processing features work, if SF-5 XML output matches the reference string. But as a former 1C:Enterprise developer I strongly believe that only real client-server environment can show if there are some errors with settings or annotations. That is why Cargo maven plugin is used in Maven test profile. Its paramount purpose is to test JAX-RS endpoints and JAX-WS. Every aspect of Openbox SF-5 settings editor is tested: transponders upload and select, user creation and select, satellites select, user settings creation and select. Jersey 2 client is used in JAX-RS integration tests, for JAX-WS auto-generated by wsimport classes provide client connectivity. Most important of the auto-generated stub classes are validated with constraints defined in XML files.

## System requirements ##

- GlassFish 4.1;
- H2 database server, running at the same host with GlassFish (for profiles dev and prod);
- Java 8.

## Technologies ##

- Java Server Faces 2.2;
- Jersey 2;
- EclipseLink MOXy 2.6;
- Hibernate ORM 4.3.11;
- Hibernate POJO classes and mappings were generated from my 1C:Enterprise database using my 1C:Enterprise project <https://github.com/detec/POJOClassesGenerationForHibernate>;
- Hibernate Validator 5.2;
- JUnit 4.12;
- Jersey 2 client;
- Maven 3.3 with plugins compiler, surefire, resources, war, cargo, jaxws;
- GlassFish 4.1;
- Java 8.

The project can be built either with Maven (3.3 or higher) or Eclipse (4.5 or higher).
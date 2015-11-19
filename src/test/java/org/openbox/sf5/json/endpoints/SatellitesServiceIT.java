package org.openbox.sf5.json.endpoints;

import java.io.File;
import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openbox.sf5.dao.DAO;
import org.openbox.sf5.dao.DAOImpl;
import org.openbox.sf5.dao.DAOList;
import org.openbox.sf5.dao.DAOListImpl;
import org.openbox.sf5.db.ConnectionManager;
import org.openbox.sf5.json.ApplicationConfig;
import org.openbox.sf5.json.service.CommonJsonizer;
import org.openbox.sf5.json.service.SatellitesJsonizer;
import org.openbox.sf5.model.Satellites;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectService;
import org.openbox.sf5.service.ObjectServiceImpl;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

// For now, it is not clear how to use this method.

// http://webstar.company/2014/02/testing-the-jax-rs-restful-web-service-2/
@RunWith(Arquillian.class)
public class SatellitesServiceIT {

	@ArquillianResource
	URL deploymentUrl;

	@Deployment
	public static WebArchive create() {
		WebArchive webArch = ShrinkWrap.create(WebArchive.class, "rest-service.war").addClasses(Satellites.class,
				SatellitesService.class, ApplicationConfig.class, CommonJsonizer.class, SatellitesJsonizer.class
				// DAO and service layers
				, ConnectionManager.class, DAO.class, DAOImpl.class, DAOList.class, DAOListImpl.class,
				CriterionService.class, ObjectsController.class, ObjectService.class, ObjectServiceImpl.class,
				ObjectsListService.class); // classes and other resources into
											// the war

		webArch.addAsResource(new File("src/main/resources/hibernate.cfg.xml"));
		webArch.addAsResource(new File("src/main/resources/transponders/0048.ini"));
		webArch.addAsResource(new File("src/main/resources/transponders/0130.ini"));
		webArch.addAsResource(new File("src/main/resources/transponders/0420.ini"));
		return webArch;
	}

	// @Test
	// public void testGetBookByTitle() {
	// BookRequest request = new BookRequest();
	// request.setTitle("War and peace");
	//
	// Response response = given()
	// .body(request)
	// .contentType(ContentType.JSON)
	// .expect()
	// .contentType(ContentType.JSON)
	// .statusCode(Status.OK.getStatusCode())
	// .when()
	// .post("http://localhost:8080/rest-service/rest/book/by_title");
	//
	// Book book = response.as(Book.class);

	// }

	@Test
	public void shouldgetSatelliteById() {

	}

	URI buildUri(String... paths) {
		UriBuilder builder = UriBuilder.fromUri(deploymentUrl.toString());
		for (String path : paths) {
			builder.path(path);
		}
		return builder.build();
	}

}

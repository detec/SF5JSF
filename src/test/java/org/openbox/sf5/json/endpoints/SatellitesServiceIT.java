package org.openbox.sf5.json.endpoints;

import java.net.URI;
import java.net.URL;

import javax.ws.rs.core.UriBuilder;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;
import org.openbox.sf5.db.Satellites;

// http://webstar.company/2014/02/testing-the-jax-rs-restful-web-service-2/
@RunWith(Arquillian.class)
public class SatellitesServiceIT {

	@ArquillianResource
    URL deploymentUrl;

    @Deployment
    public static WebArchive create() {
        return ShrinkWrap.create(WebArchive.class, "rest-service.war")
                .addClasses(Satellites.class
                        ); // classes and other resources into the war
    }

//    @Test
//    public void testGetBookByTitle() {
//        given()
//            .body(new BookRequest("War and peace"))
//            .contentType(ContentType.JSON)
//        .expect()
//            .contentType(ContentType.JSON)
//            .statusCode(Status.OK.getStatusCode())
//        .when()
//            .post(buildUri("rest", "book", "by_title"));
//    }

    URI buildUri(String... paths) {
        UriBuilder builder = UriBuilder.fromUri(deploymentUrl.toString());
        for (String path : paths) {
            builder.path(path);
        }
        return builder.build();
    }

}

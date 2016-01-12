package org.openbox.sf5.json.endpoints;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbox.sf5.common.IniReader;
import org.openbox.sf5.model.Satellites;
import org.openbox.sf5.model.Transponders;
import org.openbox.sf5.service.CriterionService;
import org.openbox.sf5.service.ObjectsController;
import org.openbox.sf5.service.ObjectsListService;

@Named
@SessionScoped
@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
@Consumes({ MediaType.MULTIPART_FORM_DATA })
@Path("transponders/")
public class TranspondersService implements Serializable {

	private static final long serialVersionUID = 330376972384785311L;

	// different types of params
	// https://www-01.ibm.com/support/knowledgecenter/SS7K4U_8.5.5/com.ibm.websphere.base.doc/ae/twbs_jaxrs_defresource_parmexchdata.html

	// Good combined params example
	// http://www.mkyong.com/webservices/jax-rs/jax-rs-matrixparam-example/

	// http://howtodoinjava.com/2015/08/05/jersey-file-upload-example/
	@POST
	@Path("upload")
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	public Response importTransponderFile(@FormDataParam("file") InputStream fileInputStream,
			@FormDataParam("file") FormDataContentDisposition fileMetaData) {

		// Boolean result =
		// transpondersJsonizer.uploadTransponders(fileInputStream,
		// fileMetaData);
		Response returnResponse = null;

		try {
			iniReader.readMultiPartFile(fileInputStream, fileMetaData);
			returnResponse = Response.status(200).build();

		} catch (Exception e) {
			// return new Boolean(false);
			returnResponse = Response.status(500).entity(e.getMessage()).build();
		}

		// GenericEntity<Boolean> gBoolean = new GenericEntity<Boolean>(result);
		// return Response.status(200).build();
		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/Speed/27500

	@GET
	@Path("filter/{type}/{typeValue}")
	public Response getTranspondersByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;
		Criterion criterion = criterionService.getCriterionByClassFieldAndStringValue(Transponders.class, fieldName,
				typeValue);

		if (criterion == null) {
			returnResponse = Response.status(204)
					.entity("Criterion not built by filed " + fieldName + " and field value " + typeValue).build();
		}

		else {
			List<Transponders> transList = listService.ObjectsCriterionList(Transponders.class, criterion);

			if (transList.isEmpty()) {
				returnResponse = Response.status(204).entity("No transponders selected by criterion with field "
						+ fieldName + " and field value " + typeValue).build();
			} else {
				GenericEntity<List<Transponders>> gtransList = new GenericEntity<List<Transponders>>(transList) {
				};
				returnResponse = Response.status(200).entity(gtransList).build();
			}
		}

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/id/56

	@GET
	@Path("filter/id/{transponderId}")
	public Response getTransponderById(@PathParam("transponderId") long tpId) {

		Response returnResponse = null;
		Transponders trans = objectsController.select(Transponders.class, tpId);
		if (trans == null) {
			returnResponse = Response.status(204).entity("No transponder found by id " + tpId).build();
		} else {
			returnResponse = Response.status(200).entity(trans).build();
		}
		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter;satId=1
	@GET
	@Path("filter/")
	public Response getTranspondersBySatelliteId(@MatrixParam("satId") long satId) {

		Response returnResponse = null;

		Satellites filterSatellite = objectsController.select(Satellites.class, satId);
		if (filterSatellite == null) {
			returnResponse = Response.status(204).entity("No satellite found by id " + satId).build();
		} else {
			Criterion criterion = Restrictions.eq("Satellite", filterSatellite);

			List<Transponders> transList = listService.ObjectsCriterionList(Transponders.class, criterion);
			if (transList.isEmpty()) {
				returnResponse = Response.status(204)
						.entity("No transponders have been selected from satellite " + filterSatellite.toString())
						.build();
			} else {
				GenericEntity<List<Transponders>> gtransList = new GenericEntity<List<Transponders>>(transList) {
				};
				returnResponse = Response.status(200).entity(gtransList).build();
			}
		}

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/all/

	@GET
	@Path("all")
	public Response getTransponders() {

		Response returnResponse = null;
		List<Transponders> transList = listService.ObjectsList(Transponders.class);

		if (transList.isEmpty()) {
			returnResponse = Response.status(204).entity("No transponders have been selected from database").build();
		} else {
			GenericEntity<List<Transponders>> gtransList = new GenericEntity<List<Transponders>>(transList) {
			};
			returnResponse = Response.status(200).entity(gtransList).build();
		}

		return returnResponse;
	}

	@Inject
	private CriterionService criterionService;

	@Inject
	private ObjectsListService listService;

	@Inject
	private ObjectsController objectsController;

	@Inject
	private IniReader iniReader;

}

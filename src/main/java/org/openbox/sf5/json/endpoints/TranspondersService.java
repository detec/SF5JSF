package org.openbox.sf5.json.endpoints;

import java.io.InputStream;
import java.io.Serializable;

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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.openbox.sf5.json.service.CommonJsonizer;
import org.openbox.sf5.json.service.TranspondersJsonizer;
import org.openbox.sf5.model.Transponders;

@Named
@SessionScoped
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

		Boolean result = transpondersJsonizer.uploadTransponders(fileInputStream, fileMetaData);
		return Response.status(200).entity(result).build();
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/Speed/27500
	@GET
	@Produces("application/json")
	@Path("filter/{type}/{typeValue}")
	public Response getTranspondersByArbitraryFilter(@PathParam("type") String fieldName,
			@PathParam("typeValue") String typeValue) {

		Response returnResponse = null;
		String result = transpondersJsonizer.getTranspondersByArbitraryFilter(fieldName, typeValue);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter/id/56
	@GET
	@Produces("application/json")
	@Path("filter/id/{transponderId}")
	public Response getTransponderById(@PathParam("transponderId") long tpId) {

		Response returnResponse = null;
		String result = commonJsonizer.buildJsonStringByTypeAndId(tpId, Transponders.class);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}
		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/filter;satId=1
	@GET
	@Produces("application/json")
	@Path("filter/")
	public Response getTranspondersBySatelliteId(@MatrixParam("satId") long satId) {

		Response returnResponse = null;
		String result = transpondersJsonizer.getTranspondersBySatelliteId(satId);
		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}
		return returnResponse;
	}

	// http://localhost:8080/SF5JSF-test/json/transponders/all/
	@GET
	@Produces("application/json")
	@Path("all/")
	public Response getTransponders() {

		Response returnResponse = null;
		String result = transpondersJsonizer.getTransponders();

		if (result.isEmpty()) {
			return Response.status(404).build();
		} else {
			returnResponse = Response.status(200).entity(result).build();
		}

		return returnResponse;
	}

	@Inject
	private CommonJsonizer commonJsonizer;

	@Inject
	private TranspondersJsonizer transpondersJsonizer;

	public TranspondersJsonizer getTranspondersJsonizer() {
		return transpondersJsonizer;
	}

	public void setTranspondersJsonizer(TranspondersJsonizer transpondersJsonizer) {
		this.transpondersJsonizer = transpondersJsonizer;
	}

	public CommonJsonizer getCommonJsonizer() {
		return commonJsonizer;
	}

	public void setCommonJsonizer(CommonJsonizer commonJsonizer) {
		this.commonJsonizer = commonJsonizer;
	}
}

package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.SensorData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.api.store.DataEntry;
import io.swagger.api.store.DataStore;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-21T14:54:57.056Z")
public class SensordataApiServiceImpl extends SensordataApiService {


	private DataStore ds = new DataStore();

	@Override
	public Response addSensorData(SensorData body, SecurityContext securityContext) throws NotFoundException {

		ds.put(body);
		
		return Response.ok().build();
	}

	@Override
	public Response getSensorData(String sourceId, String sensorId, Boolean latestOnly, SecurityContext securityContext)
			throws NotFoundException {

		if (sourceId != null && sensorId != null) {
			// get values from specific source & Sensor
			return Response.ok().build();
		} else if (sourceId == null && sensorId != null) {
			// get valus for specific sensor
			return Response.ok().build();
		} else if (sourceId != null && sensorId == null) {
			// get values for specific source
			return Response.ok().build();
		} else {
			// get all values for all sources and sensors
			return Response.ok().build();
		}

		
		// return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK,
		// "magic!")).build();
	}
}

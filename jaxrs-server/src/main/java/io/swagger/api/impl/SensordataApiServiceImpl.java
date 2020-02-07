package io.swagger.api.impl;

import io.swagger.api.*;

import io.swagger.model.SensorData;

import io.swagger.api.NotFoundException;
import io.swagger.api.store.DataStore;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-21T14:54:57.056Z")
public class SensordataApiServiceImpl extends SensordataApiService {

	private DataStore ds = new DataStore();

	@Override
	public Response addSensorData(SensorData body, SecurityContext securityContext) throws NotFoundException {

//		ds.put(body);
		ds.generateRandomData();

		return Response.ok().build();
	}

	@Override
	public Response getSensorData(String sourceId, String sensorId, String start, String end,
			SecurityContext securityContext) {

		if (start != null && end != null) {
			if (sourceId != null && sensorId != null) {
				// get values from specific source & Sensor
				return Response.ok(ds.getSourceSensorStartEnd(sensorId, sourceId, start, end)).build();
			} else if (sourceId == null && sensorId != null) {
				// get values for specific sensor
				return Response.ok(ds.getSensorStartEnd(sensorId, start, end)).build();
			} else if (sourceId != null && sensorId == null) {
				// get values for specific source
				return Response.ok(ds.getSourceStartEnd(sourceId, start, end)).build();
			} else {
				// get all values for all sources and sensors
				return Response.ok(ds.getAllStartEnd(start, end)).build();
			}
		} else if (start != null && end == null) {
			if (sourceId != null && sensorId != null) {
				// get values from specific source & Sensor
				return Response.ok(ds.getSourceSensorStart(sensorId, sourceId, start)).build();
			} else if (sourceId == null && sensorId != null) {
				// get values for specific sensor
				return Response.ok(ds.getSensorStart(sensorId, start)).build();
			} else if (sourceId != null && sensorId == null) {
				// get values for specific source
				return Response.ok(ds.getSourceStart(sourceId, start)).build();
			} else {
				// get all values for all sources and sensors
				System.out.println("Reached");
				return Response.ok(ds.getAllStart(start)).build();
			}
		} else if (start == null && end != null) {
			return Response.ok("Wrong usage - only end date specified").build();
		} else {
			if (sourceId != null && sensorId != null) {
				// get values from specific source & Sensor
				return Response.ok(ds.getSourceSensor(sensorId, sourceId)).build();
			} else if (sourceId == null && sensorId != null) {
				// get values for specific sensor
				return Response.ok(ds.getSensor(sensorId)).build();
			} else if (sourceId != null && sensorId == null) {
				// get values for specific source
				return Response.ok(ds.getSource(sourceId)).build();
			} else {
				// get all values for all sources and sensors
				return Response.ok(ds.getAll()).build();
			}
		}
	}
}

package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.SensorData;

import java.util.ArrayList;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-21T14:54:57.056Z")
public class SensordataApiServiceImpl extends SensordataApiService {
	
	private List<String[]> store = new ArrayList<String[]>();
	
	
	
	
    @Override
    public Response addSensorData(SensorData body, SecurityContext securityContext) throws NotFoundException {
    	
    	String[] entry = {body.getSourceId(),body.getSensorId(),body.getUnit(),body.getValue().toString(), body.getTimestamp().toString()};
    	
    	store.add(entry);
        return Response.ok().build();
    }
    @Override
    public Response getSensorData( String sourceId,  String sensorId,  Boolean latestOnly, SecurityContext securityContext) throws NotFoundException {
        // do some magic!
        System.out.println(store.get(0)[0].toString());
        return Response.ok(store.get(0)[0].toString()).build();
    	
        //return Response.ok().entity(new ApiResponseMessage(ApiResponseMessage.OK, "magic!")).build();
    }
}

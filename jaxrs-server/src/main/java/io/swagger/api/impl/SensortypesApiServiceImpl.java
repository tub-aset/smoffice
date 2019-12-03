package io.swagger.api.impl;

import io.swagger.api.*;
import io.swagger.model.*;

import io.swagger.model.SensorTypes;

import java.util.List;
import io.swagger.api.NotFoundException;
import io.swagger.api.store.DataStore;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-22T13:40:08.591Z")
public class SensortypesApiServiceImpl extends SensortypesApiService {
    
	private DataStore ds = new DataStore();
	
	@Override
    public Response getSensorTypes(SecurityContext securityContext) throws NotFoundException {
    	
    	List<SensorTypes> response = ds.getSensorTypes();
        return Response.ok(response).build();
    }
}

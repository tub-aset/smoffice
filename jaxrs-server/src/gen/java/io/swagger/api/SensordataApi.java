package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.SensordataApiService;
import io.swagger.api.factories.SensordataApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.SensorData;

import java.util.Map;
import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import javax.servlet.ServletConfig;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.*;
import javax.validation.constraints.*;

@Path("/sensordata")


@io.swagger.annotations.Api(description = "the sensordata API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-22T14:49:02.090Z")
public class SensordataApi  {
   private final SensordataApiService delegate;

   public SensordataApi(@Context ServletConfig servletContext) {
      SensordataApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SensordataApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SensordataApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SensordataApiServiceFactory.getSensordataApi();
      }

      this.delegate = delegate;
   }

    @POST
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Add new sensor data to the server", notes = "", response = Void.class, tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 405, message = "Invalid input", response = Void.class) })
    public Response addSensorData(@ApiParam(value = "Sensor data object" ,required=true) SensorData body
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.addSensorData(body,securityContext);
    }
    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get all sensor data", notes = "", response = SensorData.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "", response = SensorData.class, responseContainer = "List") })
    public Response getSensorData(@ApiParam(value = "") @QueryParam("sourceId") String sourceId
,@ApiParam(value = "") @QueryParam("sensorId") String sensorId
,@ApiParam(value = "the time at which the query answer starts") @QueryParam("start") String start
,@ApiParam(value = "the time at which the query answer ends") @QueryParam("end") String end
,@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSensorData(sourceId,sensorId,start,end,securityContext);
    }
}

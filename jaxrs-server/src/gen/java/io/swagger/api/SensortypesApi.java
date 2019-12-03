package io.swagger.api;

import io.swagger.model.*;
import io.swagger.api.SensortypesApiService;
import io.swagger.api.factories.SensortypesApiServiceFactory;

import io.swagger.annotations.ApiParam;
import io.swagger.jaxrs.*;

import io.swagger.model.SensorTypes;

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

@Path("/sensortypes")


@io.swagger.annotations.Api(description = "the sensortypes API")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-22T14:49:02.090Z")
public class SensortypesApi  {
   private final SensortypesApiService delegate;

   public SensortypesApi(@Context ServletConfig servletContext) {
      SensortypesApiService delegate = null;

      if (servletContext != null) {
         String implClass = servletContext.getInitParameter("SensortypesApi.implementation");
         if (implClass != null && !"".equals(implClass.trim())) {
            try {
               delegate = (SensortypesApiService) Class.forName(implClass).newInstance();
            } catch (Exception e) {
               throw new RuntimeException(e);
            }
         } 
      }

      if (delegate == null) {
         delegate = SensortypesApiServiceFactory.getSensortypesApi();
      }

      this.delegate = delegate;
   }

    @GET
    
    @Consumes({ "application/json", "application/xml" })
    @Produces({ "application/xml", "application/json" })
    @io.swagger.annotations.ApiOperation(value = "Get all sensor types", notes = "", response = SensorTypes.class, responseContainer = "List", tags={  })
    @io.swagger.annotations.ApiResponses(value = { 
        @io.swagger.annotations.ApiResponse(code = 200, message = "", response = SensorTypes.class, responseContainer = "List") })
    public Response getSensorTypes(@Context SecurityContext securityContext)
    throws NotFoundException {
        return delegate.getSensorTypes(securityContext);
    }
}

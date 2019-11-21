package io.swagger.api;

import io.swagger.api.*;
import io.swagger.model.*;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;

import io.swagger.model.SensorData;

import java.util.List;
import io.swagger.api.NotFoundException;

import java.io.InputStream;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.validation.constraints.*;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-21T14:54:57.056Z")
public abstract class SensordataApiService {
    public abstract Response addSensorData(SensorData body,SecurityContext securityContext) throws NotFoundException;
    public abstract Response getSensorData( String sourceId, String sensorId, Boolean latestOnly,SecurityContext securityContext) throws NotFoundException;
}

package io.swagger.api.factories;

import io.swagger.api.SensordataApiService;
import io.swagger.api.impl.SensordataApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-21T14:54:57.056Z")
public class SensordataApiServiceFactory {
    private final static SensordataApiService service = new SensordataApiServiceImpl();

    public static SensordataApiService getSensordataApi() {
        return service;
    }
}

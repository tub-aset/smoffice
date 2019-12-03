package io.swagger.api.factories;

import io.swagger.api.SensortypesApiService;
import io.swagger.api.impl.SensortypesApiServiceImpl;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2019-11-22T13:40:08.591Z")
public class SensortypesApiServiceFactory {
    private final static SensortypesApiService service = new SensortypesApiServiceImpl();

    public static SensortypesApiService getSensortypesApi() {
        return service;
    }
}

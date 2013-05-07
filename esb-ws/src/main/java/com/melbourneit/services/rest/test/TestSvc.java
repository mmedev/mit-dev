package com.melbourneit.services.rest.test;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/testsvc/")
public interface TestSvc{

    @GET
    @Path("/checkDomain/{domain}/{clientRefId}/{logId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkDomain(@PathParam("domain") String domain, @PathParam("clientRefId") String clientRefId, @PathParam("logId") String logId);

}

package com.melbourneit.services.rest.domain;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/service/")
public interface DomainRS{

    @GET
    @Path("/checkDomain/{domain}")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkDomain(@PathParam("domain") String domain);

}

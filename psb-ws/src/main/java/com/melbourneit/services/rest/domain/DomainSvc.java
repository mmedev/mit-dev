package com.melbourneit.services.rest.domain;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.melbourneit.model.rest.CheckDomainRequest;
import com.melbourneit.model.rest.DelegateDomainRequest;
import com.melbourneit.model.rest.GetRenewalInformationRequest;
import com.melbourneit.model.rest.MultiDomainCheckRequest;
import com.melbourneit.model.rest.UpdateContactDetailsRequest;
import com.melbourneit.model.rest.UpdateLockStatusRequest;


@Path("/domainsvc/")
public interface DomainSvc{

    @POST
    @Path("/checkDomain/")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkDomain(CheckDomainRequest request);

    @POST
    @Path("/getRenewalInformation/")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRenewalInformation(GetRenewalInformationRequest request);

    @POST
    @Path("/updateLockStatus/")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateLockStatus(UpdateLockStatusRequest request);

    @POST
    @Path("/delegateDomain/")
    @Produces(MediaType.TEXT_PLAIN)
    public String delegateDomain(DelegateDomainRequest request);

    @POST
    @Path("/updateContactDetails/")
    @Produces(MediaType.TEXT_PLAIN)
    public String updateContactDetails(UpdateContactDetailsRequest request);

    @POST
    @Path("/checkMultipleDomains/")
    @Produces(MediaType.TEXT_PLAIN)
    public String checkMultipleDomains(MultiDomainCheckRequest request);
    
    @GET
    @Path("/test/")
    @Produces(MediaType.TEXT_PLAIN)
    public String test();
 
}

package com.melbourneit.services.rest.domain;

import com.melbourneit.model.rest.CheckDomainRequest;
import com.melbourneit.model.rest.DelegateDomainRequest;
import com.melbourneit.model.rest.GetRenewalInformationRequest;
import com.melbourneit.model.rest.MultiDomainCheckRequest;
import com.melbourneit.model.rest.UpdateContactDetailsRequest;
import com.melbourneit.model.rest.UpdateLockStatusRequest;
import com.melbourneit.services.psb.PSBServiceManagement;
import com.melbourneit.utils.RequestEntityToVOConverter;
import com.melbourneit.utils.UserDetails;

public class DomainSvcImpl implements DomainSvc{

    private PSBServiceManagement serviceManagement;
    private String username;
    private String password;

    public String checkDomain(CheckDomainRequest request){	
       return serviceManagement.checkDomainName(request.getName(), new UserDetails(username, password), request.getLogId(), request.getLogId()); 
    }

    public String getRenewalInformation(GetRenewalInformationRequest request){
        return serviceManagement.getRenewalInformation(request.getName(), new UserDetails(request.getUsername(), request.getPassword()),request.getLogId(), request.getLogId());
    }

    public String updateLockStatus(UpdateLockStatusRequest request){
       return serviceManagement.updateLockStatus(request.getName(), new UserDetails(request.getUsername(), request.getPassword()), request.getAction(), request.getLogId(), request.getLogId());    
    }

    public String delegateDomain(DelegateDomainRequest request){
        return serviceManagement.delegateDomain(request.getDomainName(),new UserDetails(request.getUsername(), request.getPassword()),request.getClientRefId(), request.getPrimaryNameServer(),request.getSecondaryNameServers(), request.getLogId());
    }

    public String updateContactDetails(UpdateContactDetailsRequest request){
       return serviceManagement.updateContactDetails(request.getDomainName(),new UserDetails(request.getUsername(), request.getPassword()),request.getClientRefId(), RequestEntityToVOConverter.convertXMLContactInfoToContactInfoVO(request), request.getLogId());
    }

    public String checkMultipleDomains(MultiDomainCheckRequest request){
        return serviceManagement.checkDomainNames(request.getDomains(), new UserDetails(request.getUsername(), request.getPassword()), request.getMaxExecWaitSeconds(), request.getLogId(), request.getLogId());
    }
    
	public void setServiceManagement(PSBServiceManagement serviceManagement) {
		this.serviceManagement = serviceManagement;
	}

	@Override
	public String test() {
		try{
			UserDetails details = new UserDetails("test", "test");
			String domainName = "google.com";
			String logId = "test";
			
			return serviceManagement.checkDomainName(domainName, details, logId, logId); 
		}catch(Exception e){
			return e.toString();
		}
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

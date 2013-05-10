package com.melbourneit.services.jms.domain;

import com.melbourneit.model.jms.domain.CheckDomainRequest;
import com.melbourneit.services.jms.JMSRequestService;
import com.melbourneit.services.psb.PSBServiceManagement;
import com.melbourneit.utils.UserDetails;

public class DomainServiceImpl extends JMSRequestService implements DomainService{

    private PSBServiceManagement serviceManagement;
    private String username;
    private String password;
    
    public String checkDomain(CheckDomainRequest request){	
       return serviceManagement.checkDomainName(request.getName(), new UserDetails(username, password), "", ""); 
    }

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setServiceManagement(PSBServiceManagement serviceManagement) {
		this.serviceManagement = serviceManagement;
	}
}

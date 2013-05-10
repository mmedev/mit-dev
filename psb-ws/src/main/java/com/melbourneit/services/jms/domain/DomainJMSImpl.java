package com.melbourneit.services.jms.domain;

import com.melbourneit.model.jms.domain.CheckDomainRequest;
import com.melbourneit.services.jms.JMSRequestService;
import com.melbourneit.services.psb.domain.DomainService;
import com.melbourneit.utils.UserDetails;

public class DomainJMSImpl extends JMSRequestService implements DomainJMS{

    private DomainService domainService;
    private String username;
    private String password;
    
    public String checkDomain(String request){	
    	try{
    		CheckDomainRequest jmsReq = (CheckDomainRequest) getJMSRequest(request);
    	    return generateResponseOK(domainService.checkDomainName(jmsReq.getName(), new UserDetails(username, password), "", ""));
    	}catch(Throwable e){
    		return generateResponseError(e.getMessage());
    	}
    }

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
}

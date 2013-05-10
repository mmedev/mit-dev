package com.melbourneit.services.rest.domain;

import com.melbourneit.exception.BaseException;
import com.melbourneit.exception.JMSResquestException;
import com.melbourneit.services.esb.domain.DomainService;

public class DomainRSImpl implements DomainRS{

	private DomainService domainService;
	
	@Override
	public String checkDomain(String domain) {	
		try {
			return domainService.checkDomainName(domain);	
		}catch(JMSResquestException e){
			return e.getCause() + " - TODO : Return Error response message to the client";
		}catch(BaseException e){
			return e.getCause() + " - TODO : Return Error response message to the client";
		}catch(Exception e){
			return e.getCause() + " - TODO : Return Error response message to the client";
		}
	}

	public void setDomainService(DomainService domainService) {
		this.domainService = domainService;
	}
}

package com.melbourneit.services.rest.test;

import com.melbourneit.services.esb.TestService;

public class TestSvcImpl implements TestSvc{

	private TestService testService;
	
	@Override
	public String checkDomain(String domain) {	
		try {
			return testService.checkDomainName(domain);	
		}catch (Exception e) {
			return e.toString() + " - Return Error response message to the client";
		}
	}
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}

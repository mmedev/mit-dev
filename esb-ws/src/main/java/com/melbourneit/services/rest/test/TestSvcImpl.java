package com.melbourneit.services.rest.test;

import com.melbourneit.services.esb.TestService;

public class TestSvcImpl implements TestSvc{

	private TestService testService;
	
	@Override
	public String checkDomain(String domain, String clientRefId, String logId) {	
		try {
			return testService.checkDomainName(domain, clientRefId, logId);	
		} catch (Exception e) {
			return e.toString();
		}
	}
	
	public void setTestService(TestService testService) {
		this.testService = testService;
	}
}

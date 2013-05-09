package com.melbourneit.services.esb;

import org.apache.camel.ProducerTemplate;
import com.melbourneit.model.rest.CheckDomainRequest;

public class TestServiceImpl implements TestService{

	private ProducerTemplate producer;
	
	@Override
	public String checkDomainName(String domainName, String clientRefId, String logId) {
		return (String) producer.requestBody("direct:domainCheck", new CheckDomainRequest(domainName, null, null));
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}
}



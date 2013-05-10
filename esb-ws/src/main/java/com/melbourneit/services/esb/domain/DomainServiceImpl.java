package com.melbourneit.services.esb.domain;

import org.apache.camel.ProducerTemplate;

import com.melbourneit.exception.BaseException;
import com.melbourneit.exception.JMSResquestException;
import com.melbourneit.model.jms.JMSReqRes;
import com.melbourneit.model.jms.domain.CheckDomainRequest;
import com.melbourneit.services.esb.GenericService;
import com.melbourneit.utils.jms.JMSEndpoint;
import com.melbourneit.utils.jms.JMSRequestProcessor;

public class DomainServiceImpl extends GenericService implements DomainService {

	@Override
	public String checkDomainName(String domainName) throws BaseException{
		return (String) new JMSRequestProcessor().process(producer, JMSEndpoint.DOMAIN_CHECK.toString(), new JMSReqRes(new CheckDomainRequest(domainName, null, null), null));	
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}
}



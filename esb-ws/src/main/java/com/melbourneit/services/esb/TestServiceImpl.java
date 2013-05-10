package com.melbourneit.services.esb;

import org.apache.camel.ProducerTemplate;

import com.melbourneit.exception.BaseException;
import com.melbourneit.exception.JMSResquestException;
import com.melbourneit.model.jms.domain.CheckDomainRequest;
import com.melbourneit.utils.jms.JMSEndpoint;
import com.melbourneit.utils.jms.JMSRequestProcessor;

public class TestServiceImpl extends GenericService implements TestService {

	@Override
	public String checkDomainName(String domainName) throws BaseException{
		try{
			return new JMSRequestProcessor<String>().process(producer, JMSEndpoint.DOMAIN_CHECK.toString(), new CheckDomainRequest(domainName, null, null));
		}catch(JMSResquestException e) {
			logger.error(e.getMessage(), e);
			throw new BaseException(e);
		}catch(Exception e){
			logger.error(e.getMessage(), e);
			throw new BaseException(e);
		}
	}

	public void setProducer(ProducerTemplate producer) {
		this.producer = producer;
	}
}



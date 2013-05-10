package com.melbourneit.utils.jms;

import org.apache.camel.ProducerTemplate;
import com.melbourneit.exception.JMSResquestException;
import com.melbourneit.model.jms.JMSReqRes;
import com.melbourneit.utils.XStreamUtil;

public class JMSRequestProcessor {
	
	public Object process(ProducerTemplate producer, String endpoint, JMSReqRes request) throws JMSResquestException{

		try{
			Object response = producer.requestBody(endpoint, XStreamUtil.toXML(request));
			
			if(response != null){
				if(response instanceof String && !((String) response).trim().isEmpty()){
					request = XStreamUtil.fromXML(response.toString());		
					if(request.getResponse().getResponseStatus() == JMSResponseStatus.PASSED)
						 return request.getResponse().getResponseObject();
				}
			}
			throw new JMSResquestException("JMS Failed to response. Please check configurations.");
		}catch (Exception e) {
			throw new JMSResquestException("Error while processing JMS request. - ",e);
		} 
	}
}

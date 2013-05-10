package com.melbourneit.utils.jms;

import org.apache.camel.ProducerTemplate;
import com.melbourneit.exception.JMSResquestException;
import com.melbourneit.model.jms.JmsRequest;
import com.melbourneit.model.jms.JmsResponse;
import com.melbourneit.utils.XStreamUtil;

public class JMSRequestProcessor<T> {
	
	public T process(ProducerTemplate producer, String endpoint, JmsRequest request) throws JMSResquestException{

		try{
			Object response = producer.requestBody(endpoint, XStreamUtil.toXML(request, request.getClass()));
			
			if(response != null){
				if(response instanceof String && !((String) response).trim().isEmpty()){
					@SuppressWarnings("unchecked")
					JmsResponse<T> jmsResponse = XStreamUtil.fromXML(response.toString(), JmsResponse.class);		
					if(request.getResponseStatus() == JMSResponseStatus.PASSED)
						 return jmsResponse.getResponseObject();
				}
			}
			throw new JMSResquestException("JMS Failed to response. Please check your configurations.");
		}catch (Exception e) {
			throw new JMSResquestException("Error while processing JMS request. - "+e);
		} 
	}
}

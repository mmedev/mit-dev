package com.melbourneit.services.jms;

import com.melbourneit.model.jms.JmsResponse;
import com.melbourneit.utils.XStreamUtil;

public abstract class JMSRequestService{
	
	public <T> T getJMSRequest(String xml){
		
		XStreamUtil.fromXML(xml, JmsResponse.class);
		
		
	}
	
}

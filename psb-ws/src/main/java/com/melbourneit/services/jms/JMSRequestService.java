package com.melbourneit.services.jms;

import com.melbourneit.model.jms.JMSReqRes;
import com.melbourneit.utils.XStreamUtil;

public abstract class JMSRequestService{
	public Object getJMSRequest(String request){	
		JMSReqRes reqRes = XStreamUtil.fromXML(request, JMSReqRes.class);
		return reqRes.getRequest();	
	}
}

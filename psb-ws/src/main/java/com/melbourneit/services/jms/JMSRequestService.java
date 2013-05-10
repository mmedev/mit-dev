package com.melbourneit.services.jms;

import com.melbourneit.logger.BaseLogger;
import com.melbourneit.model.jms.JMSReqRes;
import com.melbourneit.model.jms.JmsResponse;
import com.melbourneit.utils.XStreamUtil;
import com.melbourneit.utils.jms.JMSResponseMessage;
import com.melbourneit.utils.jms.JMSResponseStatus;

public abstract class JMSRequestService extends BaseLogger{
	public Object getJMSRequest(String request){	
		JMSReqRes reqRes = XStreamUtil.fromXML(request);
		return reqRes.getRequest();	
	}
	
	public String generateResponse(Object result, JMSResponseStatus status, String message){
		return XStreamUtil.toXML(new JMSReqRes(null, new JmsResponse(status, message, result)));
	}
	
	public String generateResponseOK(Object result){
		return generateResponse(result, JMSResponseStatus.PASSED, JMSResponseMessage.OK.toString());
	}
	
	public String generateResponseError(String message){
		return generateResponse(null, JMSResponseStatus.FAILED, message);
	}
}

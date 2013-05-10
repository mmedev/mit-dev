package com.melbourneit.model.jms;

import java.io.Serializable;

import com.melbourneit.utils.jms.JMSResponseStatus;

public class JmsResponse implements Serializable {

	private static final long serialVersionUID = 1234567890L;
	
	private JMSResponseStatus responseStatus;
	private String responseMessage;
	private Object responseObject;
	
	public JmsResponse() {
	}
	
	public JmsResponse(JMSResponseStatus responseStatus, String responseMessage, Object responseObject) {
		this.responseStatus=responseStatus;
		this.responseMessage=responseMessage;
		this.responseObject=responseObject;
	}
		
	public Object getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public JMSResponseStatus getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(JMSResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
}

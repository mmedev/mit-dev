package com.melbourneit.model.jms;

import java.io.Serializable;

import com.melbourneit.utils.jms.JMSResponseStatus;

public abstract class JmsResponse<T> implements Serializable {

	private static final long serialVersionUID = 1234567890L;
	
	private JMSResponseStatus responseStatus;
	private String responseMessage;
	private T responseObject;
	
	public T getResponseObject() {
		return responseObject;
	}
	public void setResponseObject(T responseObject) {
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

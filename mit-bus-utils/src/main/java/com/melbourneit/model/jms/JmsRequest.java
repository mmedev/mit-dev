package com.melbourneit.model.jms;

import java.io.Serializable;

import com.melbourneit.utils.jms.JMSResponseStatus;

public abstract class JmsRequest implements Serializable {

	private static final long serialVersionUID = 1234567890L;
	
	private JMSResponseStatus responseStatus;
	private String responseMessage;

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

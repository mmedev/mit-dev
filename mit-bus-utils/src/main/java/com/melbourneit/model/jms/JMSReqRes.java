package com.melbourneit.model.jms;

import java.io.Serializable;

public class JMSReqRes implements Serializable {

	private static final long serialVersionUID = 1234567890L;
	
	private JmsRequest request;
	private JmsResponse response;
	
	public JMSReqRes() {
	}
	
	public JMSReqRes(JmsRequest request, JmsResponse response) {
		this.request=request;
		this.response=response;
	}
	
	public JmsRequest getRequest() {
		return request;
	}
	public void setRequest(JmsRequest request) {
		this.request = request;
	}
	public JmsResponse getResponse() {
		return response;
	}
	public void setResponse(JmsResponse response) {
		this.response = response;
	}
}

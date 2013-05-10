package com.melbourneit.utils.jms;

public enum JMSEndpoint {
	DOMAIN_CHECK("direct:domainCheck");
	
	private String endpoint;
	
	private JMSEndpoint(String endpoint) {
		this.endpoint=endpoint;
	}
	
	public String toString(){
		return this.endpoint;
	}
}

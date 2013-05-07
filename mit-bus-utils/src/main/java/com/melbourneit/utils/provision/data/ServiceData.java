package com.melbourneit.utils.provision.data;

import java.util.Date;

public class ServiceData {
	private String integrateionId;
	private Date creationDate;
	private Date expiryDate;
	private String serviceDescription;
	private String serviceCategory;
	private String serviceStatus;
	private String domainName;
	private String serviceData;
	
	public String getIntegrateionId() {
		return integrateionId;
	}
	public void setIntegrateionId(String integrateionId) {
		this.integrateionId = integrateionId;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getServiceDescription() {
		return serviceDescription;
	}
	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getServiceData() {
		return serviceData;
	}
	public void setServiceData(String serviceData) {
		this.serviceData = serviceData;
	}
}

package com.melbourneit.utils.provision.data;

import java.util.List;

public class ServiceListResult {
	private PageData pageData;
	private List<ServiceData> services;
	
	public PageData getPageData() {
		return pageData;
	}
	public void setPageData(PageData pageData) {
		this.pageData = pageData;
	}
	public List<ServiceData> getServices() {
		return services;
	}
	public void setServices(List<ServiceData> services) {
		this.services = services;
	}
}

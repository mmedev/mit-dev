package com.melbourneit.utils.provision.data;

import java.util.HashMap;

import com.melbourneit.utils.provision.ProvisionConstants;

public class ItemData {
	private int itemId = -1;
	private String productCode;
	private String operation;
	private String productName;
	private int period = ProvisionConstants.Request.PRODUCT_PERIOD_DEFAULT;
	private String periodUnit = ProvisionConstants.Request.PRODUCT_PERIOD_UNIT_DEFAULT;
	private int quantity = 1;
	private String integrationId;
	private String parentIntegrationId;
	private String itemSource;
	private String itemSourceId;
	private String itemStatus;
	private String domainName;
	private String provisionData;
	private HashMap extensionData;
	private CustomerData customer;

	public ItemData() {
		super();
	}

	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public String getPeriodUnit() {
		return periodUnit;
	}
	public void setPeriodUnit(String periodUnit) {
		this.periodUnit = periodUnit;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getIntegrationId() {
		return integrationId;
	}
	public void setIntegrationId(String integrationId) {
		this.integrationId = integrationId;
	}
	public String getParentIntegrationId() {
		return parentIntegrationId;
	}
	public void setParentIntegrationId(String parentIntegrationId) {
		this.parentIntegrationId = parentIntegrationId;
	}
	public String getItemSource() {
		return itemSource;
	}
	public void setItemSource(String itemSource) {
		this.itemSource = itemSource;
	}
	public String getItemSourceId() {
		return itemSourceId;
	}
	public void setItemSourceId(String itemSourceId) {
		this.itemSourceId = itemSourceId;
	}
	public String getItemStatus() {
		return itemStatus;
	}
	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getProvisionData() {
		return provisionData;
	}
	public void setProvisionData(String provisionData) {
		this.provisionData = provisionData;
	}
	public HashMap getExtensionData() {
		return extensionData;
	}
	public void setExtensionData(HashMap extensionData) {
		this.extensionData = extensionData;
	}
	public CustomerData getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerData customer) {
		this.customer = customer;
	}

	public boolean isValidRequest() {
		if (productCode == null || productCode.isEmpty() 
				|| operation == null || operation.isEmpty()
				|| itemSourceId == null || itemSourceId.isEmpty()) {
			return false;
		}

		return true;
	}
}

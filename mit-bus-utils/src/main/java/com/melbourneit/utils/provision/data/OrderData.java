package com.melbourneit.utils.provision.data;

import java.util.Iterator;
import java.util.List;

public class OrderData {

	private int orderId = -1;
	// Reseller account user name
	private String accountUserName;
	private String orderSource;
	private String orderSourceId;
	private String clientOrderTrackingId;
	private String sourceIPAddress;
	private String orderStatus;
	private String systemLoggingId;
	private List<ItemData> items;

	private String invalidRequestFields = "";

	public OrderData() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getAccountUserName() {
		return accountUserName;
	}
	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getOrderSourceId() {
		return orderSourceId;
	}
	public void setOrderSourceId(String orderSourceId) {
		this.orderSourceId = orderSourceId;
	}
	public String getClientOrderTrackingId() {
		return clientOrderTrackingId;
	}
	public void setClientOrderTrackingId(String clientOrderTrackingId) {
		this.clientOrderTrackingId = clientOrderTrackingId;
	}
	public String getSourceIPAddress() {
		return sourceIPAddress;
	}
	public void setSourceIPAddress(String sourceIPAddress) {
		this.sourceIPAddress = sourceIPAddress;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public List<ItemData> getItems() {
		return items;
	}
	public void setItems(List<ItemData> items) {
		this.items = items;
	}
	public String getSystemLoggingId() {
		return systemLoggingId;
	}

	public void setSystemLoggingId(String systemLoggingId) {
		this.systemLoggingId = systemLoggingId;
	}

	public boolean isValidRequest() {
		if (orderSourceId == null || orderSourceId.isEmpty()) {
			return false;
		}
	
		Iterator iter = items.iterator();
		if (iter.hasNext()) {
			ItemData itemData = (ItemData)iter.next();
			if (!itemData.isValidRequest()) {
				return false;
			}
		}
		return true;
	}
	
	public boolean isValidResponse() {
		return true;
	}
	
	public String getInvalidRequestFields() {
		return invalidRequestFields;
	}

	public void setInvalidRequestFields(String invalidRequestFields) {
		this.invalidRequestFields = invalidRequestFields;
	}
}

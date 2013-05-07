package com.melbourneit.utils.provision.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProvisionResult {
	private String result;
	private int resultCode;
	private String message;
	private OrderData orderResult;

	private static final Logger LOG = LoggerFactory.getLogger(ProvisionResult.class);

	public ProvisionResult() {
		super();
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getResultCode() {
		return resultCode;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public OrderData getOrderResult() {
		return orderResult;
	}

	public void setOrderResult(OrderData orderResult) {
		this.orderResult = orderResult;
	}

	public String toString() {
		StringBuffer buff = new StringBuffer(2048);

		buff.append("ResultStr[").append(result);
		buff.append("] ResultCode[").append(resultCode);
		buff.append("] Message[").append(message);
		buff.append("] orderResult[").append(orderResult).append("]");
		return buff.toString();
	}
}

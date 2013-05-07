package com.melbourneit.utils.provision;

public class ProvisionConstants {
	public static final class Keys {
		public static final String KEY_PROVISION_REQUEST = "PROVISION_REQ";
		public static final String KEY_PROVISION_RESPONSE = "PROVISION_RESP";
		public static final String KEY_GET_SERVICE_LIST_RESPONSE = "GET_SERVICE_LIST_RESP";
		public static final String KEY_GET_ORDER_STATUS_RESPONSE = "GET_ORDER_STATUS_RESPONSE";
		public static final String KEY_GET_ITEM_STATUS_RESPONSE = "GET_ITEM_STATUS_RESPONSE";
		
		
		public static final String KEY_PROVISION_ORDER_ID = "provisionOrderID";
		public static final String KEY_CLIENT_ORDER_TRACKING_ID = "clientOrderTrackingID";
		public static final String KEY_MAX_RESULT = "MAX_RESULT";
		public static final String KEY_START_RECORD = "START_RECORD";
		public static final String KEY_ACCOUNT_NAME = "ACCOUNT_NAME";
		public static final String KEY_DOMAIN_NAME = "DOMAIN_NAME";
		public static final String KEY_SERVICE_CATEGORY = "SERVICE_CATEGORY";
		public static final String KEY_SERVICE_STATUS = "SERVICE_STATUS";
		public static final String KEY_ORDER_ID = "ORDER_ID";
		public static final String KEY_ITEM_ID = "ITEM_ID";
	    /**
	     * The user defined log id
	     */
	    public static final String KEY_LOG_ID = "user_defined_log_id";

	}

	public static final class Request {
		public static final String KEY_PRODUCT_PERIOD = "period";
		public static final String KEY_PRODUCT_PERIOD_UNIT = "periodUnit";

		public static final int PRODUCT_PERIOD_DEFAULT = 0;
		public static final String PRODUCT_PERIOD_UNIT_DEFAULT = "Year";
		public static final String DEFAULT_IP_ADDRESS = "UNKNOWN";
	}

	public static final class Result {
		public static final String PROVISION_SUCCESS_RESULT_STR = "Success";
		public static final String PROVISION_FAILURE_RESULT_STR = "Failure";
	}

	public static final class ResultCode {
		public static final int PROVISION_SUCCESS_CODE = 1000;
		public static final int PROVISION_INVALID_FIELDS_FAILURE_CODE = 2000;
		public static final int PROVISION_AUTHENTICATION_FAILURE_CODE = 2002;
		public static final int PROVISION_FAILURE_CODE = 3000;
		public static final int PROVISION_SYSTEM_ERROR_CODE = 9000;

		public static final int PROVISION_CONNECT_EXCEPTION = 3;
		public static final int PROVISION_CONNECT_IO_EXCEPTION = 4;
		public static final int PROVISION_GENERIC_EXCEPTION = 9;
	}

	public static final class Message {
		public static final String PROVISION_SUCCESS_MSG = "Request processed";
		public static final String PROVISION_INVALID_FIELDS_FAILURE_MSG = "Invalid provision request data";
		public static final String PROVISION_AUTHENTICATION_FAILURE_MSG = "Authentication failed";
		public static final String PROVISION_FAILURE_MSG = "Provision failed";
		public static final String PROVISION_SYSTEM_ERROR_MSG = "System error";        	
	}
}

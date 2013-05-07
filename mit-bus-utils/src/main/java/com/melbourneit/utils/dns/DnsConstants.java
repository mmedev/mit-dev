package com.melbourneit.utils.dns;

public class DnsConstants
{
    public static final class Keys 
    {
        public static String RESULT_CODE = "resultCode";
        public static String ERROR_DETAILS = "errorDetails";
        public static String DNS_RECORDS_COUNT = "dnsRecordsCount";
        public static String DNS_RECORD = "dnsRecord";
        public static String DNS_NAME = "name";
        public static String DNS_TYPE = "type";
        public static String DNS_TTL = "ttl";
        public static String DNS_PRIORITY = "priority";
        public static String DNS_ADDRESS = "address";
        public static String DNS_UPDATE_OPERATION_TYPE = "updateOperationType";
        
        public static final String KEY_ACCOUNT_NAME = "ACCOUNT_NAME";
        public static final String KEY_DOMAIN_NAME = "DOMAIN_NAME";
        public static final String KEY_DNS_RRSET_STATUS = "dns_rrset_status";
        /**
         * The user defined log id
         */
        public static final String KEY_LOG_ID = "user_defined_log_id";
    }
    
    public static final class ResultCode 
    {
        public static String SUCCESS = "Success";
        public static String FAILURE = "Failure";
    }
    
    public static final class Operation
    {
        public static String ADD_RECORDS = "AddNewRecords";
        public static String DELETE_RECORDS = "DeleteRecords";
    }
 
    public static final class Status
    {
        public static String COMPLETED = "COMPLETED";
        public static String IN_PROGRESS = "IN_PROGRESS";
        public static String FAILED = "FAILED";
    }
}

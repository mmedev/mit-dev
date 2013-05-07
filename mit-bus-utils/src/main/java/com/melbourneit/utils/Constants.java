package com.melbourneit.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Constants
{
    public static final String DATE_FORMAT_ISO8601_LONG_RFC822_MS_ZONE = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String DATE_FORMAT_ISO8601_LONG_RFC822_ZONE = "yyyy-MM-dd'T'HH:mm:ssZ";
    public static final String DATE_FORMAT_ISO8601_SHORT_ZONE = "yyyyMMddHH:mm:ssZ";
    public static final String DEFAULT_DATE_FORMAT = DATE_FORMAT_ISO8601_LONG_RFC822_MS_ZONE;
    
    public static SimpleDateFormat getNewDefaultDateFormat()
    {
        return new SimpleDateFormat(DEFAULT_DATE_FORMAT); 
    }
    
    public static String getDateTimeAsString()
    {
        return getDateTimeAsString(DEFAULT_DATE_FORMAT,new Date());
    }
    
    public static String getDateTimeAsString(Date dateTime)
    {
        return getDateTimeAsString(DEFAULT_DATE_FORMAT,dateTime);
    }
    
    public static String getDateTimeAsString(String formatStr, Date dateTime)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatStr);
        return dateFormat.format(dateTime);    
    }
    
    public static class Request
    {
        public static class Keys
        {
            public static final String FUNCTION_NAME = "request.keys.functionName";
            public static final String QUERY_VALUE_ID = "request.keys.queryId";
            public static final String INPUT_STRING = "request.keys.inputString";
            public static final String SEARCH_BY_ID_FIELD_NAME = "request.keys.searchbyidFeldName";
            //this key can not keep the same format due to the need for backwards compatibility 
            public static final String USER_LOG_ID = "user_defined_log_id";
        }
        
        public static class Functions
        {
            public static final String SEARCH_BY_ID = "request.functions.searchbyid";
            public static final String SEARCH_LIST_BY_ID = "request.functions.searchlistbyid";
            public static final String UPDATE = "request.functions.update";
            public static final String DELETE = "request.functions.delete";
            public static final String CREATE = "request.functions.create";
        }
    }
    
    public static class Response
    {
        public static class Values
        {
            public static final String SUCCESS = "response.result.success";
            public static final String OBJECT_NOT_FOUND = "response.result.object_not_found";
            public static final String ERROR = "response.result.error";
        }
        
        public static class Keys
        {
            public static final String ERROR_STRING = "response.keys.error_string";
            public static final String RESULT_CODE = "response.keys.result_code";
            public static final String OUTPUT_STRING = "response.keys.output_string";
        }
    }
}

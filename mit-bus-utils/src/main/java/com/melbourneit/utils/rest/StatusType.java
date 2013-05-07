package com.melbourneit.utils.rest;

public enum StatusType
{
    SUCCESS(200), 
    SUCCESS_CREATED(201), 
    SUCCESS_NO_RESPONSE(204),
    REDIRECT_PERMANENT(301), 
    REDIRECT_TEMPORARY(302),
    ERROR_REQUEST_SYNTAX(400), 
    ERROR_REQUEST_REQUIRES_AUTHENTICATION(401),
    ERROR_REQUEST_ACCESS_DENIED(403),
    ERROR_REQUEST_RESOURCE_NOT_FOUND(404),
    ERROR_METHOD_RESOURCE_ACCESS_NOT_ALLOWED(405),
    ERROR_REQUEST_MEDIA_TYPE_NOT_SUPPORTED(406),
    ERROR_FAILED_TO_CREATE_RESOURCE_EXISTS_ALREADY(409),
    ERROR_USUPPORTED_CONTENT_BODY(415),
    ERROR_SERVER_INTERNAL_ERROR(500),
    ERROR_SERVER_FUNCTIONALITY_NOT_SUPPORTED(501),
    ERROR_SERVER_TEMPORARY_UNABLE_TO_HANDLE_REQUEST(503),
    ERROR_SERVER_GATEWAY_TIMEOUT(504);
    
    private int value;
    private StatusType(int value)
    {
        this.value=value;
    }

    public int getIntValue()
    {
        return value;
    }
    
    public String getStringValue()
    {
        return Integer.toString(value);
    }
}

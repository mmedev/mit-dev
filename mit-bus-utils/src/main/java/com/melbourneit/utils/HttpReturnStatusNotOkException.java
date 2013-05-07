package com.melbourneit.utils;

public class HttpReturnStatusNotOkException extends Exception
{
    private int statusCode;
    public HttpReturnStatusNotOkException(int returnedCode)
    {
        super("Http Client Returned code is not OK. ReturnedCode: " + returnedCode);
        statusCode = returnedCode;
    }
    
    public int getStatusCode()
    {
        return statusCode;
    }
}

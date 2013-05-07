package com.melbourneit.utils.rest;

import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.codehaus.jra.HttpHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<Throwable>
{
    protected static final Logger Log = LoggerFactory.getLogger(CatchAllExceptionMapper.class);
    @Context
    HttpHeaders httpHeaders;
    @Override
    @Produces({MediaType.APPLICATION_JSON ,MediaType.APPLICATION_XML,MediaType.TEXT_PLAIN})
    public Response toResponse(Throwable ex)
    {
        Log.info("Inside catch all exception. Caught exception: " + ex.getClass() +
                " Internal exception is: " + ex.getCause() + " Message: " + ex.getMessage() + " Cause exception: " + 
                ((ex.getCause() == null) ? "No cause exception" : ex.getCause().getMessage()));
        
        String errorString = ex.getMessage();
        StatusType status = StatusType.ERROR_SERVER_INTERNAL_ERROR;
        boolean returnApiResponse = true;
        if (ex.getCause() != null) 
        {
            if (ex.getCause() instanceof  javax.xml.stream.XMLStreamException)
            {
                errorString = "Improper formatted input. Please abide by the API specification, rectify the error and then try again";
                status = StatusType.ERROR_REQUEST_SYNTAX;
            }
        }
        else if (ex instanceof java.lang.IllegalArgumentException)
        {
            status = StatusType.ERROR_REQUEST_SYNTAX;
            errorString = ex.getMessage();
        }
        else if (ex instanceof javax.ws.rs.WebApplicationException)
        {
            Response webExceptionResponse = ((javax.ws.rs.WebApplicationException)ex).getResponse();
            if (webExceptionResponse != null) 
            {
                Log.info("Caught WebApplicationException, Response: " + ToStringBuilder.reflectionToString(webExceptionResponse, JAXBToStringStyle.DEFAULT_STYLE));
                if (webExceptionResponse.getStatus() == StatusType.ERROR_REQUEST_MEDIA_TYPE_NOT_SUPPORTED.getIntValue())
                {
                    Log.info("Ooops client has asked for a media type that we do not support.");
                    return webExceptionResponse;
                }
                else if (webExceptionResponse.getStatus() == StatusType.ERROR_METHOD_RESOURCE_ACCESS_NOT_ALLOWED.getIntValue())
                {
                    Log.info("Ooops client has asked for a method without supplying parameters. Headers: " + httpHeaders.getAcceptableMediaTypes());

                    return webExceptionResponse;
                }
                else if (webExceptionResponse.getStatus() == StatusType.ERROR_USUPPORTED_CONTENT_BODY.getIntValue())
                {
                    Log.info("Ooops we were called without a supported content body");
                    return webExceptionResponse;
                }
                else
                {
                    //if we get this error we want to see the response object, to see what it was set to
                    Log.info("Caught web application error with following response: " + ToStringBuilder.reflectionToString(webExceptionResponse, JAXBToStringStyle.DEFAULT_STYLE));
                }
            }
        }
        else if (errorString == null)
        {
            errorString = "Unexpected exception input data. Exception cause: " + ex.getClass().getName();
        }

        
        ResponseBuilderImpl builder = ApiResponse.buildErrorResponseBuilder(errorString, status, returnApiResponse);
        
        return builder.build();
    }
}

package com.melbourneit.utils.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import org.apache.commons.lang.StringUtils;
import org.apache.cxf.interceptor.LoggingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.melbourneit.utils.Constants;
import com.melbourneit.utils.UniqueIdGenerator;
import com.melbourneit.utils.stats.FunctionStats;
import com.melbourneit.utils.stats.FunctionStatsContainer;


public abstract class AbstractRestProvider
{
    protected static final Logger Log = LoggerFactory.getLogger(AbstractRestProvider.class);
    protected UniqueIdGenerator logIdGenerator;

    @Context 
    private org.apache.cxf.jaxrs.ext.MessageContext mc;

    private String functionStatsContainerName;
    protected FunctionStatsContainer functionStatsContainer;
    protected FunctionStats[] functionStats;
    
    public static final String ERROR_RESOURCE_NOT_FOUND = "Searched for resource not found";
    public static final String ERROR_UNEXPECTED = "Unexpected error";
    public static final String ERROR_UNEXPECTED_WAITING_FOR_RESPONSE = "Unknown error waiting for a server response";
    public static final String ERROR_UNEXPECTED_FROM_UPSTREAM = "Unexpected error returned by the up stream processes";
    public static final String ERROR_TIMED_OUT_WAITING_FOR_RESPONSE = "Request timed out waiting for a response";

    /**
     * The purpose of this function is to verify whether or init functions have been completed
     * If it returns a non-empty value the init function will throw an IllegalArgument exception with the string
     * @return
     */
    public abstract String getInitErrorString();

    public void init()
    {
        String errorStr = "";
        Log.info(getClass().getName() + " initiailizing...");
        
        if (StringUtils.isEmpty(functionStatsContainerName))
        {
            errorStr += "Field functionStatsContainerName must be set; ";
        }
        
        if (logIdGenerator == null)
        {
            errorStr += "logIdGenerator must be set; ";
        }

        String allotherFieldsErrorStr = getInitErrorString();
        if (StringUtils.isNotEmpty(allotherFieldsErrorStr))
        {
            errorStr += allotherFieldsErrorStr;
        }
        
        if (StringUtils.isNotEmpty(errorStr))
        {
            throw new IllegalArgumentException(errorStr);
        }
        Log.info(getClass().getName() + " started.");
    }

    protected String generateUniqueId(String userId)
    {
        String logId = null;
        String clientIpAddress = null;
        String forwardedIp = null;

        HttpServletRequest req = mc.getHttpServletRequest();
        //retrieve the logger message id, needed to identify the raw xml received
        String messageId = (String)mc.get(LoggingMessage.ID_KEY);

        if (req != null)
        {
            clientIpAddress = req.getRemoteAddr();
            forwardedIp = req.getHeader("X-Forwarded-For");
            if (forwardedIp != null)
            {
                Log.info("ID" + messageId + " has been forwarded by: " + clientIpAddress + " on behalf of: " + forwardedIp);
                clientIpAddress = forwardedIp;
            }
        }
        
        if (userId != null)
        {    
            logId = logIdGenerator.generateId(userId + "-" + clientIpAddress); 
        }
        else
        {
            logId = logIdGenerator.generateId(clientIpAddress);
        }

        if (messageId != null)
        {
            logId = "ID" + messageId + "-" + logId;
        }
        return logId;
    }

    public UniqueIdGenerator getLogIdGenerator()
    {
        return logIdGenerator;
    }

    public void setLogIdGenerator(UniqueIdGenerator logIdGenerator)
    {
        this.logIdGenerator = logIdGenerator;
    }
    
    public String getFunctionStatsContainerName()
    {
        return functionStatsContainerName;
    }

    public void setFunctionStatsContainerName(String functionStatsContainerName)
    {
        this.functionStatsContainerName = functionStatsContainerName;
    }

    /**
     * It will verify if the "result" is succesfull, if not it will set an error on the apiResponse
     * @param result
     * @param error
     * @param apiResponse
     * @param logId
     * @return
     */
    protected boolean isSuccessfulResult(String result, String error, ApiResponse apiResponse, String logId)
    {
        boolean validResult = false;
        Log.info(logId + " Result: " + result + " Error: " + error);
        if (Constants.Response.Values.SUCCESS.equals(result))
        {
            validResult = true;
        }
        else
        {
            validResult = false;
            
            if (Constants.Response.Values.OBJECT_NOT_FOUND.endsWith(result))
            {
                apiResponse.setStatusType(StatusType.ERROR_REQUEST_RESOURCE_NOT_FOUND);
                apiResponse.setMessage(ERROR_RESOURCE_NOT_FOUND);
                apiResponse.setExtendedMessage(logId);
            }
            else
            {
                //well in this case we have had some other type of error
                apiResponse.setStatusType(StatusType.ERROR_SERVER_INTERNAL_ERROR);
                apiResponse.setMessage((error != null) ? error : ERROR_UNEXPECTED_FROM_UPSTREAM);
                apiResponse.setExtendedMessage(logId);
            }
        }
         
        return validResult;
    }
}


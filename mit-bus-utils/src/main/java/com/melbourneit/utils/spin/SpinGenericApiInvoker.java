package com.melbourneit.utils.spin;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melbourneit.utils.HttpInvoker;
import com.melbourneit.utils.HttpReturnStatusNotOkException;
import com.melbourneit.utils.UserDetails;
import com.melbourneit.utils.spin.SPINConstants;
import com.melbourneit.utils.spin.SPINConstants.GenericApi;
import com.melbourneit.utils.spin.SPINConstants.GenericApi.Errors;
import com.melbourneit.utils.spin.SPINConstants.GenericApi.FieldNames;

public class SpinGenericApiInvoker 
{
    private static final Logger LOG = LoggerFactory.getLogger(SpinGenericApiInvoker.class);


    private static long activeRequestsCount = 0;
    private static long handledRequestsCount = 0;


    public static String invokeApi(HttpClient httpClient, String url, String actionName, 
            UserDetails userDetails, String clientTrid, String busLogId, 
            Map<String, String> params)
    {
        params.put(SPINConstants.GenericApi.FieldNames.USERNAME,
                userDetails.getUsername());
        params.put(SPINConstants.GenericApi.FieldNames.PASSWORD,
                userDetails.getPassword());
        if (clientTrid != null)
        {
            params.put(SPINConstants.GenericApi.FieldNames.CLIENT_TRANS_ID,
                    clientTrid);
        }
        params.put(SPINConstants.GenericApi.FieldNames.ACTION_NAME,
                actionName);
        if (busLogId != null)
        {
            params.put(SPINConstants.GenericApi.FieldNames.ENTERPRISE_BUS_LOGID, busLogId);
        }

        return invokeApi(httpClient, url, params, busLogId,actionName);
    }

    public static String invokeApi(HttpClient httpClient, String url, Map<String, String> params, String logId, String actionName)
    {
        String result = null;
        boolean succesfulExecution = false;

        long activeCount = 0;

        synchronized (SpinGenericApiInvoker.class)
        {
            activeCount = ++activeRequestsCount;
        }

        try
        {
            //ensure that we set the API type to be generic
            params.put(SPINConstants.GenericApi.FieldNames.API_TYPE, SPINConstants.GenericApi.FieldValues.API_TYPE_GENERIC);
            
            String fullActionName = "SpinGenericApiInvoker." + actionName;
            LOG.info(logId + " SpinGenericApiInvoker activeRequests " + activeCount);
            result = HttpInvoker.invokePostApi(httpClient, url, params, logId, fullActionName);
        }
        catch (HttpReturnStatusNotOkException e)
        {
            // we need to set the result to some kind of an error
            result =
                SPINConstants.GenericApi.Errors.buildErrorString(actionName,
                        SPINConstants.GenericApi.Errors.CONNECT_EXCEPTION,
                        logId);
        }
        catch (HttpException e)
        {
            // TODO Auto-generated catch block
            LOG.error(logId + " HttpException: " + e.toString(), e);
            result =
                SPINConstants.GenericApi.Errors.buildErrorString(actionName,
                        SPINConstants.GenericApi.Errors.CONNECT_IO_EXCEPTION,
                        logId);
        }
        catch (IOException e)
        {
            LOG.error(logId + " IOException", e);
            result =
                SPINConstants.GenericApi.Errors.buildErrorString(actionName,
                        SPINConstants.GenericApi.Errors.CONNECT_IO_EXCEPTION,
                        logId);
        }
        catch (IllegalArgumentException e)
        {
            LOG.error(logId + " IllegalArgumentException ", e);
            result =
                SPINConstants.GenericApi.Errors
                .buildErrorString(actionName,
                        SPINConstants.GenericApi.Errors.GENERIC_EXCEPTION,
                        logId);
        }
        finally
        {
            synchronized (SpinGenericApiInvoker.class)
            {
                activeCount = --activeRequestsCount;
                handledRequestsCount++;
            }
        }

        LOG.info(logId + " SpinGenericApiInvoker activeRequests " + activeCount);
        return result;
    }


    public static long getActiveRequestsCount()
    {
        return activeRequestsCount;
    }

    public static long getHandledRequestsCount()
    {
        return handledRequestsCount;
    }

}

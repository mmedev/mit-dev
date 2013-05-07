package com.melbourneit.utils.spin;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melbourneit.utils.HttpInvoker;
import com.melbourneit.utils.HttpReturnStatusNotOkException;
import com.melbourneit.utils.spin.SPINConstants;

public class DomainsBotApiInvoker 
{
    private static final Logger LOG = LoggerFactory.getLogger(DomainsBotApiInvoker.class);

    private static long activeRequestsCount = 0;
    private static long handledRequestsCount = 0;

    public static String invokeApi(HttpClient httpClient, String url, Map<String, String> params, String logId, String actionName, int rowsLimit)
    {
        long endTime, startTime = System.currentTimeMillis();
        String result = null;
        boolean succesfulExecution = false;

        long activeCount = 0;

        synchronized (DomainsBotApiInvoker.class)
        {
            activeCount = ++activeRequestsCount;
        }

        try
        {
            //remove all the parameters that are not needed for domains bot from the map
            params.remove(SPINConstants.GenericApi.FieldNames.ACTION_NAME);
            params.remove(SPINConstants.GenericApi.FieldNames.ENTERPRISE_BUS_LOGID);
            params.remove(SPINConstants.GenericApi.FieldNames.USERNAME);
            params.remove(SPINConstants.GenericApi.FieldNames.PASSWORD);

            //ensure that we set the format, method and row limits parameters
            params.put("format","csv");
            params.put("method", actionName);
            params.put("limit","" + rowsLimit);
            params.put("method", "SearchAvailableDomains");
            
            String fullActionName = "DomainsBotApiInvoker." + actionName;
            LOG.info(logId + " DomainsBotApiInvoker activeRequests " + activeCount);
            result = HttpInvoker.invokePostApi(httpClient, url, params, logId, fullActionName);
        }
        catch (HttpReturnStatusNotOkException e)
        {
            // we need to set the result to some kind of an error
            result =
                SPINConstants.GenericApi.Errors.buildErrorString(actionName,
                        SPINConstants.GenericApi.Errors.CONNECT_EXCEPTION,
                        logId);
            
            if (LOG.isDebugEnabled())
            {
                LOG.debug(logId + " Result: " + result);
            }
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
            endTime = System.currentTimeMillis();
            synchronized (DomainsBotApiInvoker.class)
            {
                activeCount = --activeRequestsCount;
                handledRequestsCount++;
            }

        }

        LOG.info(logId + " DomainsBotApiInvoker activeRequests " + activeCount);
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

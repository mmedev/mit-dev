package com.melbourneit.utils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpClientError;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class HttpInvoker 
{
    private static final Logger LOG = LoggerFactory.getLogger(HttpInvoker.class);

    private static long activeRequestsCount = 0;
    private static long handledRequestsCount = 0;

    public static String invokePostApi(HttpClient httpClient, String url, Map<String, String> params, String logId, String actionName) 
        throws HttpException, IOException, HttpReturnStatusNotOkException
    {
        long endTime, startTime = System.currentTimeMillis();
        String result = null;
        boolean succesfulExecution = false;

        long activeCount = 0;

        synchronized (HttpInvoker.class)
        {
            activeCount = ++activeRequestsCount;
        }

        try
        {
            LOG.info(logId + " URL[" + url + "]..............");
            PostMethod postMethod = new PostMethod(url);
            try
            {
                if (LOG.isDebugEnabled())
                {
                    LOG.debug(logId + " About to execute: " + actionName
                            + " ActiveHttpRequests: " + activeCount + " params: " + params);
                }
                else
                {
                    LOG.info(logId + " About to execute: " + actionName
                            + " ActiveHttpRequests: " + activeCount);
                }

                // attempt to set the post parameters
                setPostParams(postMethod, params, logId);

                int statusCode = httpClient.executeMethod(postMethod);
                succesfulExecution = (statusCode == HttpStatus.SC_OK);

                String responseString = postMethod.getResponseBodyAsString();
                if (succesfulExecution)
                {
                    result = responseString;
                }
                else
                {
                    LOG.info(logId + " HttpError: " + statusCode
                            + " defaulting to system error. ResponseString: " + responseString);
                    throw new HttpReturnStatusNotOkException(statusCode);
                }
            }
            finally
            {
                // the connection MUST always be released no matter what the
                // result;
                postMethod.releaseConnection();
            }
        }
        finally
        {
            endTime = System.currentTimeMillis();
            synchronized (HttpInvoker.class)
            {
                activeCount = --activeRequestsCount;
                handledRequestsCount++;
            }

            LOG.info(logId + " HttpInvoker.invokeApi=>" + actionName
                    + " took: " + ((endTime - startTime) / 1000F)
                    + " seconds. SuccessfulExec: " + succesfulExecution
                    + " ActiveHttpRequests: " + activeCount + " TotalRequestsHandled: "
                    + handledRequestsCount);

            if (LOG.isDebugEnabled())
            {
                LOG.debug(logId + " Result: " + result);
            }
        }

        return result;
    }

    protected static void setPostParams(PostMethod postMethod, Map<String,String> params, String logId)
    {
        StringBuffer nullKeysBuff= new StringBuffer();
        boolean debug = LOG.isDebugEnabled();
        // time to now set the action specific parameters
        Set<String> keysSet = params.keySet();
        for (Iterator<String> iter = keysSet.iterator(); iter.hasNext();)
        {
            String key = iter.next();
            String value = params.get(key);
            if ((key != null) && (value != null))
            {
                postMethod.addParameter(key, value);
            }
            else if (debug)
            {
                nullKeysBuff.append("[").append(key).append("]");
            }
        }
        
        if (nullKeysBuff.length() >0)
        {
            LOG.debug(logId
                    + " Not setting key/value pair due to null values. Keys: " + nullKeysBuff.toString());
        }
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

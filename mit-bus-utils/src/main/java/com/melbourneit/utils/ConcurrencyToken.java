package com.melbourneit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The purpose of this class is to manage parallel requests.
 * It must be instantiated with the maximum number of tokens allowed to be dispersed.
 * The contract of the class is that if the getToken function returns true, the caller
 * must call the releaseToken() function when it finished work.
 * @author teo
 *
 */
public class ConcurrencyToken
{
    private static final Logger Log = LoggerFactory.getLogger(ConcurrencyToken.class);
    
    private int _maxTokens = 0;
    private int _requestsWaitCount = 0;
    private int _activeRequestCount = 0;

    public ConcurrencyToken(int maxTokens)
    {
        _maxTokens = maxTokens;
    }

    /**
     * The function will attempt to get a token in order to proceed
     * It will attempt to wait uindefinitely until it manages to get a token. If it is succesfull
     * it will return true, otherwise it will return false.
     * @param waitTimeMs - the maximum period of time to wait until getting an execution token
     * @return - true if token can be retrieved, else it will return false
     */
    public boolean getToken(String logId)
    {
        //a negative value means: wait indefinitely
        return getToken(-1, logId);
    }

    /**
     * The function will attempt to get a token in order to proceed
     * It will attempt to wait until currentTime + waitTimeMs. If it is succesfull
     * it will return true, otherwise it will return false.
     * @param waitTimeMs - the maximum period of time to wait until getting an execution token
     * @return - true if token can be retrieved, else it will return false
     */
    public boolean getToken(long waitTimeMs, String logId)
    {
        boolean tokenIsAvailable = false;
        boolean debugEnabled = (Log.isDebugEnabled());
        long waitUntil;

        if (waitTimeMs >= 0)
        {
            waitUntil = System.currentTimeMillis() + waitTimeMs;
        }
        else
        {
            waitUntil = Long.MAX_VALUE;
        }

        int activeRequests = 0;

        synchronized (this)
        {
            while (tokenIsAvailable == false)
            {
                //time to check how many parallel requests are available
                if (_activeRequestCount < _maxTokens)
                {
                    //ensure that we set the flag for execution allowed
                    tokenIsAvailable = true;

                    //ensure that we increment the number of active requests
                    _activeRequestCount++;
                    activeRequests = _activeRequestCount;
                }
                else
                {
                    activeRequests = _activeRequestCount;

                    //calculate the maximum time to wait for
                    long waitPeriod = waitUntil - System.currentTimeMillis();

                    //if the wait period is too small then exit the loop
                    if (waitPeriod <= 0)
                    {
                        break;
                    }

                    _requestsWaitCount++;

                    try
                    {
                        //go to sleep now to ensure we will be woken up when it
                        //is time to perform the request
                        if (debugEnabled)
                        {
                            Log.debug(logId + 
                                          " Going to wait sleep for: " + waitPeriod
                                          + " ms ActiveRequests: " + _activeRequestCount
                                          + " maxParallelRequests: " + _maxTokens
                                          + " waitingCount: " + _requestsWaitCount);
                        }

                        try
                        {
                            wait(waitPeriod);
                        }
                        catch (Exception e)
                        {
                            /*ignore*/
                        }
                    }
                    finally
                    {
                        _requestsWaitCount--;
                    }
                }
            }
        }

        Log.info(logId + " ActiveRequest: " + activeRequests + " current: " + _activeRequestCount);

        return tokenIsAvailable;
    }

    /**
     * This function will release a token back, so that others can get an available token
     *
     */
    public void releaseToken(String logId)
    {
        boolean released = false;

        synchronized (this)
        {
            //ensure that we decrement the number of active requests
            if (_activeRequestCount > 0)
            {
                _activeRequestCount--;
                released = true;
                notify();
            }
        }

        if (!released)
        {
            Log.info(logId + " Called for release token when no token was retrieved");
        }
    }
}

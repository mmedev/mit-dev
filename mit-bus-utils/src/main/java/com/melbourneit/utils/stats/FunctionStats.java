package com.melbourneit.utils.stats;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

public class FunctionStats
{
    private int activeRequestsCount = 0;
    private long totalRequestsCount = 0;
    private long lastExecTime = 0;
    private long lastExecDuration = 0;
    private long totalExecDuration = 0;
    private String functionName;
    

    protected FunctionStats(String functionName)
    {
        this.functionName = functionName;
    }

    public int updateActiveRequests(boolean up, long execDuration)
    {
        int activeRequests = 0;
        synchronized (this)
        {
            if (up)
            {
                ++activeRequestsCount;
                lastExecTime = System.currentTimeMillis();
            }
            else
            {
                //update total number of handled requests
                totalRequestsCount++;
                --activeRequestsCount;
                totalExecDuration += execDuration;
                lastExecDuration = execDuration;
            }
            activeRequests = activeRequestsCount;
        }

        return activeRequests;
    }

    public int getActiveRequestsCount()
    {
        return activeRequestsCount;
    }

    public long getTotalRequestsCount()
    {
        return totalRequestsCount;
    }

    public long getLastExecTime()
    {
        return lastExecTime;
    }

    public long getLastExecDuration()
    {
        return lastExecDuration;
    }

    public long getTotalExecDuration()
    {
        return totalExecDuration;
    }

    public String getFunctionName()
    {
        return functionName;
    }

    public String toString()
    {
        String lastExecString = "";

        if (totalRequestsCount > 0)
        {
            long lastExec = lastExecTime;
            lastExecString =
                " LastExecDuration: " + (lastExecDuration / 1000F) + " sec. AvgExecDuration: " + (totalExecDuration / 1000F / totalRequestsCount)
                		   + " sec. LastExec: " + ((System.currentTimeMillis() - lastExec) / 1000F)
                        + " sec ago at: " + new Date(lastExec);
        }

        return "Function: " + functionName + " ActiveRequests: "
                + activeRequestsCount + " TotalRequests: " + totalRequestsCount
                + lastExecString;
    }
}
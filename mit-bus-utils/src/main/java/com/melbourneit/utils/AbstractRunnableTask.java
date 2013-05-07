package com.melbourneit.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractRunnableTask implements Runnable
{
    private static final Logger Log = LoggerFactory.getLogger(AbstractRunnableTask.class);
    private String name;
    private boolean finished = false;
    private boolean started = false;
    protected Exception exception = null;
    protected String errorMessage;
    protected Object response = null;
    protected String threadLogId;
    
    protected AbstractRunnableTask(String name, String logId)
    {
        this.name = name;
        this.threadLogId = logId + " RunnableTask: " + name;
    }
    
    protected abstract void executeTask() throws Exception;
    
    public void run()
    {
        started = true;
        long endTime, startTime = System.currentTimeMillis();
        try
        {
            executeTask();
        }
        catch (Exception e)
        {
            Log.info(threadLogId + " Error: " + e);
            exception = e;
        }
        finally 
        {
            synchronized (this)
            {
                finished = true;
                notify();
            }

            endTime = System.currentTimeMillis();
            Log.info(threadLogId + " took: " + ((endTime-startTime) / 1000.0F) + " secs to execute.");
        }
    }
    
    public boolean isStarted()
    {
        return started;
    }
    
    public boolean isFinished()
    {
        return finished;
    }
    
    public Object getResponse()
    {
        return response;
    }
    
    public Exception getException()
    {
        return exception;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}

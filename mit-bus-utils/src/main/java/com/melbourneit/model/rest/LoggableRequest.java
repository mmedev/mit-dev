package com.melbourneit.model.rest;

import org.apache.commons.lang.StringUtils;

/**
 * Any classes that extend this class are loggable
 * 
 * @author txiao
 */
public class LoggableRequest
{
    private String logId;

    public LoggableRequest()
    {

    }

    public LoggableRequest(String logId)
    {
        this.logId = logId;
    }

    public String getLogId()
    {
        return logId;
    }

    public void setLogId(String logId)
    {
        this.logId = logId;
    }

    public void appendLogId(String logId, String separator)
    {
        if (StringUtils.isEmpty(this.logId))
        {
            this.logId = logId;
        }
        else
        {
            this.logId += (separator + logId);
        }
    }
}

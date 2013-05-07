package com.melbourneit.utils;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UniqueIdGenerator
{
    private static final Logger LOG = LoggerFactory
            .getLogger(UniqueIdGenerator.class);
    private static String fullLocalHostName;
    private static long uniqueIdCount = 0;
	private String prefix;

    private int maxLocalHostChars = -1;
    private String hostNameToUse = "";

    static
    {
        try
        {
            fullLocalHostName = InetAddress.getLocalHost().getHostName();
        }
        catch (Exception e)
        {
            LOG.error("Unable to retrieve the host name on the psb", e);
            fullLocalHostName = "PSBErrorHostName";
        }
    }

    public String generateId()
    {
        return generateId(null);
    }
    
    public String generateId(String includeStr)
    {
        long uniqueId = 0;
        synchronized (UniqueIdGenerator.class)
        {
            uniqueId = ++uniqueIdCount;
        }

        // the only way to esure we have a unique id is to also have a local
        // call increment and the hostname in the id
        if (includeStr == null)
        {
            return prefix + "-" + System.currentTimeMillis() + "-" + uniqueId + "-" + hostNameToUse;
        }
        else
        {
            return prefix + "-" + includeStr + "-" + System.currentTimeMillis() + "-" + uniqueId + "-" + hostNameToUse;
        }
    }

    public String generateMinimumUniqueId()
    {
        long uniqueId = 0;
        synchronized (UniqueIdGenerator.class)
        {
            uniqueId = ++uniqueIdCount;
        }
        
        return prefix + "-" + System.currentTimeMillis() + "-" + uniqueId;
    }
    
    public String appendToUniqueId(String currentUniqueId)
    {
        if (currentUniqueId == null)
        {
            return generateId(null);
        }

        long uniqueId = 0;
        synchronized (UniqueIdGenerator.class)
        {
            uniqueId = ++uniqueIdCount;
        }

        //well we just append to an already uniqueId
        return currentUniqueId + "-" + prefix + "-" + uniqueId + "-" + hostNameToUse;
    }
    
    public void init()
    {
        if (maxLocalHostChars == -1)
        {
            hostNameToUse = fullLocalHostName;
        }
        else if (maxLocalHostChars >= 0)
        {
            hostNameToUse =
                fullLocalHostName.substring(0, (maxLocalHostChars > fullLocalHostName
                        .length()) ? fullLocalHostName.length() : maxLocalHostChars);
        }
    }

    public int getMaxLocalHostChars()
    {
        return maxLocalHostChars;
    }

    public void setMaxLocalHostChars(int maxLocalHostChars)
    {
        this.maxLocalHostChars = maxLocalHostChars;
    }
    
    public String getPrefix()
    {
        return prefix;
    }

    public void setPrefix(String prefix)
    {
        this.prefix = prefix;
    }

}

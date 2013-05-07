package com.melbourneit.model.rest;


import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author qinhaoding
 */
@XmlRootElement(name = "multiDomainCheckRequest")
public class MultiDomainCheckRequest extends LoggableRequest
{
    private String username;
    private String password;
    private String clientRefId;
    private List<String> domains;
    private String maxExecWaitSeconds;

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the clientRefId
     */
    public String getClientRefId()
    {
        return clientRefId;
    }

    /**
     * @param clientRefId
     *            the clientRefId to set
     */
    public void setClientRefId(String clientRefId)
    {
        this.clientRefId = clientRefId;
    }

    @XmlElement(name = "domain")
    @XmlElementWrapper(name = "domains")
    public List<String> getDomains()
    {
        return domains;
    }

    public void setDomains(List<String> domains)
    {
        this.domains = domains;
    }

    /**
     * @return the maxExecWaitSeconds
     */
    public String getMaxExecWaitSeconds()
    {
        return maxExecWaitSeconds;
    }

    /**
     * @param maxExecWaitSeconds
     *            the maxExecWaitSeconds to set
     */
    public void setMaxExecWaitSeconds(String maxExecWaitSeconds)
    {
        this.maxExecWaitSeconds = maxExecWaitSeconds;
    }
}

package com.melbourneit.model.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author qinhaoding
 */
@XmlRootElement(name = "delegateDomainRequest")
public class DelegateDomainRequest extends LoggableRequest
{

    private String domainName;
    private String username;
    private String password;
    private String primaryNameServer;
    private String clientRefId;
    private List<String> secondaryNameServers;

    public String getDomainName()
    {
        return domainName;
    }

    public void setDomainName(String name)
    {
        this.domainName = name;
    }

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

    public String getPrimaryNameServer()
    {
        return primaryNameServer;
    }

    public void setPrimaryNameServer(String action)
    {
        this.primaryNameServer = action;
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

    @XmlElement(name = "nameserver")
    @XmlElementWrapper(name = "secondaryNameServers")
    public List<String> getSecondaryNameServers()
    {
        return secondaryNameServers;
    }

    public void setSecondaryNameServers(List<String> secondaryNameServers)
    {
        this.secondaryNameServers = secondaryNameServers;
    }
}

package com.melbourneit.model.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author qinhaoding
 */
@XmlRootElement(name = "updateLockStatusRequest")
public class UpdateLockStatusRequest extends LoggableRequest
{
    private String name;
    private String username;
    private String password;
    private String action;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
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

    public String getAction()
    {
        return action;
    }

    public void setAction(String action)
    {
        this.action = action;
    }
}

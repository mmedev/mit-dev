package com.melbourneit.model.rest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "checkDomainRequest")
public class CheckDomainRequest extends LoggableRequest implements Serializable
{

	private static final long serialVersionUID = 1323232L;
	private String name;
    private String username;
    private String password;
    
    public CheckDomainRequest(String name, String username, String password) {
		this.name=name;
		this.username=username;
		this.password=password;
	}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }
}

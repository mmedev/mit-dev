package com.melbourneit.utils.security;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.ws.security.WSPasswordCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerPropertyFilePasswordCallback implements CallbackHandler
{
    private static final Logger LOG = LoggerFactory.getLogger(ServerPropertyFilePasswordCallback.class);
    
    private Properties userPasswordsProperties = null;
    private String userPasswordsPropertiesFileName = null;
    
    @Override
    public void handle(Callback[] callbacks) throws IOException,
            UnsupportedCallbackException
    {
        WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
        
        String userIdentifier = pc.getIdentifier();
        String userPassword = userPasswordsProperties.getProperty(userIdentifier);
        if (userPassword != null)
        {
            LOG.info("Found password for user: " + userIdentifier + " userPassword: " + userPassword);
            pc.setPassword(userPassword);
        }
        else
        {
            LOG.info("Ooops no password found for user: " + userIdentifier);
        }
    }

    public void setUserPasswordsPropertiesFileName(String userPropertyFileName)
    {
        this.userPasswordsPropertiesFileName = userPropertyFileName;
    }
    
    public void init()
    {
        //attempt to read the user properties 
        try
        {
            userPasswordsProperties = new Properties();
            userPasswordsProperties.load(new FileInputStream(userPasswordsPropertiesFileName));
            LOG.info("Successfully initialized. UsersPasswords property file is: " + userPasswordsPropertiesFileName);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("UserNamesPropertyFile name: " + userPasswordsPropertiesFileName + " not valid. Error: " + e);
        }
        
    }
}

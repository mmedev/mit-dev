package com.melbourneit.utils.security;


import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.util.Properties;

import javax.ws.rs.core.Response;

import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.jaxrs.ext.RequestHandler;
import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfo;
import org.apache.cxf.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melbourneit.utils.annotation.Authenticate;
import com.melbourneit.utils.rest.ApiResponse;
import com.melbourneit.utils.rest.StatusType;

/**
 * This class provides basic auth authorization handling for apache cxf rest services
 * 
 * @author teo
 *
 */
public class BasicAuthAuthorizationRestHandler implements RequestHandler 
{
    private Properties userPasswordsProperties = null;
    private String userPasswordsPropertiesFileName = null;
    private boolean allowConnectionWithNoAuthPolicy = false;
    private boolean returnApiResponseAsBody = true;
    private static Logger log = LoggerFactory.getLogger(BasicAuthAuthorizationRestHandler.class);
    
    public void init()
    {
        //attempt to read the user properties 
        try
        {
            userPasswordsProperties = new Properties();
            userPasswordsProperties.load(new FileInputStream(userPasswordsPropertiesFileName));
            log.info("Successfully initialized. UsersPasswords property file is: " + userPasswordsPropertiesFileName);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException("UserNamesPropertyFile name: " + userPasswordsPropertiesFileName + " not valid. Error: " + e);
        }
        
    }

    @Override
    public Response handleRequest(Message message, ClassResourceInfo arg1)
    {
        if (allowConnectionWithNoAuthPolicy == true)
        {
            //well nothing to do, just allow everything to pass through
            log.info("Do not care about credentials, flag was set to allow everything in");
            return null;
        }
        
        //should this message be auhtenticated??
        if (!shouldAuthenticate(message))
        {
            return null;
        }
        
        //so we must authenticate
        // This is set by CXF
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        
        // If the policy is not set, the user did not specify
        // credentials. A 401 is sent to the client to indicate
        // that authentication is required
        if (policy == null) 
        {
                log.info("No credentials were specified on the connection, rejecting request");
                ResponseBuilderImpl responseBuilder = ApiResponse.buildErrorResponseBuilder("Http Basic authentication user/passwords must be set", 
                        StatusType.ERROR_REQUEST_REQUIRES_AUTHENTICATION, returnApiResponseAsBody);
                responseBuilder.header("WWW-Authenticate", "Basic");
                return responseBuilder.build();
        }
        
        String userName = policy.getUserName();
        // Verify the password
        String realPassword = userPasswordsProperties.getProperty(userName);
        
        if (realPassword == null || !realPassword.equals(policy.getPassword())) 
        {
            log.warn("Invalid username or password for user: " + userName + " with request Password: " + policy.getPassword());
            ResponseBuilderImpl responseBuilder = ApiResponse.buildErrorResponseBuilder("User/passwords are not correct", 
                    StatusType.ERROR_REQUEST_ACCESS_DENIED, returnApiResponseAsBody);
            responseBuilder.header("WWW-Authenticate", "Basic");
            return responseBuilder.build();
        }
        else
        {
            log.info("User: " + userName + " has valid password verified");
        }

        
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * This function will verify whetehr authetication is to be used for a given method
     * It first verifies if the annotation Authenticate is set on the class, if it is set to false then no authentication will be performed
     * If the class Authenticate flag is set to true it will attempt to authenticate all functions unless an Authenticate annotation has 
     * been set to false on the method.
     * If there is no class Authenticate annotation the code will check if there is an Authenticate annotation set to true for the method 
     * @param m
     * @return
     */
    public boolean shouldAuthenticate(Message m)
    {
        OperationResourceInfo info = m.getExchange().get(OperationResourceInfo.class);
        
        if (info != null)
        {
            boolean authenticate = false;
            Authenticate authClassAnnotation = info.getAnnotatedMethod().getDeclaringClass().getAnnotation(Authenticate.class);
           
            if (authClassAnnotation != null)
            {
                if (!authClassAnnotation.value())
                {
                    return false;
                }
                
                //well time to turn the authenticate flag to on for now:
                authenticate = true;
            }
            
            Authenticate authMethodAnnotation = info.getAnnotatedMethod().getAnnotation(Authenticate.class);
            if (authMethodAnnotation != null)
            {
                authenticate = authMethodAnnotation.value();
            }
            
            return authenticate;
        }
        else
        {
            return false;
        }
    }
    
    public void setUserPasswordsPropertiesFileName(String userPropertyFileName)
    {
        this.userPasswordsPropertiesFileName = userPropertyFileName;
    }

    public void setAllowConnectionWithNoAuthPolicy(
            boolean allowConnectionWithNoAuthPolicy)
    {
        this.allowConnectionWithNoAuthPolicy = allowConnectionWithNoAuthPolicy;
    }

    public boolean isReturnApiResponseAsBody()
    {
        return returnApiResponseAsBody;
    }

    public void setReturnApiResponseAsBody(boolean returnApiResponseAsBody)
    {
        this.returnApiResponseAsBody = returnApiResponseAsBody;
    }
}
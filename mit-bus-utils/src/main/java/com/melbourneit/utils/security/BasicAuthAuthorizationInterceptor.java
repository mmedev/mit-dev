package com.melbourneit.utils.security;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.binding.soap.interceptor.SoapHeaderInterceptor;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Endpoint;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.transport.Conduit;
import org.apache.cxf.ws.addressing.EndpointReferenceType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class provides basic auth authorization handling for apache cxf web services
 * 
 * @author teo
 *
 */
public class BasicAuthAuthorizationInterceptor extends SoapHeaderInterceptor 
{
    private Properties userPasswordsProperties = null;
    private String userPasswordsPropertiesFileName = null;
    private boolean allowConnectionWithNoAuthPolicy = false;
    private static Logger log = LoggerFactory.getLogger(BasicAuthAuthorizationInterceptor.class);
    
    @Override 
    public void handleMessage(Message message)    throws Fault 
    {
        // This is set by CXF
        AuthorizationPolicy policy = message.get(AuthorizationPolicy.class);
        
        // If the policy is not set, the user did not specify
        // credentials. A 401 is sent to the client to indicate
        // that authentication is required
        if (policy == null) 
        {
            if (allowConnectionWithNoAuthPolicy == false)
            {
                log.info("No credentials were specified on the connection");
                sendErrorResponse(message,
                    HttpURLConnection.HTTP_UNAUTHORIZED);
            }
            else
            {
                log.info("No credentials, allowing connectivity for now");
            }
            return;
        }
        
        String userName = policy.getUserName();
        // Verify the password
        String realPassword = userPasswordsProperties.getProperty(userName);
        
        if (realPassword == null || !realPassword.equals(policy.getPassword())) 
        {
            log.warn("Invalid username or password for user: " + userName + " with request Password: " + policy.getPassword());
            sendErrorResponse(message,
                    HttpURLConnection.HTTP_FORBIDDEN);
        }
        else
        {
            log.info("User: " + userName + " has valid password verified");
        }
    }
    
    private void sendErrorResponse(Message message,   int responseCode) 
    {
        Message outMessage = getOutMessage(message);
        outMessage.put(Message.RESPONSE_CODE,
                responseCode);
        
        // Set the response headers
        Map responseHeaders = (Map)message.get(Message.PROTOCOL_HEADERS);
        
        if (responseHeaders != null) 
        {
            responseHeaders.put("WWW-Authenticate",
                    Arrays.asList(new String[]{"Basic realm=realm"}));
            responseHeaders.put("Content-Length",
                    Arrays.asList(new String[]{"0"}));
        }
        
        message.getInterceptorChain().abort();
        
        try 
        {
            getConduit(message).prepare(outMessage);
            close(outMessage);
        } 
        catch (IOException e) 
        {
            log.warn(e.getMessage(), e);
        }
    }
    
    private Message getOutMessage(Message inMessage) 
    {
        Exchange exchange = inMessage.getExchange();
        Message outMessage = exchange.getOutMessage();
        if (outMessage == null) 
        {
            Endpoint endpoint = exchange.get(Endpoint.class);
            outMessage = endpoint.getBinding().createMessage();
            exchange.setOutMessage(outMessage);
        }
        
        outMessage.putAll(inMessage);
        return outMessage;
    }
    
    private Conduit getConduit(Message inMessage) throws IOException 
    {
        Exchange exchange = inMessage.getExchange();
        EndpointReferenceType target = exchange.get(
                EndpointReferenceType.class);
        Conduit conduit =
            exchange.getDestination().getBackChannel(
                    inMessage, null, target);
        exchange.setConduit(conduit);
        return conduit;
    }
    
    private void close(Message outMessage) throws IOException 
    {
        OutputStream os = outMessage.getContent(OutputStream.class);
        os.flush();
        os.close();
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
}
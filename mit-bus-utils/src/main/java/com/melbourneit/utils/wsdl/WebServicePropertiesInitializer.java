package com.melbourneit.utils.wsdl;

import java.net.URL;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.common.util.StringUtils;
import org.apache.cxf.configuration.security.AuthorizationPolicy;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxb.JAXBToStringStyle;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class WebServicePropertiesInitializer
{
    protected static final Logger Log = LoggerFactory.getLogger(WebServicePropertiesInitializer.class);
    private String wsdlFileName;
    private URL wsdlFileUrl;
    private String endPointAddress;
    private boolean logInputFlag = false;
    private boolean logOutputFlag = false;
    private boolean useBasicHttpAuth = false;
    private String basicAuthUserName;
    private String basicAuthPassword;
    private long connectionTimeOut = 0;
    private long receiveTimeOut = 0;
    
    public WebServicePropertiesInitializer()
    {
        //intentionally left empty.
    }
    
    public void init()
    {
        String errorStr = "";
        
        if (StringUtils.isEmpty(wsdlFileName))
        {
            errorStr += " Field wsdlFileName must be set; ";
        }
        else
        {
            //attempt to validate the wsdl.
            wsdlFileUrl = Thread.currentThread().getContextClassLoader().getResource(wsdlFileName);
            if (wsdlFileUrl == null)
            {
                errorStr += " Could not find: " + wsdlFileName + ", please ensure the file name is valid and it can be found on the classpath; ";
            }
        }
        
        if (StringUtils.isEmpty(endPointAddress))
        {
            errorStr +=" Field endPointAddress must be set; ";
        }
        
        //validate the user name and password if basic auth has been set
        if (useBasicHttpAuth)
        {
            if (StringUtils.isEmpty(basicAuthUserName))
            {
                errorStr += " Field basicAuthUserName must be set as useBasicAuth was set to true; ";
            }
            
            if (StringUtils.isEmpty(basicAuthPassword))
            {
                errorStr += " Field basicAuthPassword must be set as useBasicAuth was set to true; ";
            }
        }
        else
        {
            if (!StringUtils.isEmpty(basicAuthUserName))
            {
                errorStr += " Field basicAuthUserName must not be set whem useBasicAuth is set to false; ";
            }
            
            if (!StringUtils.isEmpty(basicAuthPassword))
            {
                errorStr += " Field basicAuthPassword must not be set whem useBasicAuth is set to false; ";
            }
        }
        
        if (!StringUtils.isEmpty(errorStr))
        {
            throw new IllegalArgumentException(errorStr);
        }
        
        Log.info("WebServicePropertiesInitializer started for: " + wsdlFileName + " Settings: " + this);
    }
    
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.DEFAULT_STYLE);
    }

    public String getWsdlFileName()
    {
        return wsdlFileName;
    }

    public void setWsdlFileName(String wsdlFileName)
    {
        this.wsdlFileName = wsdlFileName;
    }

    public String getEndPointAddress()
    {
        return endPointAddress;
    }

    public void setEndPointAddress(String endPointAddress)
    {
        this.endPointAddress = endPointAddress;
    }

    public boolean isLogInputFlag()
    {
        return logInputFlag;
    }

    public void setLogInputFlag(boolean logInputFlag)
    {
        this.logInputFlag = logInputFlag;
    }

    public boolean isLogOutputFlag()
    {
        return logOutputFlag;
    }

    public void setLogOutputFlag(boolean logOutputFlag)
    {
        this.logOutputFlag = logOutputFlag;
    }

    public boolean isUseBasicHttpAuth()
    {
        return useBasicHttpAuth;
    }

    public void setUseBasicHttpAuth(boolean useBasicHttpAuth)
    {
        this.useBasicHttpAuth = useBasicHttpAuth;
    }

    public String getBasicAuthUserName()
    {
        return basicAuthUserName;
    }

    public void setBasicAuthUserName(String basicAuthUserName)
    {
        this.basicAuthUserName = basicAuthUserName;
    }

    public String getBasicAuthPassword()
    {
        return basicAuthPassword;
    }

    public void setBasicAuthPassword(String basicAuthPassword)
    {
        this.basicAuthPassword = basicAuthPassword;
    }

    public long getConnectionTimeOut()
    {
        return connectionTimeOut;
    }

    public void setConnectionTimeOut(long connectionTimeOut)
    {
        this.connectionTimeOut = connectionTimeOut;
    }

    public long getReceiveTimeOut()
    {
        return receiveTimeOut;
    }

    public void setReceiveTimeOut(long receiveTimeOut)
    {
        this.receiveTimeOut = receiveTimeOut;
    }

    public URL getWsdlFileUrl()
    {
        return wsdlFileUrl;
    }

    public void initializeWebServiceProperties(Object port)
    {
        Client client = ClientProxy.getClient(port);
        
        //ensure that we set the logging interceptors for the web service calls
        if (logInputFlag)
        {
            client.getInInterceptors().add(new LoggingInInterceptor());
        }
        
        if (logOutputFlag)
        {
            client.getOutInterceptors().add(new LoggingOutInterceptor());
        }
        
        //ensure that we set the endpoint 
        client.getRequestContext().put(org.apache.cxf.message.Message.ENDPOINT_ADDRESS, endPointAddress);

        //now retrieve the http conduit
        HTTPConduit httpConduit = (HTTPConduit)client.getConduit();

        //check if we are to set any policy on the object
        if ((connectionTimeOut >0) || (receiveTimeOut > 0))
        {
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();

            if (connectionTimeOut > 0)
            {
                httpClientPolicy.setConnectionTimeout(connectionTimeOut);
            }

            if (receiveTimeOut > 0)
            {
                httpClientPolicy.setReceiveTimeout(receiveTimeOut);
            }
            httpConduit.setClient(httpClientPolicy);
        }
        
        if (useBasicHttpAuth)
        {
            //create and set the AuthorizationPolicy on the conduit
            AuthorizationPolicy auth = new AuthorizationPolicy();
            //set the user name and password with values retrieved from a property file, here they are hard coded as an example only
            auth.setUserName(basicAuthUserName);
            auth.setPassword( basicAuthPassword);
            //now set them on the httpConduit
            httpConduit.setAuthorization(auth);
        }
    }
}

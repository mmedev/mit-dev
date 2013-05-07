package com.melbourneit.utils;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class HttpUtil
{
    private static final Logger LOG = LoggerFactory.getLogger(HttpUtil.class);
    public static final int DEFAULT_CONN_TIMEOUT = 10000;
    public static final int DEFAULT_READ_TIMEOUT = 15000;
    public static final int DEFAULT_MAX_CONNECTIONS = 20;
    public static final int DEFAULT_MAX_CONNECTIONS_PER_HOST = 20;

    /**
     * This function will attempt to retrieve data for a fully encoded url
     * @param url - the fully encoded url to run
     * @return - The return string in UTF-8 encoding
     * @throws IOException
     * @throws HttpException
     */
    public static String performHttpGet(String url) throws HttpException, IOException
    {
        return performHttpGet(url, "UTF-8", DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public static String performHttpGet(String uri, String encoding, HttpClient client)
                                 throws HttpException, IOException
    {
        String result = null;

        LOG.info("URL: " + uri);
        GetMethod httpGetMethod = new GetMethod(uri);

        try
        {
            int statusCode = client.executeMethod(httpGetMethod);

            //we must read all the data back
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            //result = httpGetMethod.getResponseBodyAsString();
            result = new String(httpGetMethod.getResponseBody(), encoding);

            if (statusCode != HttpStatus.SC_OK)
            {
                LOG.info("Http Method failed: " + httpGetMethod.getStatusCode() + " result: "  + result);
                throw new HttpException("Returned Result is not success: " + statusCode);
            }
        }
        finally
        {
            //the connection MUST be released before continuing
            httpGetMethod.releaseConnection();
        }

        return result;
    }

    /**
     * This function will attempt to retrieve data for a fully encoded url
     * @param uri - the fully encoded url to run
     * @param encoding - the encoding of the returned string
     * @return - The return string in the request encoding
     * @throws HttpException
     * @throws IOException
     */
    public static String performHttpGet(String uri, String encoding, int connTimeout,
                                        int readTimeout)
                                 throws HttpException, IOException
    {
        HttpConnectionManagerParams conParams = new HttpConnectionManagerParams();
        conParams.setConnectionTimeout(connTimeout);
        conParams.setSoTimeout(readTimeout);

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().setParams(conParams);

        return performHttpGet(uri, encoding, client);
    }

    public static String performHttpGet(String uri, String encoding, Hashtable params,
                                        HttpClient client)
                                 throws HttpException, IOException
    {
        String encodedUrl = buildFullUriFromUrlAndProperties(uri, encoding, params);

        return performHttpGet(encodedUrl, encoding, client);
    }

    public static String performHttpGet(String uri, String encoding, Hashtable params)
                                 throws HttpException, IOException
    {
        return performHttpGet(uri, encoding, params, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public static String buildFullUriFromUrlAndProperties(String url, String encoding,
                                                          Hashtable params)
                                                   throws UnsupportedEncodingException
    {
        StringBuffer queryString = new StringBuffer(256);

        Enumeration keys = params.keys();
        int i = 0;

        while (keys.hasMoreElements())
        {
            if (i++ == 0)
            {
                queryString.append("?");
            }
            else
            {
                queryString.append("&");
            }

            String key = (String) keys.nextElement();
            Object value = params.get(key);

            if (value instanceof List)
            {
                List valueList = (List) value;

                for (Iterator iter = valueList.iterator(); iter.hasNext();)
                {
                    queryString.append(key).append("=");
                    queryString.append(URLEncoder.encode(iter.next().toString(), encoding));

                    if (iter.hasNext())
                    {
                        queryString.append("&");
                    }
                }
            }
            else
            {
                queryString.append(key).append("=");
                queryString.append(URLEncoder.encode(value.toString(), encoding));
            }
        }

        return url + queryString.toString();
    }

    public static String performHttpGet(String url, String encoding, Hashtable params,
                                        int connTimeout, int readTimeout)
                                 throws HttpException, IOException
    {
        String encodedUrl = buildFullUriFromUrlAndProperties(url, encoding, params);

        //well it is time to encode the url before attempting to make the call
        return performHttpGet(encodedUrl, encoding, connTimeout, readTimeout);
    }

    public static String performHttpPost(String uri, String encoding, Hashtable params)
                                  throws HttpException, IOException
    {
        return performHttpPost(uri, encoding, params, DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT);
    }

    public static String performHttpPost(String uri, String encoding, Hashtable params,
                                         HttpClient client)
                                  throws HttpException, IOException
    {
        String result = null;
        PostMethod httpPostMethod = new PostMethod(uri);

        try
        {
            //time to set the input parameters
            Enumeration keys = params.keys();

            while (keys.hasMoreElements())
            {
                String paramName = (String) keys.nextElement();
                String paramValue = (String) params.get(paramName);
                httpPostMethod.addParameter(paramName, paramValue);
            }

            int statusCode = client.executeMethod(httpPostMethod);

            //we must read all the data back
            ByteArrayOutputStream stream = new ByteArrayOutputStream();

            //result = httpGetMethod.getResponseBodyAsString();
            result = new String(httpPostMethod.getResponseBody(), encoding);

            if (statusCode != HttpStatus.SC_OK)
            {
                LOG.info("Http Method failed: " + httpPostMethod.getStatusCode());
                throw new HttpException("Returned Result is not success: " + statusCode);
            }
        }
        finally
        {
            //the connection MUST be released before continuing
            httpPostMethod.releaseConnection();
        }

        return result;
    }

    /**
     * This function will attempt to retrieve data for a post request
     * @param uri - the fully encoded url to run
     * @param encoding - the encoding of the returned string
     * @return - The return string in the request encoding
     * @throws HttpException
     * @throws IOException
     */
    public static String performHttpPost(String uri, String encoding, Hashtable params,
                                         int connTimeout, int readTimeout)
                                  throws HttpException, IOException
    {
        HttpConnectionManagerParams conParams = new HttpConnectionManagerParams();
        conParams.setConnectionTimeout(connTimeout);
        conParams.setSoTimeout(readTimeout);

        HttpClient client = new HttpClient();
        client.getHttpConnectionManager().setParams(conParams);

        return performHttpPost(uri, encoding, params, client);
    }
    
    public static HttpClient getNewHttpClient(int connectionTimeout, int readTimeout, int maxTotalConnections,
            int maxConnectionsPerHost)
    {
        //attempt to create all the necessary connections management
        HttpConnectionManagerParams connMgrParams = new HttpConnectionManagerParams();
        //max time in attempting to establish a connection to the server
        connMgrParams.setConnectionTimeout(connectionTimeout);
        //max time to wait for a response from the server
        connMgrParams.setSoTimeout(readTimeout);
        //the following two settings deal with maximum number of connections for concurrent executions 
        connMgrParams.setMaxTotalConnections(maxTotalConnections);
        //maximum allowed per host
        connMgrParams.setDefaultMaxConnectionsPerHost(maxConnectionsPerHost);
        
        MultiThreadedHttpConnectionManager multiThreadConnMgr = new MultiThreadedHttpConnectionManager();
        multiThreadConnMgr.setParams(connMgrParams);
        
        
        //attempt to run a test via an http client to connect to mobiart crap
        HttpClient client = new HttpClient();
        client.setHttpConnectionManager(multiThreadConnMgr);
        
        return client;
    }
    
    public static HttpClient getNewHttpClient()
    {
        return getNewHttpClient(DEFAULT_CONN_TIMEOUT, DEFAULT_READ_TIMEOUT, 
                DEFAULT_MAX_CONNECTIONS, DEFAULT_MAX_CONNECTIONS_PER_HOST);
    }
    
    public static void main(String[] args)
    {
        //attempt to run a test via an http client to connect to mobiart crap
        final HttpClient client = getNewHttpClient();
        
        //attempt to perform a login request first:
        try
        {
            
            String loginResponse = performHttpGet("http://api.mmepoc.mobeeart.com/VerizonMMEAPI.asmx/ApiLogin?ApiUsername=api@mmepoc&ApiPassword=123poC!@$",
                "UTF-8", client);
            System.out.println("\nSharedClient LoginResponse: " + loginResponse);
            
            //base sellp time is 30 seconds
            long baseSleepTime = 10000;
            final long startTime = System.currentTimeMillis() + 10000;
            
                for (int i=1; i<=4; i++)
                {
                    Thread thread = new Thread(new Runnable()
                    {
                        
                        @Override
                        public void run()
                        {
                            while (System.currentTimeMillis() < startTime)
                            {
                                try { Thread.sleep(10); } catch (Exception e) {}
                            }
                            try
                            {
                                for (int j=0; j<6; ++j)
                                {
                                    System.out.println(Thread.currentThread().toString() + " Loop[" + j + "] About to call performHttpGet");
                                    String createResponse= performHttpGet("http://api.mmepoc.mobeeart.com/VerizonMMEAPI.asmx/CreateAccount?Username=teostest5&Password=pass&Email=teo.groza@melbourneit.com.au",
                                            "UTF-8",client);
                                    System.out.println(Thread.currentThread().toString() + " Loop[" + j + "] SharedClient Create user Response: " + createResponse);
                                }
                            }
                            catch (Exception e)
                            {
                                
                            }
                        }
                    });
                    
                    thread.start();
            }
            
            //time to now create an account
            
            //we will now attempt to do it a different way
            /*
            String loginResponse = performHttpGet("http://api.mmepoc.mobeeart.com/VerizonMMEAPI.asmx/ApiLogin?ApiUsername=api@mmepoc&ApiPassword=123poC!@$");


            System.out.println("\nLoginResponse: " + loginResponse);
            String createResponse= performHttpGet("http://api.mmepoc.mobeeart.com/VerizonMMEAPI.asmx/CreateAccount?Username=teostest4&Password=pass&Email=teo.groza@melbourneit.com.au");

            System.out.println("\nCreate user Response: " + createResponse);
            */

        }
        catch (Throwable e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}

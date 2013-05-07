package com.melbourneit.utils;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.FileRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;

public final class HttpRestClient {

    private String urlStr;
    private String fileNameToSend;
    
    private HttpRestClient(String urlStr, String fileNameToSend) 
    {
        this.urlStr = urlStr;
        this.fileNameToSend = fileNameToSend; 
    }
    
    public static void main(String args[]) throws Exception 
    {
        try
        {
            if (args.length != 2)
            {
                System.out.println("Usage: java " + HttpRestClient.class.getName() + " [url] [fileToSend]");
                System.exit(0);
            }
            
            String urlStr = args[0];
            String fileName = args[1];
            HttpRestClient client = new HttpRestClient(urlStr, fileName);
            
            client.executeHttpRequest();
        }
        catch (Throwable e)
        {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
        

        System.out.println("\n");
        System.exit(0);
    }

    private void executeHttpRequest() throws Exception
    {
        PostMethod post = new PostMethod(urlStr);
        File input = new File(fileNameToSend);
        RequestEntity entity = new FileRequestEntity(input, "text/xml; charset=ISO-8859-1");

        post.setRequestEntity(entity);
        HttpClient httpclient = new HttpClient();
        
        System.out.println("\n\nAbout to do an http post call on url: " + urlStr + " of file contents: " + fileNameToSend);
        try 
        {
            int result = httpclient.executeMethod(post);
            System.out.println("Response status code: " + result);
            System.out.println("Response body: ");
            System.out.println(post.getResponseBodyAsString());
        } 
        finally 
        {
            // Release current connection to the connection pool once you are
            // done
            post.releaseConnection();
        }
    }
}

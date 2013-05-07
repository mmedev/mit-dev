package com.melbourneit.utils.rest;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.DatatypeConfigurationException;

import org.apache.cxf.jaxrs.impl.ResponseBuilderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melbourneit.utils.Constants;
import com.sun.xml.bind.AnyTypeAdapter;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "apiResponse")
@XmlType(propOrder = {
        "status",
        "code",
        "message",
        "extendedMessage",
        "moreInfo",
        "timeStamp",
        "resource"
})
@XmlSeeAlso(ResourceContainer.class)
public class ApiResponse
{
    //private String status;
    private StatusType statusType;
    private String code;
    private String message;
    private String extendedMessage;
    private String moreInfo;
    private String timeStamp;
    private ResourceContainer resource;
    public static final Logger Log = LoggerFactory.getLogger(ApiResponse.class);
    
    public ApiResponse()
    {
        //generate the timeStamp first
        updateTimeStamp(new Date());
        
    }

    public ApiResponse(StatusType statusType)
    {
        //generate the timeStamp first
        updateTimeStamp(new Date());
        this.statusType = statusType;
        this.code = statusType.toString();
    }

    public ApiResponse(StatusType statusType, String code, ResourceContainer resource)
    {
        this.statusType = statusType;
        this.code = code;
        this.resource = resource;
        updateTimeStamp(new Date());
    }
    
    @XmlElement(name="status")
    public String getStatus()
    {
        return "" + statusType.getIntValue();
    }
    
    public int getStatusInt()
    {
        return statusType.getIntValue();
    }
    
    public StatusType getStatusType()
    {
        return statusType;
    }

    public void setStatusType(StatusType statusType)
    {
        this.statusType = statusType;
        if ((statusType != null) && (code == null))
        {
            //reflect the statusType to a code string representation for easier understanding of the returned code
            code = statusType.toString();
        }
    }

    @XmlElement(name="code")
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }
    
    @XmlElement(name="message")
    public String getMessage()
    {
        return message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }
    
    @XmlElement(name="extendedMessage")
    public String getExtendedMessage()
    {
        return extendedMessage;
    }
    
    public void setExtendedMessage(String extendedMessage)
    {
        this.extendedMessage = extendedMessage;
    }
    
    @XmlElement(name="moreInfo")
    public String getMoreInfo()
    {
        return moreInfo;
    }
    
    public void setMoreInfo(String moreInfo)
    {
        this.moreInfo = moreInfo;
    }
    
    @XmlElement(name="timeStamp")
    public String getTimeStamp()
    {
        return timeStamp;
    }
    
    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }
    
    @XmlAnyElement
    public ResourceContainer getResource()
    {        
        return resource;
    }
    
    public void setResource(ResourceContainer resource)
    {
        this.resource = resource;
    }
    
    public void updateTimeStamp(Date dateTime)
    {
        this.timeStamp = Constants.getDateTimeAsString(dateTime);
    }

    public static ResponseBuilderImpl buildErrorResponseBuilder(String errorString, StatusType statusType, boolean returnApiResponse)
    {
        if (returnApiResponse)
        {
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatusType(statusType);
            apiResponse.setCode(statusType.toString());
            apiResponse.setMessage(errorString);

            return buildResponseBuilder(apiResponse);
        }
        else
        {
            ResponseBuilderImpl builder = new ResponseBuilderImpl();
            builder.status(statusType.getIntValue());

            return builder;
        }
    }

    public static ResponseBuilderImpl buildErrorResponseBuilder(String errorString, StatusType statusType, List<MediaType> returnMediaList)
    {
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusType(statusType);
        apiResponse.setCode(statusType.toString());
        apiResponse.setMessage(errorString);

        ResponseBuilderImpl builder = new ResponseBuilderImpl();
        builder.status(apiResponse.getStatusInt());
        //default to the errorString for the return String.
        String returnString =errorString;
        boolean generateJson = false;
        //find out if json 
        for (MediaType currentMediaType : returnMediaList)
        {
            if (currentMediaType.isCompatible(MediaType.APPLICATION_JSON_TYPE) || 
                    currentMediaType.isCompatible(MediaType.TEXT_PLAIN_TYPE))
            {
                generateJson = true;
                break;
            }
        }
        
        if (generateJson)
        {
            returnString = apiResponse.generateJsonString();
        }
        else
        {
            returnString = apiResponse.generateXmlString();
        }
        builder.entity(returnString);

        return builder;
    }

    public static ResponseBuilderImpl buildResponseBuilder(ApiResponse apiResponse)
    {
        ResponseBuilderImpl builder = new ResponseBuilderImpl();
        builder.status(apiResponse.getStatusInt());
        builder.entity(apiResponse);
        
        return builder;
    }
    
    /**
     * This function is mainly called to verify if the received response object was received. If the receivedObject is null it assumes
     * that it was not received and it will attempt to alos set the rest ApiResponse object status, code and error
     *  
     * @param apiResponse - the apiResponse to set the status, code, extendedMessage if no response was received
     * @param receivedResponse - the object to check if a response was received
     * @param timedOut - a flag for whether or not there was a time out waiting for a response
     * @param logId - the logId assoicated with this function
     * @return - true if hte receivedResponse is not null, else false and then sets the apiResponse fields accordingly
     */
    public static boolean hasResponseObjectBeenReceived(ApiResponse apiResponse, Object receivedResponse, boolean timedOut, String logId)
    {
        boolean result = true;
        if (receivedResponse == null)
        {
            //well, the received response is not valid
            result = false;
            Log.info(logId + " Ooops we received no response, request timed out: " + timedOut);
            if (timedOut)
            {
                apiResponse.setStatusType(StatusType.ERROR_SERVER_GATEWAY_TIMEOUT);
                apiResponse.setMessage(AbstractRestProvider.ERROR_TIMED_OUT_WAITING_FOR_RESPONSE);
                apiResponse.setExtendedMessage(logId);
            }
            else
            {
                apiResponse.setStatusType(StatusType.ERROR_SERVER_INTERNAL_ERROR);
                apiResponse.setMessage(AbstractRestProvider.ERROR_UNEXPECTED_WAITING_FOR_RESPONSE);
                apiResponse.setExtendedMessage(logId);
            }
        }
        
        return result;
    }
    
    private String generateJsonString()
    {
        StringBuffer buff = new StringBuffer(256);
        
        boolean hasSetValue = false;
        buff.append("{\"apiResponse\":{");
        if (statusType != null)
        {
            buff.append("\"status\":").append(getStatusInt());
            hasSetValue = true;
        }
        
        if (code != null)
        {
            if (hasSetValue) buff.append(",");
            buff.append("\"code\":\"").append(getCode()).append("\"");
            hasSetValue = true;
        }

        if (message != null)
        {
            if (hasSetValue) buff.append(",");
            buff.append("\"message\":\"").append(getMessage()).append("\"");
            hasSetValue = true;
        }
        
        if (message != null)
        {
            if (hasSetValue) buff.append(",");
            buff.append("\"extendedMessage\":\"").append(getExtendedMessage()).append("\"");
            hasSetValue = true;
        }

        if (message != null)
        {
            if (hasSetValue) buff.append(",");
            buff.append("\"moreInfo\":\"").append(getMoreInfo()).append("\"");
            hasSetValue = true;
        }

        if (message != null)
        {
            if (hasSetValue) buff.append(",");
            buff.append("\"timeStamp\":\"").append(getTimeStamp()).append("\"");
            hasSetValue = true;
        }

        buff.append("}}");
        return buff.toString();
    }
    
    private String generateXmlString()
    {
        StringBuffer buff = new StringBuffer(512);
        
        buff.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><apiResponse>");
        
        if (statusType != null)
        {
            buff.append(getXmlString("status", getStatus()));
        }

        buff.append(getXmlString("code",code));
        buff.append(getXmlString("message",message));
        buff.append(getXmlString("extendedMessage",extendedMessage));
        buff.append(getXmlString("moreInfo",moreInfo));
        buff.append(getXmlString("timeStamp",timeStamp));
        
        return buff.toString();
    }

    private String getXmlString(String fieldName, String fieldValue)
    {
        if (fieldValue == null)
        {
            return "";
        }
        
        if (fieldValue.length() < 1)
        {
            return "</" + fieldName + ">";
        }
        
        return "<" + fieldName + ">" + fieldValue + "</" + fieldName + ">";
    }
}

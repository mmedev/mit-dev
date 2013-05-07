package com.melbourneit.utils.spin;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author ruchirad
 * 
 */
public class GenericResult {
    private String result;
    private int resultCode;
    private String actionName;
    private GregorianCalendar dateTime;
    private String message;
    private Map<String, String> resultMap;

    private static final Logger LOG = LoggerFactory.getLogger(GenericResult.class);

    public GenericResult()
    {
        //iensure that we have set the date time for this
        setDateTime(new GregorianCalendar());
    }

    public GenericResult(String httpResponse, String logId)
    {
        setResponseFromGenericApiHttpResponse(httpResponse, logId, this);
    }

    public Map<String, String> getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map<String, String> resultMap) {
        this.resultMap = resultMap;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public GregorianCalendar getDateTime() {
        return dateTime;
    }

    public void setDateTime(GregorianCalendar dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString()
    {
        StringBuffer buff = new StringBuffer(2048);

        buff.append("ResultStr[").append(result);
        buff.append("] ResultCode[").append(resultCode);
        buff.append("] ActionName[").append(actionName);
        buff.append("] DateTime[").append(dateTime);
        buff.append("] Message[").append(message);
        buff.append("] ResultMap[").append(resultMap).append("]");

        return buff.toString();
    }

    public static void setResponseFromGenericApiHttpResponse(String httpResponse, String logId, GenericResult result)
    {
        if (StringUtils.isNotEmpty(httpResponse))
        {
            LOG.info("...........httpResponse [" + httpResponse + "].................");

            result.setResult("SUCCESS");
            result.setResultCode(1000);
            result.setMessage("Action performed successfully");

            Map resultMap = new HashMap();
            resultMap.put("RESULT", httpResponse);

            result.setResultMap(resultMap);
            result.setDateTime(new GregorianCalendar());
        }        	
        else
        {
            LOG.info(logId + " Ooops we received an empty result");
        }
    }
}

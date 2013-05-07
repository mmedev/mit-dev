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
 * @author qinhaoding
 * 
 */
public class SPINGenericResult {
	private String result;
	private int resultCode;
	private String actionName;
	private GregorianCalendar dateTime;
	private String message;
	private Map<String, String> resultMap;
	private String rawHttpResponse;
	private String logId;
	
    private static final Logger LOG = LoggerFactory.getLogger(SPINGenericResult.class);

	public SPINGenericResult()
	{
	    dateTime = new GregorianCalendar();
	    logId = "not-set";
	}
	
	public SPINGenericResult(String httpResponse, String domainName, String logId)
	{
	    this.logId = logId;
	    setResponseFromGenericApiHttpResponse(httpResponse, logId, this);
	    setDomainName(domainName);
	}
	
	/**
	 * The purpose of this constructor is to allow the creation of a new response with the spin code and resultCode changed
	 * @param result
	 * @param resultCode
	 * @param genericSpinResult
	 */
	public SPINGenericResult(String result, int resultCode, SPINGenericResult genericSpinResult)
	{
	    this.result = result;
	    this.resultCode = resultCode;
	    actionName = genericSpinResult.actionName;
	    dateTime = genericSpinResult.dateTime;
	    message = genericSpinResult.message;
	    resultMap = genericSpinResult.resultMap;
	    rawHttpResponse = genericSpinResult.rawHttpResponse;
	    this.logId = genericSpinResult.logId;
	}
	
	private void setDomainName(String domainName)
	{
	    if (domainName != null)
	    {
	        //will attempt to set the domain name if it is not set in the map already.
	        if (resultMap == null)
	        {
	            resultMap = new HashMap<String,String>();
	        }
	        String currentDomainName = resultMap.get(SPINConstants.GenericApi.FieldNames.DOMAIN_NAME);
	        if (!domainName.equalsIgnoreCase(currentDomainName))
	        {
	            LOG.info(logId + " About to set domainName to[" + domainName + "] from[" + currentDomainName + "]");
	            resultMap.put(SPINConstants.GenericApi.FieldNames.DOMAIN_NAME, domainName);
	        }
	    }
	    else
	    {
	        LOG.info(logId + " No domain name to set for SPINGenericResult");
	    }
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
	
    public String getRawHttpResponse()
    {
        return rawHttpResponse;
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
	
	public void setResponseFromGenericApiHttpResponse(String httpResponse, String logId, SPINGenericResult spinResult)
	{
	    rawHttpResponse = httpResponse;
        if (StringUtils.isNotEmpty(httpResponse))
        {
            String results[] = httpResponse.split("\\|");

            LOG.info(logId + " Results.length: " + results.length);
            if (results.length > 5)
            {
                spinResult.result = results[0].trim();
                spinResult.resultCode = Integer.parseInt(results[1].trim());
                spinResult.actionName = results[2].trim();
                GregorianCalendar cal = new GregorianCalendar();
                
                try
                {
                    //the date format should really be read from a property file
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                    Date date = df.parse(results[3].trim());
                
                    cal.setTime(date);
                }
                catch (ParseException e)
                {
                    LOG.error(logId + " Error parsing the date: " + e);
                }
                
                spinResult.dateTime = cal;
                spinResult.message = results[4].trim();
                String allParametersString = null;
                if (results.length == 6)
                {
                    allParametersString = results[5].trim();
                    spinResult.resultMap = extractParametersToMap(allParametersString, logId);
                }
                else
                {
                    //well time to reassemble all the parameters string as it obviously had extra "|" piping symbols in the data
                    StringBuffer buff = new StringBuffer(2048);
                    for (int i=5;i<results.length;++i)
                    {
                        buff.append(results[i].trim());
                        if (i < results.length-1)
                        {
                            //we need to replace the escaped character
                            buff.setCharAt(buff.length()-1, '|');
                        }
                    }
                    
                    allParametersString = buff.toString();
                    spinResult.resultMap = extractParametersToMap(allParametersString, logId);
                }
            }
            else
            {
                spinResult.dateTime = new GregorianCalendar();
                spinResult.message = httpResponse;
                if (httpResponse.toLowerCase().indexOf("password incorrect") > -1)
                {
                    spinResult.result = "Error";
                }
                else
                {
                    LOG.info(logId + " Ooops we received unknown data format: " + httpResponse);
                    spinResult.result = "Unknown response. Please report to customer support";
                }
                //generic error response
                spinResult.resultCode = 9;
            }
        }
        else
        {
            LOG.info(logId + " Ooops we received an empty result from spin");
        }
	}
	
    private static Map<String,String> extractParametersToMap(String allParamsStr, String logId)
    {
        HashMap<String, String> map = new HashMap<String, String>();

        int currentPos = 0;
        int allParamsStrLength = allParamsStr.length();

        while (currentPos < allParamsStrLength)
        {
            String nextParamStr = "";
            int nextIndex = allParamsStr.indexOf(';',currentPos);
            if (nextIndex > 0)
            {
                //well time to check if it was an escaped character
                while (allParamsStr.charAt(nextIndex-1) == '\\')
                {
                    //ok, so it was an escaped character, time to get the next one
                    nextParamStr += allParamsStr.substring(currentPos,nextIndex-1) + ";";
                    currentPos = nextIndex+1;
                    nextIndex = allParamsStr.indexOf(';',currentPos);
                }

                if (nextIndex > 0)
                {
                    nextParamStr += allParamsStr.substring(currentPos,nextIndex);
                    currentPos = nextIndex+1;
                }
                else
                {
                    currentPos = allParamsStrLength;
                }
                
                setParameterDataFromString(map, nextParamStr, logId);
            }
            else
            {
                if (currentPos < allParamsStr.length()-1)
                {
                    nextParamStr = allParamsStr.substring(currentPos+1);
                    setParameterDataFromString(map, nextParamStr, logId);
                }
                else
                {
                   LOG.info(logId + " Finished parsing, no more to parse");
                }

                currentPos = allParamsStrLength;
            }
        }

        return map;
    }

    private static void setParameterDataFromString(HashMap<String,String> map, String param, String logId)
    {
        int delimiterIndex = param.indexOf('=');
        if (LOG.isDebugEnabled())
        {
            LOG.debug(logId + " DelimiterIndex=" + delimiterIndex + " Param[" + param + "] CurrentIndex=" + map.size());
        }
        
        if (delimiterIndex > 0)
        {
            String key = param.substring(0, delimiterIndex).trim();
            String value = "";
            if (delimiterIndex < (param.length() -1))
            {
                value = param.substring(delimiterIndex+1);
            }

            if (key != null)
            {
                map.put(key, value);
            }
            
            //was the value entered an array?? 
            if (((value != null) && value.startsWith("[") && value.endsWith("]") && (value.length() > 2))
                    || ((key != null) && key.endsWith("[]")))
            {
                StringTokenizer subListValuesTokenizer = null;
                //ok so it was an array, time to now parse and set parameters for that array
                if (value.startsWith("[") && value.endsWith("]"))
                {
                    //if it ends with "}" exclude this parameter name
                    subListValuesTokenizer= new StringTokenizer(value.substring(1,value.length()-1).trim(),",");
                }
                else
                {
                    subListValuesTokenizer = new StringTokenizer(value.trim(),",");
                }
                
                int tokenCount = subListValuesTokenizer.countTokens();
                String newKey = null;
                if (key.endsWith("[]"))
                {
                    newKey = key.substring(0,key.length()-2);
                }
                else
                {
                    newKey = key;
                }
                
                for (int i=0;i<tokenCount;++i)
                {
                    String subListValue = subListValuesTokenizer.nextToken().trim();
                    map.put(newKey + "." + i, subListValue);
                }
            }
        }
        else
        {
            LOG.info(logId + " Ooops illegal data[" + param + "]");
        }
    }
}

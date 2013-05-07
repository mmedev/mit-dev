package com.melbourneit.services.psb;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.melbourneit.model.psb.ContactInfo;
import com.melbourneit.utils.spin.DomainsBotApiInvoker;
import com.melbourneit.utils.spin.SPINConstants;
import com.melbourneit.utils.spin.SpinGenericApiInvoker;
import com.melbourneit.utils.UserDetails;

public class PSBServiceManagementImpl implements PSBServiceManagement
{
    private String spinUrl;

    private HttpClient checkDomainHttpClient;
    private HttpClient multiCheckDomainHttpClient;
    private HttpClient contactUpdateHttpClient;
    private HttpClient updateLockHttpClient;
    private HttpClient delegateDomainHttpClient;
    private HttpClient getRenewalInfoHttpClient;
    private HttpClient getDomainDetailsHttpClient;
    private HttpClient getWhoisDetailsHttpClient;
    private HttpClient getResellerReportsHttpClient;
    private HttpClient genericFunctionHttpClient;
    private HttpClient nameSuggestionHttpClient;

    private Hashtable<HttpClient,String> httpClientsHash = new Hashtable<HttpClient,String>();
    private Hashtable<String, HttpClient> actionToHttpClientMap = new Hashtable<String, HttpClient>();
    
    private static final Logger LOG = LoggerFactory
            .getLogger(PSBServiceManagementImpl.class);

    private String defaultMaxExecWaitSeconds;

    public void setCheckDomainHttpClient(HttpClient checkDomainHttpClient)
    {
        this.checkDomainHttpClient = checkDomainHttpClient;
    }

    public void setMultiCheckDomainHttpClient(HttpClient multiCheckDomainHttpClient)
    {
        this.multiCheckDomainHttpClient = multiCheckDomainHttpClient;
    }

    public void setContactUpdateHttpClient(HttpClient contactUpdateHttpClient)
    {
        this.contactUpdateHttpClient = contactUpdateHttpClient;
    }

    public void setUpdateLockHttpClient(HttpClient updateLockHttpClient)
    {
        this.updateLockHttpClient = updateLockHttpClient;
    }

    public void setDelegateDomainHttpClient(HttpClient delegateDomainHttpClient)
    {
        this.delegateDomainHttpClient = delegateDomainHttpClient;
    }

    public void setGenericFunctionHttpClient(HttpClient genericFunctionHttpClient)
    {
        this.genericFunctionHttpClient = genericFunctionHttpClient;
    }

    public void setGetDomainDetailsHttpClient(HttpClient getDomainDetailsHttpClient)
    {
        this.getDomainDetailsHttpClient = getDomainDetailsHttpClient;
    }

    public void setGetWhoisDetailsHttpClient(HttpClient getWhoisDetailsHttpClient)
    {
        this.getWhoisDetailsHttpClient = getWhoisDetailsHttpClient;
    }

    public void setGetResellerReportsHttpClient(
            HttpClient getResellerReportsHttpClient)
    {
        this.getResellerReportsHttpClient = getResellerReportsHttpClient;
    }

    public void setGetRenewalInfoHttpClient(HttpClient getRenewalInfoHttpClient)
    {
        this.getRenewalInfoHttpClient = getRenewalInfoHttpClient;
    }

    public HttpClient getNameSuggestionHttpClient() {
		return nameSuggestionHttpClient;
	}

	public void setNameSuggestionHttpClient(HttpClient nameSuggestionHttpClient) {
		this.nameSuggestionHttpClient = nameSuggestionHttpClient;
	}

	/**
     * @param defaultMaxExecWaitSeconds
     *            the defaultMaxExecWaitSeconds to set
     */
    public void setDefaultMaxExecWaitSeconds(String defaultMaxExecWaitSeconds)
    {
        this.defaultMaxExecWaitSeconds = defaultMaxExecWaitSeconds;
    }

    /**
     * @param spinUrl
     *            the spinUrl to set
     */
    public void setSpinUrl(String spinUrl)
    {
        this.spinUrl = spinUrl;
    }
    
    private void verifyHttpClients()
    {
        String errorStr = "";
        String warningStr = "";
        if (genericFunctionHttpClient == null)
        {
            errorStr += "The field genericFunctionHttpClient must be set; ";
        }
        
        if (nameSuggestionHttpClient == null)
        {
            errorStr += "The field nameSuggestionHttpClient must be set; ";
        }
        
        //now attempt to check that all the other clients have been set
        if (checkDomainHttpClient == null)
        {
            warningStr += " setting checkDomainHttpClient to genericFunctionHttpClient ";
            checkDomainHttpClient = genericFunctionHttpClient;
        }
        
        if (multiCheckDomainHttpClient == null)
        {
            warningStr += " setting multiCheckDomainHttpClient to genericFunctionHttpClient ";
            multiCheckDomainHttpClient = genericFunctionHttpClient;
        }
        
        if (contactUpdateHttpClient == null)
        {
            warningStr += " setting contactUpdateHttpClient to genericFunctionHttpClient ";
            contactUpdateHttpClient = genericFunctionHttpClient;
        }

        if (updateLockHttpClient == null)
        {
            warningStr += " setting updateLockHttpClient to genericFunctionHttpClient ";
            updateLockHttpClient = genericFunctionHttpClient;
        }

        if (delegateDomainHttpClient == null)
        {
            warningStr += " setting delegateDomainHttpClient to genericFunctionHttpClient ";
            delegateDomainHttpClient = genericFunctionHttpClient;
        }

        if (getRenewalInfoHttpClient == null)
        {
            warningStr += " setting getRenewalInfoHttpClient to genericFunctionHttpClient ";
            getRenewalInfoHttpClient = genericFunctionHttpClient;
        }

        if (getDomainDetailsHttpClient == null)
        {
            warningStr += " setting getDomainDetailsHttpClient to genericFunctionHttpClient ";
            getDomainDetailsHttpClient = genericFunctionHttpClient;
        }

        if (getWhoisDetailsHttpClient == null)
        {
            warningStr += " setting getWhoisDetailsHttpClient to genericFunctionHttpClient ";
            getWhoisDetailsHttpClient = genericFunctionHttpClient;
        }

        if (getResellerReportsHttpClient == null)
        {
            warningStr += " setting getResellerReportsHttpClient to genericFunctionHttpClient ";
            getResellerReportsHttpClient = genericFunctionHttpClient;
        }
        
        if (warningStr.length() > 0)
        {
            LOG.warn("WArning about settings: " + warningStr);
        }
        
        if (errorStr.length() > 0)
        {
            LOG.error("PSBServiceManagementImpl has not been set up properly: " + errorStr);
            throw new IllegalArgumentException(errorStr);
        }
    }
    
    public void init(){
        verifyHttpClients();
        // at these stage all parameters are supposed to be set by now, time to
        // work out the stats
        // about how many connections allowed per each operations and who shares
        // what
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.CHECK_DOMAIN,
                checkDomainHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.CHECK_MULTIPLE_DOMAINS,
                multiCheckDomainHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.DELEGATE_DOMAIN,
                delegateDomainHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.GET_RENEWAL_INFO,
                getRenewalInfoHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.UPDATE_LOCK_STATUS,
                updateLockHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.UPDATE_CONTACTS,
                contactUpdateHttpClient);

        addHttpClientForFunction(SPINConstants.GenericApi.Actions.GET_DOMAIN_DETAILS,
                getDomainDetailsHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.GET_WHOIS_DETAILS,
                getWhoisDetailsHttpClient);
        addHttpClientForFunction(SPINConstants.GenericApi.Actions.GET_RESELLER_REPORTS,
                getResellerReportsHttpClient);

        
        addHttpClientForFunction("genericSpinApiCall.*", genericFunctionHttpClient);
        addHttpClientForFunction("GetAvailableNameSuggestions", nameSuggestionHttpClient);

        // display the stats of who uses what.
        Enumeration<HttpClient> keys = httpClientsHash.keys();
        while (keys.hasMoreElements())
        {
            HttpClient httpClient = (HttpClient) keys.nextElement();
            String actionsUsingClient = (String) httpClientsHash.get(httpClient);
            LOG.info(getHttpClientSetupInfo(httpClient, actionsUsingClient));
        }
    }

    private void appendHttpClientHashSetupInfo(StringBuilder strBuilder)
    {
        Enumeration<HttpClient> keys = httpClientsHash.keys();
        while (keys.hasMoreElements())
        {
            HttpClient httpClient = (HttpClient) keys.nextElement();
            String actionsUsingClient = (String) httpClientsHash.get(httpClient);
            strBuilder.append(getHttpClientSetupInfo(httpClient, actionsUsingClient));
            strBuilder.append("\n");
        }
    }

    private String getHttpClientSetupInfo(HttpClient httpClient, String actionsUsingClient)
    {
        StringBuilder strBuilder = new StringBuilder(256);
        boolean sharedConnections = actionsUsingClient.indexOf(",") > 0;
        HttpConnectionManagerParams connParams =
            httpClient.getHttpConnectionManager().getParams();

        strBuilder.append("HttpClient ");
        if (sharedConnections)
        {
            strBuilder.append("shared by actions: ");
        }
        else
        {
            strBuilder.append("notshared, used by action: ");
        }

        strBuilder.append(actionsUsingClient).append(" ConnectionTimeout: ");
        strBuilder.append(connParams.getConnectionTimeout()).append(" ReadTimeout: ");
        strBuilder.append(connParams.getSoTimeout()).append(" MaxConnectionsPerHost: ");
        strBuilder.append(connParams.getDefaultMaxConnectionsPerHost());
        strBuilder.append(" MaxTotalConnections: ").append(
                connParams.getMaxTotalConnections());

        return strBuilder.toString();
    }

    private void addHttpClientForFunction(String actionName, HttpClient httpClient)
    {
        actionToHttpClientMap.put(actionName,httpClient);
        String actionsUsingClient = (String) httpClientsHash.get(httpClient);

        if (actionsUsingClient == null)
        {
            actionsUsingClient = actionName;
        }
        else
        {
            actionsUsingClient += "," + actionName;
        }

        // time to update the hash with this http client
        httpClientsHash.put(httpClient, actionsUsingClient);
    }

    private HashMap<String, String> getNewParamsMapWithName(String domainName)
    {
        HashMap<String, String> params = new HashMap<String,String>();
        params.put(SPINConstants.GenericApi.FieldNames.DOMAIN_NAME, domainName);

        return params;
    }

    @Override
    public String checkDomainName(String domainName, UserDetails details, String clientRefId, 
            String logId)
    {
        HashMap<String,String> params = getNewParamsMapWithName(domainName);

        return SpinGenericApiInvoker.invokeApi(checkDomainHttpClient, spinUrl, 
                SPINConstants.GenericApi.Actions.CHECK_DOMAIN, details, clientRefId, logId, params);
    }

    @Override
    public String getRenewalInformation(String domainName, UserDetails details, String clientRefId,
            String logId)
    {
        HashMap<String,String> params = getNewParamsMapWithName(domainName);

        return SpinGenericApiInvoker.invokeApi(getRenewalInfoHttpClient, spinUrl,
                SPINConstants.GenericApi.Actions.GET_RENEWAL_INFO, details, clientRefId, logId, params);
    }

    @Override
    public String updateLockStatus(String domainName, UserDetails details, String action,
            String clientRefId, String logId)
    {
        HashMap<String,String> params = getNewParamsMapWithName(domainName);
        params.put(SPINConstants.GenericApi.FieldNames.DOMAIN_LOCK_STATUS, action);

        return SpinGenericApiInvoker.invokeApi(updateLockHttpClient, spinUrl,
                SPINConstants.GenericApi.Actions.UPDATE_LOCK_STATUS, details, clientRefId, logId, params);
    }

    @Override
    public String delegateDomain(String domainName, UserDetails details,
            String clientRefId, String primaryNameServer,
            List<String> secondaryNameServers, String logId)
    {
        HashMap<String,String> params = getNewParamsMapWithName(domainName);
        params.put(SPINConstants.GenericApi.FieldNames.DOMAIN_NAME_SERVER + "[0]",
                primaryNameServer);
        for (int i = 1; i <= secondaryNameServers.size(); ++i)
        {
            params.put(SPINConstants.GenericApi.FieldNames.DOMAIN_NAME_SERVER + "[" + i
                    + "]", secondaryNameServers.get(i - 1));
        }

        return SpinGenericApiInvoker.invokeApi(delegateDomainHttpClient, spinUrl,
                SPINConstants.GenericApi.Actions.DELEGATE_DOMAIN, details, clientRefId, logId, params);
    }

    @Override
    public String updateContactDetails(String domainName, UserDetails details,
            String clientRefId, ContactInfo contactInfo, String logId)
    {
        HashMap<String,String> params = getNewParamsMapWithName(domainName);
        populateContactParams(params,
                SPINConstants.GenericApi.FieldNames.Contact.Type.REGISTRANT,
                contactInfo.getRegistrantContactData());
        populateContactParams(params,
                SPINConstants.GenericApi.FieldNames.Contact.Type.ADMIN,
                contactInfo.getAdminContactData());
        populateContactParams(params,
                SPINConstants.GenericApi.FieldNames.Contact.Type.BILL,
                contactInfo.getBillContactData());
        populateContactParams(params,
                SPINConstants.GenericApi.FieldNames.Contact.Type.TECH,
                contactInfo.getTechContactData());

        return SpinGenericApiInvoker.invokeApi(contactUpdateHttpClient, spinUrl, 
                SPINConstants.GenericApi.Actions.UPDATE_CONTACTS, details, clientRefId, logId, params);
    }

    @Override
    public String runGenericSpinCommand(Map<String, String> spinParamsMap)
    {
        String actionName = spinParamsMap.get(SPINConstants.GenericApi.FieldNames.ACTION_NAME);
        String logId = spinParamsMap.get(SPINConstants.GenericApi.FieldNames.ENTERPRISE_BUS_LOGID);
        
        //attempt to retrieve the httpClient to be used for this action
        HttpClient httpClient = actionToHttpClientMap.get(actionName);
        
        if (httpClient == null)
        {
            LOG.info(logId + " Could not find httpClient for action: " + actionName + " defaulting to genericHttpClient");
            httpClient = genericFunctionHttpClient;
        }
        
        return SpinGenericApiInvoker.invokeApi(httpClient, spinUrl, spinParamsMap, logId, actionName);
    }

    public String getDomainNameSuggestions(Map<String, String> paramsMap, String url, int maximumRows, int defaultRows)
    {

        String actionName = paramsMap.get(SPINConstants.GenericApi.FieldNames.ACTION_NAME);
        String logId = paramsMap.get(SPINConstants.GenericApi.FieldNames.ENTERPRISE_BUS_LOGID);
        LOG.info(logId + " getDomainNameSuggestions in PSBServiceManagementImpl. Params[" + paramsMap + "]");
        
        HttpClient httpClient = actionToHttpClientMap.get(actionName);
        if (httpClient == null)
        {
            LOG.info(logId + " Could not find httpClient for action: " + actionName + " defaulting to genericHttpClient");
            httpClient = genericFunctionHttpClient;
        }
        
        //time to verify the asked for limit for the number of rows to be returned
        int rowsLimitToUse = defaultRows;
        if (paramsMap.get("limit") != null)
        {
            int askedForLimit = Integer.parseInt(paramsMap.get("limit"));
            if ((askedForLimit > 0) && (askedForLimit <= maximumRows))
            {
                LOG.info(logId + " Setting number of rows to return to: " + askedForLimit);
                rowsLimitToUse = askedForLimit;
            }
            else
            {
                LOG.info(logId + " Using default row limit of: " + rowsLimitToUse);
            }
        }
        
        //return DomainsBotApiInvoker.getDomainNameSuggestions(paramsMap);
        //we are now set to call the domainsbot api invoker
        return DomainsBotApiInvoker.invokeApi(httpClient, url, paramsMap,logId, actionName, rowsLimitToUse);
    }
    
    private void populateContactParams(HashMap<String,String> params, String contactType,
            ContactInfo.ContactData contactData)
    {
        // if not a registrant contact then we must populate the given and
        // family names
        if (!contactType
                .equalsIgnoreCase(SPINConstants.GenericApi.FieldNames.Contact.Type.REGISTRANT))
        {
            params.put(contactType + "."
                    + SPINConstants.GenericApi.FieldNames.Contact.GIVEN_NAME,
                    contactData.getGivenName());
            params.put(contactType + "."
                    + SPINConstants.GenericApi.FieldNames.Contact.FAMILY_NAME,
                    contactData.getGivenName());
        }

        // well we can now set all the other contact's parameters
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ORGANISATION,
                contactData.getOrganisation());
        params.put(contactType + "." + SPINConstants.GenericApi.FieldNames.Contact.EMAIL,
                contactData.getEmail());
        params.put(contactType + "." + SPINConstants.GenericApi.FieldNames.Contact.PHONE,
                contactData.getPhone());
        params.put(contactType + "." + SPINConstants.GenericApi.FieldNames.Contact.FAX,
                contactData.getFax());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_LINE1,
                contactData.getAddressLine1());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_LINE2,
                contactData.getAddressLine2());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_LINE3,
                contactData.getAddressLine3());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_CITY,
                contactData.getAddressCity());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_STATE_PROVINCE,
                contactData.getAddressStateprovince());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_POSTALCODE,
                contactData.getAddressPostalcode());
        params.put(contactType + "."
                + SPINConstants.GenericApi.FieldNames.Contact.ADDRESS_COUNTRY_CODE,
                contactData.getAddressCountrycode());
    }

    @Override
    public String checkDomainNames(List<String> domains, UserDetails details,
            String maxExecWaitSeconds, String clientRefId, String logId)
    {
        HashMap<String,String> params = new HashMap<String,String>();

        if (StringUtils.isEmpty(maxExecWaitSeconds))
        {
            params.put(SPINConstants.GenericApi.FieldNames.MAX_EXEC_WAIT_SECONDS,
                    defaultMaxExecWaitSeconds);
        }
        else
        {
            params.put(SPINConstants.GenericApi.FieldNames.MAX_EXEC_WAIT_SECONDS,
                    maxExecWaitSeconds);
        }

        StringBuilder builder = new StringBuilder(512);
        for (Iterator<String> iter = domains.iterator(); iter.hasNext();)
        {
            builder.append(iter.next()).append(",");
        }

        params.put(SPINConstants.GenericApi.FieldNames.DOMAIN_NAMES, builder.toString());

        return SpinGenericApiInvoker.invokeApi(multiCheckDomainHttpClient, spinUrl,
                SPINConstants.GenericApi.Actions.CHECK_MULTIPLE_DOMAINS, details,
                clientRefId, logId, params);
    }

    @Override
    public String getStats()
    {
        StringBuilder strBuilder = new StringBuilder(256 * httpClientsHash.size() + 1024);
        appendHttpClientHashSetupInfo(strBuilder);

        strBuilder.append("\nSpinGenericInvoker activeRequest: ");
        strBuilder.append(SpinGenericApiInvoker.getActiveRequestsCount());
        strBuilder.append(" handledRequests: ");
        strBuilder.append(SpinGenericApiInvoker.getHandledRequestsCount());

        return strBuilder.toString();
    }

}

package com.melbourneit.services.psb;

import java.util.List;
import java.util.Map;

import com.melbourneit.model.psb.ContactInfo;
import com.melbourneit.utils.UserDetails;

/**
 * This interface defines the operations exposed by the PSBServiceManagement OSGi service
 * There are only 6 operations defined at this stage.
 * To add more operations to your OSGi service, add those operations in this interface
 * with appropriate signatures
 * @author mhanda
 */
public interface PSBServiceManagement
{
    /**
     * This operation provides check domain service in the form of an OSGi service
     * @param domainName The domain name
     * @param details User details
     * @param logId Log id for platform tracings
     * @return Result of check domain
     */
    public String checkDomainName(String domainName, UserDetails details, String clientRefId,
            String logId);

    /**
     * This operation provides get renewal information service in the form of an OSGi service
     * @param domainName The domain name
     * @param details User details
     * @param logId Log id for platform tracings
     * @return Result of get renewal information
     */
    public String getRenewalInformation(String domainName, UserDetails details, String clientRefId,
            String logId);

    /**
     * This operation provides update lock status service in the form of an OSGi service
     * @param domainName The domain name
     * @param details User details
     * @param action The lock status action, such as no-lock
     * @param logId Log id for platform tracings
     * @return Result of update lock status
     */
    public String updateLockStatus(String domainName, UserDetails details, String action, 
            String clientRefId, String logId);

    /**
     * This operation provides delegate domain service in the form of an OSGi service
     * @param domainName The domain name
     * @param details User details
     * @param clientRefId
     * @param primaryNameServer The primary name servers for domain delegation
     * @param secondaryNameServers The secondary name servers for domain delegation
     * @param logId Log id for platform tracings
     * @return Result of delegate domain
     */
    public String delegateDomain(String domainName, UserDetails details, String clientRefId, String primaryNameServer,
            List<String> secondaryNameServers, String logId);

    /**
     * 
     * This operation provides delegate domain service in the form of an OSGi service
     * @param domainName The domain name
     * @param details User details
     * @param clientRefId
     * @param info Contact information
     * @param logId Log id for platform tracings
     * @return Result of update contact details
     */
    public String updateContactDetails(String domainName, UserDetails details, String clientRefId, ContactInfo info,
            String logId);

    /**
     * This operation provides check multiple domains service in the form of an OSGi service
     * @param domainNames The domain names
     * @param details User details
     * @param maxExecWaitSeconds Max time the consumer shall wait for
     * @param logId Log id for platform tracings
     * @return Result of check multiple domains
     */
    public String checkDomainNames(List<String> domainNames, UserDetails details, String maxExecWaitSeconds,
            String clientRefId, String logId);
    
    /**
     * This function will be used to run generic spin functions
     * @param spinParamsMap - the key value pairs required to execute a spin generic command
     * @return - the return string returned by the spin system
     */
    public String runGenericSpinCommand(Map<String, String> spinParamsMap);
    
    
    public String getDomainNameSuggestions(Map<String, String> paramsMap, String url, int maximumRows, int defaultRows);
    
    /**
     * This operation allows the retreval of stats for the service
     * 
     * @return -  A string representation of current stats for the domain service
     */
    public String getStats();
}

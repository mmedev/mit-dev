package com.melbourneit.model.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author mhanda
 */

@XmlRootElement(name = "contactUpdateRequest")
public class UpdateContactDetailsRequest extends LoggableRequest
{
    private String domainName;

    private String username;

    private String password;

    private String clientRefId;

    private String registrantOrganisation;

    private String registrantEmail;

    private String registrantPhone;

    private String registrantFax;

    private String registrantAddressLine1;

    private String registrantAddressLine2;

    private String registrantAddressLine3;

    private String registrantAddressCity;

    private String registrantAddressStateprovince;

    private String registrantAddressPostalcode;

    private String registrantAddressCountrycode;

    private String adminFamilyName;

    private String adminGivenName;

    private String adminOrganisation;

    private String adminEmail;

    private String adminPhone;

    private String adminFax;

    private String adminAddressLine1;

    private String adminAddressLine2;

    private String adminAddressLine3;

    private String adminAddressCity;

    private String adminAddressStateprovince;

    private String adminAddressPostalcode;

    private String adminAddressCountrycode;

    private String billFamilyName;

    private String billGivenName;

    private String billOrganisation;

    private String billEmail;

    private String billPhone;

    private String billFax;

    private String billAddressLine1;

    private String billAddressLine2;

    private String billAddressLine3;

    private String billAddressCity;

    private String billAddressStateprovince;

    private String billAddressPostalcode;

    private String billAddressCountrycode;

    private String techFamilyName;

    private String techGivenName;

    private String techOrganisation;

    private String techEmail;

    private String techPhone;

    private String techFax;

    private String techAddressLine1;

    private String techAddressLine2;

    private String techAddressLine3;

    private String techAddressCity;

    private String techAddressStateprovince;

    private String techAddressPostalcode;

    private String techAddressCountrycode;

    /**
     * @return the domainName
     */
    public String getDomainName()
    {
        return domainName;
    }

    /**
     * @param domainName
     *            the domainName to set
     */
    public void setDomainName(String domainName)
    {
        this.domainName = domainName;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

    /**
     * @return the clientRefId
     */
    public String getClientRefId()
    {
        return clientRefId;
    }

    /**
     * @param clientRefId
     *            the clientRefId to set
     */
    public void setClientRefId(String clientRefId)
    {
        this.clientRefId = clientRefId;
    }

    /**
     * @return the registrantOrganisation
     */
    public String getRegistrantOrganisation()
    {
        return registrantOrganisation;
    }

    /**
     * @param registrantOrganisation
     *            the registrantOrganisation to set
     */
    public void setRegistrantOrganisation(String registrantOrganisation)
    {
        this.registrantOrganisation = registrantOrganisation;
    }

    /**
     * @return the registrantEmail
     */
    public String getRegistrantEmail()
    {
        return registrantEmail;
    }

    /**
     * @param registrantEmail
     *            the registrantEmail to set
     */
    public void setRegistrantEmail(String registrantEmail)
    {
        this.registrantEmail = registrantEmail;
    }

    /**
     * @return the registrantPhone
     */
    public String getRegistrantPhone()
    {
        return registrantPhone;
    }

    /**
     * @param registrantPhone
     *            the registrantPhone to set
     */
    public void setRegistrantPhone(String registrantPhone)
    {
        this.registrantPhone = registrantPhone;
    }

    /**
     * @return the registrantFax
     */
    public String getRegistrantFax()
    {
        return registrantFax;
    }

    /**
     * @param registrantFax
     *            the registrantFax to set
     */
    public void setRegistrantFax(String registrantFax)
    {
        this.registrantFax = registrantFax;
    }

    /**
     * @return the registrantAddressLine1
     */
    public String getRegistrantAddressLine1()
    {
        return registrantAddressLine1;
    }

    /**
     * @param registrantAddressLine1
     *            the registrantAddressLine1 to set
     */
    public void setRegistrantAddressLine1(String registrantAddressLine1)
    {
        this.registrantAddressLine1 = registrantAddressLine1;
    }

    /**
     * @return the registrantAddressLine2
     */
    public String getRegistrantAddressLine2()
    {
        return registrantAddressLine2;
    }

    /**
     * @param registrantAddressLine2
     *            the registrantAddressLine2 to set
     */
    public void setRegistrantAddressLine2(String registrantAddressLine2)
    {
        this.registrantAddressLine2 = registrantAddressLine2;
    }

    /**
     * @return the registrantAddressLine3
     */
    public String getRegistrantAddressLine3()
    {
        return registrantAddressLine3;
    }

    /**
     * @param registrantAddressLine3
     *            the registrantAddressLine3 to set
     */
    public void setRegistrantAddressLine3(String registrantAddressLine3)
    {
        this.registrantAddressLine3 = registrantAddressLine3;
    }

    /**
     * @return the registrantAddressCity
     */
    public String getRegistrantAddressCity()
    {
        return registrantAddressCity;
    }

    /**
     * @param registrantAddressCity
     *            the registrantAddressCity to set
     */
    public void setRegistrantAddressCity(String registrantAddressCity)
    {
        this.registrantAddressCity = registrantAddressCity;
    }

    /**
     * @return the registrantAddressStateprovince
     */
    public String getRegistrantAddressStateprovince()
    {
        return registrantAddressStateprovince;
    }

    /**
     * @param registrantAddressStateprovince
     *            the registrantAddressStateprovince to set
     */
    public void setRegistrantAddressStateprovince(String registrantAddressStateprovince)
    {
        this.registrantAddressStateprovince = registrantAddressStateprovince;
    }

    /**
     * @return the registrantAddressPostalcode
     */
    public String getRegistrantAddressPostalcode()
    {
        return registrantAddressPostalcode;
    }

    /**
     * @param registrantAddressPostalcode
     *            the registrantAddressPostalcode to set
     */
    public void setRegistrantAddressPostalcode(String registrantAddressPostalcode)
    {
        this.registrantAddressPostalcode = registrantAddressPostalcode;
    }

    /**
     * @return the registrantAddressCountrycode
     */
    public String getRegistrantAddressCountrycode()
    {
        return registrantAddressCountrycode;
    }

    /**
     * @param registrantAddressCountrycode
     *            the registrantAddressCountrycode to set
     */
    public void setRegistrantAddressCountrycode(String registrantAddressCountrycode)
    {
        this.registrantAddressCountrycode = registrantAddressCountrycode;
    }

    /**
     * @return the adminFamilyName
     */
    public String getAdminFamilyName()
    {
        return adminFamilyName;
    }

    /**
     * @param adminFamilyName
     *            the adminFamilyName to set
     */
    public void setAdminFamilyName(String adminFamilyName)
    {
        this.adminFamilyName = adminFamilyName;
    }

    /**
     * @return the adminGivenName
     */
    public String getAdminGivenName()
    {
        return adminGivenName;
    }

    /**
     * @param adminGivenName
     *            the adminGivenName to set
     */
    public void setAdminGivenName(String adminGivenName)
    {
        this.adminGivenName = adminGivenName;
    }

    /**
     * @return the adminOrganisation
     */
    public String getAdminOrganisation()
    {
        return adminOrganisation;
    }

    /**
     * @param adminOrganisation
     *            the adminOrganisation to set
     */
    public void setAdminOrganisation(String adminOrganisation)
    {
        this.adminOrganisation = adminOrganisation;
    }

    /**
     * @return the adminEmail
     */
    public String getAdminEmail()
    {
        return adminEmail;
    }

    /**
     * @param adminEmail
     *            the adminEmail to set
     */
    public void setAdminEmail(String adminEmail)
    {
        this.adminEmail = adminEmail;
    }

    /**
     * @return the adminPhone
     */
    public String getAdminPhone()
    {
        return adminPhone;
    }

    /**
     * @param adminPhone
     *            the adminPhone to set
     */
    public void setAdminPhone(String adminPhone)
    {
        this.adminPhone = adminPhone;
    }

    /**
     * @return the adminFax
     */
    public String getAdminFax()
    {
        return adminFax;
    }

    /**
     * @param adminFax
     *            the adminFax to set
     */
    public void setAdminFax(String adminFax)
    {
        this.adminFax = adminFax;
    }

    /**
     * @return the adminAddressLine1
     */
    public String getAdminAddressLine1()
    {
        return adminAddressLine1;
    }

    /**
     * @param adminAddressLine1
     *            the adminAddressLine1 to set
     */
    public void setAdminAddressLine1(String adminAddressLine1)
    {
        this.adminAddressLine1 = adminAddressLine1;
    }

    /**
     * @return the adminAddressLine2
     */
    public String getAdminAddressLine2()
    {
        return adminAddressLine2;
    }

    /**
     * @param adminAddressLine2
     *            the adminAddressLine2 to set
     */
    public void setAdminAddressLine2(String adminAddressLine2)
    {
        this.adminAddressLine2 = adminAddressLine2;
    }

    /**
     * @return the adminAddressLine3
     */
    public String getAdminAddressLine3()
    {
        return adminAddressLine3;
    }

    /**
     * @param adminAddressLine3
     *            the adminAddressLine3 to set
     */
    public void setAdminAddressLine3(String adminAddressLine3)
    {
        this.adminAddressLine3 = adminAddressLine3;
    }

    /**
     * @return the adminAddressCity
     */
    public String getAdminAddressCity()
    {
        return adminAddressCity;
    }

    /**
     * @param adminAddressCity
     *            the adminAddressCity to set
     */
    public void setAdminAddressCity(String adminAddressCity)
    {
        this.adminAddressCity = adminAddressCity;
    }

    /**
     * @return the adminAddressStateprovince
     */
    public String getAdminAddressStateprovince()
    {
        return adminAddressStateprovince;
    }

    /**
     * @param adminAddressStateprovince
     *            the adminAddressStateprovince to set
     */
    public void setAdminAddressStateprovince(String adminAddressStateprovince)
    {
        this.adminAddressStateprovince = adminAddressStateprovince;
    }

    /**
     * @return the adminAddressPostalcode
     */
    public String getAdminAddressPostalcode()
    {
        return adminAddressPostalcode;
    }

    /**
     * @param adminAddressPostalcode
     *            the adminAddressPostalcode to set
     */
    public void setAdminAddressPostalcode(String adminAddressPostalcode)
    {
        this.adminAddressPostalcode = adminAddressPostalcode;
    }

    /**
     * @return the adminAddressCountrycode
     */
    public String getAdminAddressCountrycode()
    {
        return adminAddressCountrycode;
    }

    /**
     * @param adminAddressCountrycode
     *            the adminAddressCountrycode to set
     */
    public void setAdminAddressCountrycode(String adminAddressCountrycode)
    {
        this.adminAddressCountrycode = adminAddressCountrycode;
    }

    /**
     * @return the billFamilyName
     */
    public String getBillFamilyName()
    {
        return billFamilyName;
    }

    /**
     * @param billFamilyName
     *            the billFamilyName to set
     */
    public void setBillFamilyName(String billFamilyName)
    {
        this.billFamilyName = billFamilyName;
    }

    /**
     * @return the billGivenName
     */
    public String getBillGivenName()
    {
        return billGivenName;
    }

    /**
     * @param billGivenName
     *            the billGivenName to set
     */
    public void setBillGivenName(String billGivenName)
    {
        this.billGivenName = billGivenName;
    }

    /**
     * @return the billOrganisation
     */
    public String getBillOrganisation()
    {
        return billOrganisation;
    }

    /**
     * @param billOrganisation
     *            the billOrganisation to set
     */
    public void setBillOrganisation(String billOrganisation)
    {
        this.billOrganisation = billOrganisation;
    }

    /**
     * @return the billEmail
     */
    public String getBillEmail()
    {
        return billEmail;
    }

    /**
     * @param billEmail
     *            the billEmail to set
     */
    public void setBillEmail(String billEmail)
    {
        this.billEmail = billEmail;
    }

    /**
     * @return the billPhone
     */
    public String getBillPhone()
    {
        return billPhone;
    }

    /**
     * @param billPhone
     *            the billPhone to set
     */
    public void setBillPhone(String billPhone)
    {
        this.billPhone = billPhone;
    }

    /**
     * @return the billFax
     */
    public String getBillFax()
    {
        return billFax;
    }

    /**
     * @param billFax
     *            the billFax to set
     */
    public void setBillFax(String billFax)
    {
        this.billFax = billFax;
    }

    /**
     * @return the billAddressLine1
     */
    public String getBillAddressLine1()
    {
        return billAddressLine1;
    }

    /**
     * @param billAddressLine1
     *            the billAddressLine1 to set
     */
    public void setBillAddressLine1(String billAddressLine1)
    {
        this.billAddressLine1 = billAddressLine1;
    }

    /**
     * @return the billAddressLine2
     */
    public String getBillAddressLine2()
    {
        return billAddressLine2;
    }

    /**
     * @param billAddressLine2
     *            the billAddressLine2 to set
     */
    public void setBillAddressLine2(String billAddressLine2)
    {
        this.billAddressLine2 = billAddressLine2;
    }

    /**
     * @return the billAddressLine3
     */
    public String getBillAddressLine3()
    {
        return billAddressLine3;
    }

    /**
     * @param billAddressLine3
     *            the billAddressLine3 to set
     */
    public void setBillAddressLine3(String billAddressLine3)
    {
        this.billAddressLine3 = billAddressLine3;
    }

    /**
     * @return the billAddressCity
     */
    public String getBillAddressCity()
    {
        return billAddressCity;
    }

    /**
     * @param billAddressCity
     *            the billAddressCity to set
     */
    public void setBillAddressCity(String billAddressCity)
    {
        this.billAddressCity = billAddressCity;
    }

    /**
     * @return the billAddressStateprovince
     */
    public String getBillAddressStateprovince()
    {
        return billAddressStateprovince;
    }

    /**
     * @param billAddressStateprovince
     *            the billAddressStateprovince to set
     */
    public void setBillAddressStateprovince(String billAddressStateprovince)
    {
        this.billAddressStateprovince = billAddressStateprovince;
    }

    /**
     * @return the billAddressPostalcode
     */
    public String getBillAddressPostalcode()
    {
        return billAddressPostalcode;
    }

    /**
     * @param billAddressPostalcode
     *            the billAddressPostalcode to set
     */
    public void setBillAddressPostalcode(String billAddressPostalcode)
    {
        this.billAddressPostalcode = billAddressPostalcode;
    }

    /**
     * @return the billAddressCountrycode
     */
    public String getBillAddressCountrycode()
    {
        return billAddressCountrycode;
    }

    /**
     * @param billAddressCountrycode
     *            the billAddressCountrycode to set
     */
    public void setBillAddressCountrycode(String billAddressCountrycode)
    {
        this.billAddressCountrycode = billAddressCountrycode;
    }

    /**
     * @return the techFamilyName
     */
    public String getTechFamilyName()
    {
        return techFamilyName;
    }

    /**
     * @param techFamilyName
     *            the techFamilyName to set
     */
    public void setTechFamilyName(String techFamilyName)
    {
        this.techFamilyName = techFamilyName;
    }

    /**
     * @return the techGivenName
     */
    public String getTechGivenName()
    {
        return techGivenName;
    }

    /**
     * @param techGivenName
     *            the techGivenName to set
     */
    public void setTechGivenName(String techGivenName)
    {
        this.techGivenName = techGivenName;
    }

    /**
     * @return the techOrganisation
     */
    public String getTechOrganisation()
    {
        return techOrganisation;
    }

    /**
     * @param techOrganisation
     *            the techOrganisation to set
     */
    public void setTechOrganisation(String techOrganisation)
    {
        this.techOrganisation = techOrganisation;
    }

    /**
     * @return the techEmail
     */
    public String getTechEmail()
    {
        return techEmail;
    }

    /**
     * @param techEmail
     *            the techEmail to set
     */
    public void setTechEmail(String techEmail)
    {
        this.techEmail = techEmail;
    }

    /**
     * @return the techPhone
     */
    public String getTechPhone()
    {
        return techPhone;
    }

    /**
     * @param techPhone
     *            the techPhone to set
     */
    public void setTechPhone(String techPhone)
    {
        this.techPhone = techPhone;
    }

    /**
     * @return the techFax
     */
    public String getTechFax()
    {
        return techFax;
    }

    /**
     * @param techFax
     *            the techFax to set
     */
    public void setTechFax(String techFax)
    {
        this.techFax = techFax;
    }

    /**
     * @return the techAddressLine1
     */
    public String getTechAddressLine1()
    {
        return techAddressLine1;
    }

    /**
     * @param techAddressLine1
     *            the techAddressLine1 to set
     */
    public void setTechAddressLine1(String techAddressLine1)
    {
        this.techAddressLine1 = techAddressLine1;
    }

    /**
     * @return the techAddressLine2
     */
    public String getTechAddressLine2()
    {
        return techAddressLine2;
    }

    /**
     * @param techAddressLine2
     *            the techAddressLine2 to set
     */
    public void setTechAddressLine2(String techAddressLine2)
    {
        this.techAddressLine2 = techAddressLine2;
    }

    /**
     * @return the techAddressLine3
     */
    public String getTechAddressLine3()
    {
        return techAddressLine3;
    }

    /**
     * @param techAddressLine3
     *            the techAddressLine3 to set
     */
    public void setTechAddressLine3(String techAddressLine3)
    {
        this.techAddressLine3 = techAddressLine3;
    }

    /**
     * @return the techAddressCity
     */
    public String getTechAddressCity()
    {
        return techAddressCity;
    }

    /**
     * @param techAddressCity
     *            the techAddressCity to set
     */
    public void setTechAddressCity(String techAddressCity)
    {
        this.techAddressCity = techAddressCity;
    }

    /**
     * @return the techAddressStateprovince
     */
    public String getTechAddressStateprovince()
    {
        return techAddressStateprovince;
    }

    /**
     * @param techAddressStateprovince
     *            the techAddressStateprovince to set
     */
    public void setTechAddressStateprovince(String techAddressStateprovince)
    {
        this.techAddressStateprovince = techAddressStateprovince;
    }

    /**
     * @return the techAddressPostalcode
     */
    public String getTechAddressPostalcode()
    {
        return techAddressPostalcode;
    }

    /**
     * @param techAddressPostalcode
     *            the techAddressPostalcode to set
     */
    public void setTechAddressPostalcode(String techAddressPostalcode)
    {
        this.techAddressPostalcode = techAddressPostalcode;
    }

    /**
     * @return the techAddressCountrycode
     */
    public String getTechAddressCountrycode()
    {
        return techAddressCountrycode;
    }

    /**
     * @param techAddressCountrycode
     *            the techAddressCountrycode to set
     */
    public void setTechAddressCountrycode(String techAddressCountrycode)
    {
        this.techAddressCountrycode = techAddressCountrycode;
    }
}

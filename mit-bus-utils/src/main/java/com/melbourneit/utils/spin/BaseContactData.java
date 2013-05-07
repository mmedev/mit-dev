package com.melbourneit.utils.spin;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.cxf.jaxb.JAXBToStringStyle;

public class BaseContactData
{
    private String organisation;

    private String email;

    private String phone;

    private String fax;

    private String addressLine1;

    private String addressLine2;

    private String addressLine3;

    private String addressCity;

    private String addressStateprovince;

    private String addressPostalcode;

    private String addressCountrycode;
    
    private String vat;
    
    private String language;
    
    private String orgNumber;
    
    private String contactType;
    
    public String getOrganisation()
    {
        return organisation;
    }

    public void setOrganisation(String organisation)
    {
        this.organisation = organisation;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getFax()
    {
        return fax;
    }

    public void setFax(String fax)
    {
        this.fax = fax;
    }

    public String getAddressLine1()
    {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2()
    {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2)
    {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3()
    {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3)
    {
        this.addressLine3 = addressLine3;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public void setAddressCity(String addressCity)
    {
        this.addressCity = addressCity;
    }

    public String getAddressStateprovince()
    {
        return addressStateprovince;
    }

    public void setAddressStateprovince(String addressStateprovince)
    {
        this.addressStateprovince = addressStateprovince;
    }

    public String getAddressPostalcode()
    {
        return addressPostalcode;
    }

    public void setAddressPostalcode(String addressPostalcode)
    {
        this.addressPostalcode = addressPostalcode;
    }

    public String getAddressCountrycode()
    {
        return addressCountrycode;
    }

    public void setAddressCountrycode(String addressCountrycode)
    {
        this.addressCountrycode = addressCountrycode;
    }

    public String getVat()
    {
        return vat;
    }

    public void setVat(String vat)
    {
        this.vat = vat;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getOrgNumber()
    {
        return orgNumber;
    }

    public void setOrgNumber(String orgNumber)
    {
        this.orgNumber = orgNumber;
    }

    public String getContactType()
    {
        return contactType;
    }

    public void setContactType(String contactType)
    {
        this.contactType = contactType;
    }
    
    public String toString()
    {
        return ToStringBuilder.reflectionToString(this, JAXBToStringStyle.DEFAULT_STYLE);
    }
}
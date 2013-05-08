package com.melbourneit.model;

/**
 * @author mhanda
 */
public class ContactInfo
{
    public static class ContactData
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
        
        private String familyName;
        
        private String givenName;

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

        public String getFamilyName()
        {
            return familyName;
        }

        public void setFamilyName(String familyName)
        {
            this.familyName = familyName;
        }

        public String getGivenName()
        {
            return givenName;
        }

        public void setGivenName(String givenName)
        {
            this.givenName = givenName;
        }
    }

    private ContactData registrantContactData;
    private ContactData adminContactData;
    private ContactData techContactData;
    private ContactData billContactData;

    public ContactInfo()
    {
        registrantContactData = new ContactData();
        adminContactData = new ContactData();
        techContactData = new ContactData();
        billContactData = new ContactData();
    }

    public ContactData getRegistrantContactData()
    {
        return registrantContactData;
    }

    public ContactData getAdminContactData()
    {
        return adminContactData;
    }

    public ContactData getTechContactData()
    {
        return techContactData;
    }

    public ContactData getBillContactData()
    {
        return billContactData;
    }

}
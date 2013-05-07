package com.melbourneit.utils.spin;


/**
 * @author mhanda
 */
public class ContactsInfo
{
    private RegistrantContactData registrantContactData;
    private ContactData adminContactData;
    private ContactData techContactData;
    private ContactData billContactData;

    public ContactsInfo()
    {
        registrantContactData = new RegistrantContactData();
        adminContactData = new ContactData();
        techContactData = new ContactData();
        billContactData = new ContactData();
    }

    public RegistrantContactData getRegistrantContactData()
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

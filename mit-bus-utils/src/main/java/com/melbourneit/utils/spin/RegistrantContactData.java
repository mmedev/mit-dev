package com.melbourneit.utils.spin;

public class RegistrantContactData extends BaseContactData
{
    private String registrantName;
    
    private String registrantContactName;

    public String getRegistrantName()
    {
        return registrantName;
    }

    public void setRegistrantName(String registrantName)
    {
        this.registrantName = registrantName;
    }

    public String getRegistrantContactName()
    {
        return registrantContactName;
    }

    public void setRegistrantContactName(String registrantContactName)
    {
        this.registrantContactName = registrantContactName;
    }
}
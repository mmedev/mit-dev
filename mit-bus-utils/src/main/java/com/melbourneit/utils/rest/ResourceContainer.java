package com.melbourneit.utils.rest;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "resource")
public class ResourceContainer
{
    private Object resourceObject;
    
    public ResourceContainer()
    {
        //intentionally left empty
    }
    
    public ResourceContainer(Object resourceObject)
    {
        this.resourceObject = resourceObject;
    }

    @XmlAnyElement
    public Object getResourceObject()
    {
        return resourceObject;
    }

    public void setResourceObject(Object resourceObject)
    {
        this.resourceObject = resourceObject;
    }
}

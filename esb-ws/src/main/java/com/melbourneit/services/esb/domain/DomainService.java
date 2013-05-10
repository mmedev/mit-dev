package com.melbourneit.services.esb.domain;

import com.melbourneit.exception.BaseException;

public interface DomainService{
    
    public String checkDomainName(String domainName) throws BaseException;

}

package com.melbourneit.utils;

import com.melbourneit.model.psb.ContactInfo;
import com.melbourneit.model.rest.UpdateContactDetailsRequest;

/**
 *
 * @author mhanda
 */
public class RequestEntityToVOConverter
{
    public static ContactInfo convertXMLContactInfoToContactInfoVO(UpdateContactDetailsRequest request)
    {
        ContactInfo info = new ContactInfo();

        ContactInfo.ContactData adminContact = info.getAdminContactData();
        adminContact.setAddressCity(request.getAdminAddressCity());
        adminContact.setAddressCountrycode(request.getAdminAddressCountrycode());
        adminContact.setAddressLine1(request.getAdminAddressLine1());
        adminContact.setAddressLine2(request.getAdminAddressLine2());
        adminContact.setAddressLine3(request.getAdminAddressLine3());
        adminContact.setAddressPostalcode(request.getAdminAddressPostalcode());
        adminContact.setAddressStateprovince(request.getAdminAddressStateprovince());
        adminContact.setEmail(request.getAdminEmail());
        adminContact.setFamilyName(request.getAdminFamilyName());
        adminContact.setFax(request.getAdminFax());
        adminContact.setGivenName(request.getAdminGivenName());
        adminContact.setOrganisation(request.getAdminOrganisation());
        adminContact.setPhone(request.getAdminPhone());

        ContactInfo.ContactData billContact = info.getBillContactData();
        billContact.setAddressCity(request.getBillAddressCity());
        billContact.setAddressCountrycode(request.getBillAddressCountrycode());
        billContact.setAddressLine1(request.getBillAddressLine1());
        billContact.setAddressLine2(request.getBillAddressLine2());
        billContact.setAddressLine3(request.getBillAddressLine3());
        billContact.setAddressPostalcode(request.getBillAddressPostalcode());
        billContact.setAddressStateprovince(request.getBillAddressStateprovince());
        billContact.setEmail(request.getBillEmail());
        billContact.setFamilyName(request.getBillFamilyName());
        billContact.setFax(request.getBillFax());
        billContact.setGivenName(request.getBillGivenName());
        billContact.setOrganisation(request.getBillOrganisation());
        billContact.setPhone(request.getBillPhone());

        ContactInfo.ContactData registrantContact = info.getRegistrantContactData();
        registrantContact.setAddressCity(request.getRegistrantAddressCity());
        registrantContact.setAddressCountrycode(request.getRegistrantAddressCountrycode());
        registrantContact.setAddressLine1(request.getRegistrantAddressLine1());
        registrantContact.setAddressLine2(request.getRegistrantAddressLine2());
        registrantContact.setAddressLine3(request.getRegistrantAddressLine3());
        registrantContact.setAddressPostalcode(request.getRegistrantAddressPostalcode());
        registrantContact.setAddressStateprovince(request.getRegistrantAddressStateprovince());
        registrantContact.setEmail(request.getRegistrantEmail());
        registrantContact.setFax(request.getRegistrantFax());
        registrantContact.setOrganisation(request.getRegistrantOrganisation());
        registrantContact.setPhone(request.getRegistrantPhone());
        
        ContactInfo.ContactData techContact = info.getTechContactData();
        techContact.setAddressCity(request.getTechAddressCity());
        techContact.setAddressCountrycode(request.getTechAddressCountrycode());
        techContact.setAddressLine1(request.getTechAddressLine1());
        techContact.setAddressLine2(request.getTechAddressLine2());
        techContact.setAddressLine3(request.getTechAddressLine3());
        techContact.setAddressPostalcode(request.getTechAddressPostalcode());
        techContact.setAddressStateprovince(request.getTechAddressStateprovince());
        techContact.setEmail(request.getTechEmail());
        techContact.setFamilyName(request.getTechFamilyName());
        techContact.setFax(request.getTechFax());
        techContact.setGivenName(request.getTechGivenName());
        techContact.setOrganisation(request.getTechOrganisation());
        techContact.setPhone(request.getTechPhone());

        return info;
    }

}

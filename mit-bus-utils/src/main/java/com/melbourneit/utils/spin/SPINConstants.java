package com.melbourneit.utils.spin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SPINConstants
{
    public static final class GenericApi
    {
        public static final int SUCCESS_RESULT_CODE = 1000;
        public static final int SUCCESS_PENDING_RESULT_CODE = 1001;
        
        public static final String SUCCESS_PENDING_RESULT_STRING = "Pending";
        
        public static final class Actions
        {
            // the following action names are to be used when calling spin
            // functions
            public static final String CHECK_DOMAIN = "checkDomain";
            public static final String CHECK_MULTIPLE_DOMAINS =
                "checkdomainnames";
            public static final String GET_RENEWAL_INFO = "getrenewalinfo";
            public static final String UPDATE_LOCK_STATUS =
                "updatedomainlockstatus";
            public static final String DELEGATE_DOMAIN = "delegateDomain";
            public static final String UPDATE_CONTACTS = "contactUpdate";
            public static final String REGISTER_DOMAIN = "registerdomain";
            public static final String GET_DOMAIN_DETAILS = "getDomainDetails";
            public static final String GET_WHOIS_DETAILS = "getwhoisdetails";
            public static final String GET_RESELLER_REPORTS = "getresellerreports";
            public static final String GET_RESELLER_ENABLED_DOMAIN_SPACES = "getresellerenableddomainspaces";
            public static final String ASSIGN_DOMAIN_TO_NEW_RESELLER = "assigndomaintonewreseller";
            public static final String CHANGE_OWNERSHIP = "changeownership";
            public static final String POLL_TRANSACTION_STATUS = "polltransactionstatus";
            public static final String RESET_DOMAIN_AUTH_INFO = "resetdomainauthinfo";
            public static final String GET_DOMAIN_AUTH_INFO = "getdomainauthinfo";
            public static final String UPDATE_DOMAIN_AUTO_RENEW = "updatedomainautorenewflag";
            public static final String UPDATE_PRIVATE_REGISTRATION = "updateprivateregistration";
            public static final String GET_DOMAIN_LOCK_STATUS = "getdomainlockstatus";
            public static final String MAKE_DOMAIN_DIRECT = "makedomaindirect";
            public static final String GET_DOMAIN_AUTO_RENEW = "getdomainautorenewflag";
            public static final String GET_PRIVATE_REG_STATUS = "getprivateregstatus";
            public static final String VALIDATE_PARTNER = "validatepartner";
            public static final String GET_DOMAIN_TRANSFER_STATUS = "transferdomainstatus";
        }

        public static final class FieldValues
        {
            public static final String API_TYPE_GENERIC = "generic";
            public static final String FR_NAME_HOLDER_TYPE_CORPORATE= "corporate";
            public static final String FR_NAME_HOLDER_TYPE_INDIVIDUAL = "individual";
        }

        public static final class FieldNames
        {
            // the following represent the spin field names required when
            // sending requests
            public static final String USERNAME = "username";
            public static final String PASSWORD = "password";
            public static final String API_TYPE = "apitype";
            public static final String CLIENT_TRANS_ID = "CLTRID";
            public static final String ENTERPRISE_BUS_LOGID = "enterpriseBusLogId";

            public static final String SIEBEL_ASSET_INTEGRATION_ID = "siebel.assetintegrationid";
            public static final String ACTION_NAME = "actionName";
            public static final String DOMAIN_NAME = "domain.name";
            public static final String DOMAIN_NAMES = "domain.names";
            public static final String DOMAIN_REGISTRY_KEY = "domain.key";
            public static final String DOMAIN_AUTH_INFO = "domain.authinfo";
            public static final String DOMAIN_AUTO_RENEW = "domain.autorenewflag";
            public static final String DOMAIN_PERIOD = "domain.period";
            public static final String DOMAIN_LOCK_STATUS = "domain.lockstatus";
            public static final String DOMAIN_NAME_SERVER = "domain.nameserver";
            public static final String MAX_EXEC_WAIT_SECONDS =
                "maxExecWaitSeconds";
            public static final String MULTI_CHECK_LIST_RESULTS = "check.list.results";
            public static final String MULTI_CHECK_LIST_RESULTS_SIZE = "check.list.size";
            public static final String TRANSACTION_ID = "transaction.id";
            
            
            //au specific parameters
            public static final String AU_POLICY_REASON = "au.policyreason";
            public static final String AU_AUTO_REG_FLAG = "autoregflag";
            public static final String AU_REGISTRANT_ID_TYPE = "au.registrantidtype";
            public static final String AU_REGISTRANT_ID_NUMBER = "au.registrantidnumber";
            public static final String AU_ELIGIBILITY_TYPE = "au.eligibilitytype";
            public static final String AU_ELIGIBILITY_ID_TYPE = "au.eligibilityidtype";
            public static final String AU_ELIGIBILITY_NAME = "au.eligibilityname";
            public static final String AU_ELIGIBILITY_ID_NUMBER = "au.eligibilityidnumber";
            public static final String AU_WARRANT = "au.warrant";
            //the next one is used for the au registrant contact only
            public static final String AU_REGISTRANT_CONTACT_NAME = "au.registrantcontactname";

            //uk specific parameters
            public static final String UK_REGISTRANT_TYPE = "uk.regtype";
            public static final String UK_REGISTRANT_NUMBER = "uk.regnumber";
            
            //travel specific parameters
            public static final String TRAVEL_UNIQUE_ID_NUMBER = "travel.uniqueidnumber";
            
            //name specific parameters 
            public static final String NAME_PRODUCT_TYPE = "name.producttype";
            public static final String NAME_EMAIL_FORWARD_ADDRESS = "name.emailforwardingaddress";
            
            //fr specific parameters
            public static final String FR_NAME_HOLDER_TYPE = "fr.nameholdertype";
            public static final String FR_NAME_HOLDER_NAME = "fr.nameholdername";
            public static final String FR_BIRTH_DATE = "fr.birthdate";
            public static final String FR_BIRTH_PLACE = "fr.birthplace";
            //the following apply to Corporate Entities
            public static final String FR_LEGAL_STATUS = "fr.legalstatus";
            public static final String FR_LEGAL_STATUS_DESC = "fr.legalstatusdesc";
            public static final String FR_SIREN = "fr.siren";
            public static final String FR_TRADE_MARK = "fr.trademark";
            public static final String FR_VAT = "fr.vat";
            public static final String FR_DUNS = "fr.duns";
            public static final String FR_LOCAL = "fr.local";
            public static final String FR_WALDEC = "fr.waldec";
            public static final String FR_ASSOCIATION_DECLARATION_DATE = "fr.assocdecldate";
            public static final String FR_ASSOCIATION_PUBLICATION_DATE = "fr.assocpubldate";
            public static final String FR_ASSOCIATION_PUBLICATION_ANNOUNCE_NUMBER = "fr.assocpublannouncenumber";
            public static final String FR_ASSOCIATION_PUBLICATION_PAGE_NUMBER = "fr.assocpublpagenumber";
            
            //us specific parameters
            public static final String US_NEXUS_APP_PURPOSE = "us.nexusapppurpose";
            public static final String US_NEXUS_CATEGORY = "us.nexuscategory";
            public static final String US_NEXUS_NAME_SERVER_CERTIFICATION = "us.nexusnameservercert";
            
            
            //asia specific parameters
            public static final String ASIA_CONTACT_TYPE = "asia.contacttype";
            public static final String ASIA_LEGAL_ENTITY_TYPE = "asia.legalentitytype";
            public static final String ASIA_OTHER_LEGAL_ENTITY_TYPE = "asia.otherlettype";
            public static final String ASIA_IDENT_FORM = "asia.identform";
            public static final String ASIA_OTHER_IDENT_FORM = "asia.otheridentform";
            public static final String ASIA_IDENTIFICATION_NUMBER = "asia.identnumber";
            public static final String ASIA_LOCALITY_COUNTRY_CODE = "asia.localityCC";
            public static final String ASIA_LOCALITY_SP = "asia.localitysp";
            public static final String ASIA_LOCALITY_CITY = "asia.localitycity";
            
            //private registration data
            public static final String PRIVATE_REG_DOMAIN_FLAG = "domain.privateregistrationflag";
            public static final String PRIVATE_REG_REGISTRANT_CONTACT = "registrant.privatecontact";
            public static final String PRIVATE_REG_ADMIN_CONTACT = "admin.privatecontact";
            public static final String PRIVATE_REG_BILL_CONTACT = "bill.privatecontact";
            public static final String PRIVATE_REG_TECH_CONTACT = "tech.privatecontact";
            
            //contact related data
            public static final String ADMIN_CONTACT_TYPE = "admin";
            public static final String BILL_CONTACT_TYPE = "bill";
            public static final String TECH_CONTACT_TYPE = "tech";
            public static final String REGISTRANT_CONTACT_TYPE = "registrant";
            
            public static final class Contact
            {
                public static final class Type
                {
                    public static final String ADMIN = "admin";
                    public static final String BILL = "bill";
                    public static final String TECH = "tech";
                    public static final String REGISTRANT = "registrant";
                }
                
                public static final String GIVEN_NAME = "givenName";
                public static final String FAMILY_NAME = "familyName";
                public static final String ORGANISATION = "organisation";
                public static final String EMAIL = "email";
                public static final String PHONE = "phone";
                public static final String FAX = "fax";
                public static final String VAT = "vat";
                public static final String LANGUAGE = "language";
                public static final String ORG_NUMBER = "orgnumber";
                public static final String CONTACT_TYPE = "contactType";
                public static final String ADDRESS_LINE1 = "address.line1";
                public static final String ADDRESS_LINE2 = "address.line2";
                public static final String ADDRESS_LINE3 = "address.line3";
                public static final String ADDRESS_CITY = "address.city";
                public static final String ADDRESS_STATE_PROVINCE = "address.stateprovince";
                public static final String ADDRESS_POSTALCODE = "address.postalcode";
                public static final String ADDRESS_COUNTRY_CODE = "address.countrycode";
                
                //the "NAME" field is used for the registrant's contact ONLY
                public static final String NAME = "name";
            }
        }

        public static final class Errors
        {
            public static final String CONNECT_EXCEPTION = "3";
            public static final String CONNECT_IO_EXCEPTION = "4";
            public static final String GENERIC_EXCEPTION = "9";
            public static final String SYSTEM_FAILURE = "2004";
            public static final String AUTHENTICATION_FAILURE = "2002";
            public static final String FIELDS_MISSING_FAILURE = "2000";
            public static final String SYSTEM_ERROR = "3000";

            private static final String DEFAULT_DATE_FORMAT_STR =
                "yyyy-MM-dd'T'HH:mm:ssZ";

            public static String buildErrorString(String actionName,
                    String errorCode, String clTrid)
            {
                return buildErrorString(actionName, errorCode, null, clTrid);
            }

            public static String buildErrorString(String actionName,
                    String errorCode, String actionErrors, String clTrid)
            {
                SimpleDateFormat format =
                    new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);
                String currentDateStr = format.format(new Date());

                if (actionErrors == null)
                {
                    return "Error | " + SYSTEM_ERROR + " | " + actionName + " | " + currentDateStr
                        + " | System error | errorCode=" + errorCode
                        + "; CLTRID=" + clTrid;
                }
                else
                {
                    return "Error | " + SYSTEM_ERROR + " | " + actionName + " | " + currentDateStr
                    + " | System error | errorCode=" + errorCode + "; actionErrors[" + actionErrors
                    + "]; CLTRID=" + clTrid;
                }
            }

            public static String buildUnsupportedFunctionFailureString(String actionName,
                    String domainName, String clTrid)
            {
                SimpleDateFormat format =
                    new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);
                String currentDateStr = format.format(new Date());

                return "Failure | " + SYSTEM_FAILURE + " | " + actionName + " | " + currentDateStr
                        + " | Request is unsupported | domain.name=" + domainName 
                        + "; CLTRID=" + clTrid;
            }
            
            public static String buildAuthenticationFailureMessage(String actionName, String clTrid)
            {
                SimpleDateFormat format =
                    new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);
                String currentDateStr = format.format(new Date());

                return "Failure | " + AUTHENTICATION_FAILURE + " | " + actionName + " | " + currentDateStr
                        + " | Authentication error | actionErrors=[Invalid user credentials]; CLTRID=" + clTrid;
                
            }

            public static String buildMissingInvalidFieldsFailureMessage(String actionName, String clTrid, List<String> missingFields)
            {
                SimpleDateFormat format =
                    new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);
                String currentDateStr = format.format(new Date());

                StringBuffer strBuff= new StringBuffer();
                
                strBuff.append("Failure | " + FIELDS_MISSING_FAILURE + " | " + actionName + " | " + currentDateStr
                        + " | Field error | fieldsErrors={");
                
                for (String fieldName : missingFields)
                {
                    strBuff.append(fieldName).append("=[missing/invalid field], ");
                }
                strBuff.append("}; CLTRID=").append(clTrid);
                
                return strBuff.toString();
            }

            public static String buildAuthenticationFailureMessage(String clTrid)
            {
                SimpleDateFormat format =
                    new SimpleDateFormat(DEFAULT_DATE_FORMAT_STR);
                String currentDateStr = format.format(new Date());

//                return "Failure | " + AUTHENTICATION_FAILURE + " | " + actionName + " | " + currentDateStr
//                        + " | Authentication error | actionErrors=[Invalid user credentials]; CLTRID=" + clTrid;
                
                return "This needs to be handled";
                
            }
        }
    }
}

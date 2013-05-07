package com.melbourneit.utils.dns;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.melbourneit.utils.xml.XMLParserUtil;

public class DnsUtils
{
    protected static final Logger Log = LoggerFactory.getLogger(DnsUtils.class);
    
    public static void addRecordToMap(HashMap<String,String> resultMap, int recordNumber, 
            String name, String type, String ttl, String address, String priority, String logId)
    {
        //time to build the resultHashMap with the ResourceRecord data
        resultMap.put(DnsConstants.Keys.DNS_NAME + "." + recordNumber, name);
        resultMap.put(DnsConstants.Keys.DNS_TYPE + "." + recordNumber, type);
        resultMap.put(DnsConstants.Keys.DNS_TTL + "." + recordNumber, ttl);
        resultMap.put(DnsConstants.Keys.DNS_ADDRESS  + "." + recordNumber, address);
        resultMap.put(DnsConstants.Keys.DNS_PRIORITY + "." + recordNumber, priority);
    }
    
    public static void addRecordToMap(HashMap<String,String> resultMap, int recordNumber, 
            String name, String type, String ttl, String address, String logId)
    {
        //time to build the resultHashMap with the ResourceRecord data
        resultMap.put(DnsConstants.Keys.DNS_NAME + "." + recordNumber, name);
        resultMap.put(DnsConstants.Keys.DNS_TYPE + "." + recordNumber, type);
        resultMap.put(DnsConstants.Keys.DNS_TTL + "." + recordNumber, ttl);

        if ("MX".equalsIgnoreCase(type))
        {
            String[] paramsArray = address.split(" ");
            if (paramsArray.length == 2)
            {
                //the format for the address for MX records is: "PRIORITY ADDRESS_NAME", priority is element[0], address is element[1]
                //eg "5 aspmx.l.google.com.", priority=5, address=aspmx.l.google.com.
                resultMap.put(DnsConstants.Keys.DNS_PRIORITY + "." + recordNumber, paramsArray[0]);
                resultMap.put(DnsConstants.Keys.DNS_ADDRESS  + "." + recordNumber, paramsArray[1]);
            }
            else if (paramsArray.length == 1)
            {
                resultMap.put(DnsConstants.Keys.DNS_ADDRESS  + "." + recordNumber, address);
            }
            else
            {
                Log.info(logId + " Error on MX records address.  AddressArray.length: " + paramsArray.length + " address[" + address + "]");
            }
        }
        else
        {
            resultMap.put(DnsConstants.Keys.DNS_ADDRESS  + "." + recordNumber, address);
        }
    }
    
    public static void setDnsRecordCountToMap(HashMap<String,String> resultMap, int recordsCount, String logId)
    {
        resultMap.put(DnsConstants.Keys.DNS_RECORDS_COUNT, "" + recordsCount);
    }
    
    public static List<RRSetVO> readResourceRecordVOFromMap(HashMap<String,String> map, String logId)
    {
        ArrayList<RRSetVO> resultList = new ArrayList<DnsUtils.RRSetVO>();
        
        //read the number of records 
        String recordCountStr = (String)map.get(DnsConstants.Keys.DNS_RECORDS_COUNT);
        Log.info(logId + " Total number of resourceRecords is: " + recordCountStr);
        int recordCount = Integer.parseInt(recordCountStr);
        for (int i=0; i<recordCount; ++i)
        {
            RRSetVO rrsetVo = new RRSetVO();
            rrsetVo.name = (String)map.get(DnsConstants.Keys.DNS_NAME+ "." + i);
            rrsetVo.type = (String)map.get(DnsConstants.Keys.DNS_TYPE + "." + i);
            rrsetVo.ttl = (String)map.get(DnsConstants.Keys.DNS_TTL + "." + i);
            rrsetVo.address = (String)map.get(DnsConstants.Keys.DNS_ADDRESS  + "." + i);
            rrsetVo.priority = (String)map.get(DnsConstants.Keys.DNS_PRIORITY + "." + i);
            Log.info(logId + " Record[" + i + "] is: " + rrsetVo);
            resultList.add(rrsetVo);
        }
        
        return resultList;
    }
    
    public static List<String> convertResourceRecordVOListtToXmlList(String domainName, 
            List<RRSetVO> resourceRecordVoList, String logId) 
        throws Exception
    {
        List<String> resultList = new ArrayList<String>(resourceRecordVoList.size());
        
        for (RRSetVO rrVo : resourceRecordVoList)
        {
            resultList.add(generateXmlStringForRRSetVO(rrVo, domainName));
        }
        
        return resultList;
    }
    
    public static String generateXmlStringForRRSetVO(RRSetVO rrVo, String domainName)
    {
        StringBuffer buff = new StringBuffer(512);
        String origin = domainName.endsWith(".") ? domainName : (domainName + ".");
        String type = rrVo.type.trim().toUpperCase();
        buff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");
        buff.append("<").append(type).append(" origin=\"").append(origin).append("\"");
        buff.append(" xmlns=\"http://dns.melbourneit.com\"");
        buff.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
        buff.append(" xsi:schemaLocation=\"http://dns.melbourneit.com rr.xsd\">\n");
        buff.append("<type>").append(type).append("</type>\n");
        if (domainName.equalsIgnoreCase(rrVo.name) && !rrVo.name.endsWith("."))
        {
            buff.append("<name>").append(rrVo.name).append(".</name>\n");
        }
        else
        {
            buff.append("<name>").append(rrVo.name).append("</name>\n");
        }
        buff.append("<ttl>").append(rrVo.ttl).append("</ttl>\n");
        if ("MX".equals(type))
        {
            buff.append("<priority>").append(rrVo.priority).append("</priority>\n");
        }
        
        if (type.startsWith("A") || type.equals("TXT"))
        {
            buff.append("<address>").append(rrVo.address).append("</address>\n");
        }
        else if (!rrVo.address.endsWith("."))
        {
            buff.append("<address>").append(rrVo.address).append(".</address>\n");
        }
        else
        {
            buff.append("<address>").append(rrVo.address).append("</address>\n");
        }
        
        
        buff.append("</").append(type).append(">\n");
        return buff.toString();
    }
    
    public static class RRSetVO
    {
        public String name;
        public String type;
        public String ttl;
        public String address;
        public String priority;
        
        public String toString()
        {
            return "RRSetVO[name=" + name + " type=" + type + " ttl=" + ttl + " priority=" + priority + " address=" + address + "]";
        }
    }
}

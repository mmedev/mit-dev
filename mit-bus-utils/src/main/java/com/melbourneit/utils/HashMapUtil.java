package com.melbourneit.utils;

import java.util.HashMap;
import java.util.Set;

public class HashMapUtil
{
    private static String COUNT = "count";
    private static String KEY = "key";
    private static String VALUE = "value";
    
    public static void setHashMapProperties(String mapName, HashMap<String,String> fromMap, HashMap<String,String> toMap)
    {
        int fromMapEntriesCount = (fromMap == null) ? 0 : fromMap.size();
        
        toMap.put(mapName + "." + COUNT, "" + fromMapEntriesCount);
        int currentIndex = 0;
        for (String currentKey : fromMap.keySet())
        {
            toMap.put(mapName + "." + KEY + "." + currentIndex, currentKey);
            toMap.put(mapName + "." + VALUE + "." + currentIndex++, fromMap.get(currentKey));
        }
    }

    public static HashMap<String,String> getHashMapProperties(String mapName, HashMap<String,String> fromMap)
    {
        HashMap<String,String> resultMap = new HashMap<String, String>();
        
        //first find out how many entries there are in the list
        String entriesCountStr = fromMap.get(mapName + "." + COUNT);
        int entriesCount = (entriesCountStr == null) ? 0 : Integer.parseInt(entriesCountStr);
        
        for (int i = 0; i < entriesCount; ++i)
        {
            String key = fromMap.get(mapName + "." + KEY + "." + i);
            String value = fromMap.get(mapName + "." + VALUE + "." + i);
            
            //now add the key values to the map
            resultMap.put(key, value);
        }
        
        return resultMap;
    }
}

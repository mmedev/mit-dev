package com.melbourneit.utils.stats;

import java.util.Enumeration;
import java.util.Hashtable;

public class FunctionStatsContainer
{
    private String name;
    private Hashtable<String,FunctionStats> functionNameStatsHash = new Hashtable<String,FunctionStats>();
    private static Hashtable<String,FunctionStatsContainer> functionStatsContainerHash =
        new Hashtable<String,FunctionStatsContainer>();
    
    private FunctionStatsContainer(String name)
    {
        this.name = name;
    }
    
    public static FunctionStatsContainer getInstance(String name)
    {
        FunctionStatsContainer result = null;
        
        synchronized(functionStatsContainerHash)
        {
            result = functionStatsContainerHash.get(name.toLowerCase());
            if (result == null)
            {
                result = new FunctionStatsContainer(name);
                functionStatsContainerHash.put(name.toLowerCase(), result);
            }
        }
        
        return result;
    }

    public static String getAllFunctionsCurrentStats(String title)
    {
        StringBuilder strBuilder = new StringBuilder(4096);
        
        buildAllFunctionsCurrentStats(title, strBuilder);
        
        return strBuilder.toString();
    }

    public static void buildAllFunctionsCurrentStats(String title, StringBuilder strBuilder)
    {
        strBuilder.append(title).append("A total of: ").append(functionStatsContainerHash.size()).append(" FunctionStats individual containers\n");
        Enumeration<FunctionStatsContainer> functionStatsEnum = functionStatsContainerHash.elements();
        int count = 0;
        while (functionStatsEnum.hasMoreElements())
        {
            FunctionStatsContainer functionStats = functionStatsEnum.nextElement();
            functionStats.buildFunctionsCurrentStats(title + " FunctionStatsContainer[" + count++ + "] ", strBuilder);
            strBuilder.append("\n");
        }
    }
    
    public FunctionStats getFunctionStats(String functionName)
    {
        FunctionStats result = null;
        String functionNameLower = functionName.toLowerCase();
        
        synchronized (functionNameStatsHash)
        {
            result = functionNameStatsHash.get(functionNameLower);
            if (result == null)
            {
                result = new FunctionStats(functionName);
                functionNameStatsHash.put(functionNameLower, result);
            }
        }
        
        return result;
    }
    
    public FunctionStats removeFunctionStats(String functionName)
    {
        return functionNameStatsHash.remove(functionName.toLowerCase());
    }
    
    public int getFunctionStatsCount()
    {
        return functionNameStatsHash.size();
    }

    public String getFunctionsCurrentStats(String title)
    {
        StringBuilder strBuilder = new StringBuilder(256*(functionNameStatsHash.size()+1));
        
        buildFunctionsCurrentStats(title, strBuilder);
        
        return strBuilder.toString();
    }
    
    public void buildFunctionsCurrentStats(String title, StringBuilder strBuilder)
    {
        strBuilder.append(title).append("Stats from FunctionStatsContainer:").append(name).append("\n");
        Enumeration<String> keys = functionNameStatsHash.keys();
        while (keys.hasMoreElements())
        {
            strBuilder.append(functionNameStatsHash.get(keys.nextElement())).append("\n");
        }
    }
}

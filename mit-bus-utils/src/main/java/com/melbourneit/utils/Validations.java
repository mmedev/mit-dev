package com.melbourneit.utils;

public class Validationss
{
    public static boolean isValidInteger(String intString)
    {
        boolean isValid = true;
        try
        {
            Integer.valueOf(intString);
        }
        catch (NumberFormatException e)
        {
            isValid = false;
        }

       return isValid;
    }

    public static boolean isValidDouble(String intString)
    {
        boolean isValid = true;
        try
        {
            Double.valueOf(intString);
        }
        catch (NumberFormatException e)
        {
            isValid = false;
        }

       return isValid;
    }
}

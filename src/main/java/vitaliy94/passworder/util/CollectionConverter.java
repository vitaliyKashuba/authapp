package vitaliy94.passworder.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap used to avoid username duplicates
 * this class is converter between list of strings key[separator]value and hashMap
 */
public class CollectionConverter
{
    private static final String SEPARATOR = "__";

    public static Map<String, String> convertListToMap(List<String> list)
    {
        HashMap<String, String> users = new HashMap<>();
        for(String s : list)
        {
            String[] splittedStr = s.split(SEPARATOR);
            users.put(splittedStr[0], splittedStr[1]);
        }
        return users;
    }

    public static List<String> convertMapToList(Map<String,String> users)
    {
        ArrayList<String> lines = new ArrayList<>();
        for(String user : users.keySet())
        {
            lines.add(user + SEPARATOR + users.get(user));
        }
        return lines;
    }
}

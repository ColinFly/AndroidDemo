package com.kuaima;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by colin on 17-6-8.
 */

public class ParamUtil {
    /**
     * 得到参数列表字符串
     * @param method   请求类型 get or  post
     * @param paramValues 参数map对象
     * @return  参数列表字符串
     */
    public static String getParams(String method,Map<String, String> paramValues)
    {
        String params="";
        Set<String> key = paramValues.keySet();
        String beginLetter="";
        if (method.equalsIgnoreCase("get"))
        {
            beginLetter="?";
        }

        for (Iterator<String> it = key.iterator(); it.hasNext();) {
            String s = (String) it.next();
            if (params.equals(""))
            {
                params += beginLetter + s + "=" + paramValues.get(s);
            }
            else
            {
                params += "&" + s + "=" + paramValues.get(s);
            }
        }
        return params;
    }


    /**
     * 按照key排序得到参数列表字符串
     * @param method   请求类型 get or  post
     * @param paramValues 参数map对象
     * @return  参数列表字符串
     */
    public static String getParamsOrderByKey(String method,Map<String, String> paramValues)
    {
        String params="";
        Set<String> key = paramValues.keySet();
        String beginLetter="";
        if (method.equalsIgnoreCase("get"))
        {
            beginLetter="?";
        }
        List<String> paramNames = new ArrayList<String>(paramValues.size());
        paramNames.addAll(paramValues.keySet());
        Collections.sort(paramNames);
        for (String paramName : paramNames) {

            if (params.equals(""))
            {
                params += beginLetter + paramName + "=" + paramValues.get(paramName);
            }
            else
            {
                params += "&" + paramName + "=" + paramValues.get(paramName);
            }
        }

        return params;
    }


}

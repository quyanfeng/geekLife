package com.jr.juhe.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import org.codehaus.jackson.map.ObjectMapper;

public class ObjectJsonUtil
{
    public static String objToJson(Object obj)
            throws IOException
    {
        if (obj == null) {
            return "";
        }
        ObjectMapper mapper = new ObjectMapper();
        Writer strWriter = new StringWriter();
        mapper.writeValue(strWriter, obj);
        return strWriter.toString();
    }

    public static <T> T jsonToObj(String dataJsonStr, Class<T> clazz)
            throws IOException
    {
        if ((dataJsonStr == null) || (dataJsonStr.isEmpty())) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return (T)mapper.readValue(dataJsonStr, clazz);
    }
}

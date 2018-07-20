package com.dong.buddy.util;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonFormatter
{

    private String prefixChar = "\t";
    private String wrap = "\r\n";

    public JsonFormatter()
    {
        prefixChar = "\t";
    }

    public JsonFormatter(String prefixChar)
    {
        this.prefixChar = prefixChar;
    }

    /**
     * 执行格式化
     * 
     * @param jsonObj
     * @return
     * @throws JSONException
     */
    public String to(JSONObject jsonObj) throws JSONException
    {
        StringBuilder sb = new StringBuilder();
        if (jsonObj != null && jsonObj.length() > 0)
        {
            to(sb, jsonObj, 1);
        }
        return sb.toString();
    }

    /**
     * @param sb
     * @param jsonObj
     * @param level
     * @throws JSONException
     */
    @SuppressWarnings("unchecked")
    private void to(StringBuilder sb, JSONObject jsonObj, int level) throws JSONException
    {
        sb.append("{");
        sb.append(wrap);
        int length = jsonObj.length();
        Iterator<String> keyIter = jsonObj.keys();
        int i = 0;
        while (keyIter.hasNext())
        {
            String key = keyIter.next();
            Object value = jsonObj.get(key);
            if (value == null)
            {
                continue;
            }
            for (int j = 0; j < level; j++)
            {
                sb.append(prefixChar);
            }
            if (value instanceof Boolean)
            {
                boolean boolv = (boolean) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                sb.append(boolv);
            }
            else if (value instanceof Double)
            {
                double dv = (double) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                sb.append(dv);
            }
            else if (value instanceof Integer)
            {
                int iv = (int) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                sb.append(iv);
            }
            else if (value instanceof Long)
            {
                long lv = (long) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                sb.append(lv);
            }
            else if (value instanceof String)
            {
                String strv = (String) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                sb.append("\"" + strv + "\"");
            }
            else if (value instanceof JSONObject)
            {
                JSONObject objv = (JSONObject) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                to(sb, objv, level + 1);
            }
            else if (value instanceof JSONArray)
            {
                JSONArray array = (JSONArray) value;
                sb.append("\"" + key + "\"");
                sb.append(" : ");
                to(sb, array, level + 1);
            }
            if (i == length - 1)
            {
                sb.append(wrap);
            }
            else
            {
                sb.append(" ,");
                sb.append(wrap);
            }
            i++;
        }
        for (int j = 0; j < level - 1; j++)
        {
            sb.append(prefixChar);
        }
        sb.append("}");
    }

    /**
     * @param sb
     * @param array
     * @param level
     * @throws JSONException
     */
    private void to(StringBuilder sb, JSONArray array, int level) throws JSONException
    {
        if (array == null || array.length() <= 0)
        {
            sb.append("[ ]");
            return;
        }
        sb.append(" [");
        sb.append(wrap);
        for (int i = 0; i < array.length(); i++)
        {
            Object value = array.get(i);
            if (value == null)
            {
                continue;
            }
            for (int j = 0; j < level; j++)
            {
                sb.append(prefixChar);
            }
            if (value instanceof Boolean)
            {
                boolean boolv = (boolean) value;
                sb.append(boolv);
            }
            else if (value instanceof Double)
            {
                double dv = (double) value;
                sb.append(dv);
            }
            else if (value instanceof Integer)
            {
                int iv = (int) value;
                sb.append(iv);
            }
            else if (value instanceof Long)
            {
                long lv = (long) value;
                sb.append(lv);
            }
            else if (value instanceof String)
            {
                String strv = (String) value;
                sb.append("\"" + strv + "\"");
            }
            else if (value instanceof JSONObject)
            {
                JSONObject objv = (JSONObject) value;
                to(sb, objv, level + 1);
            }
            else if (value instanceof JSONArray)
            {
                JSONArray arrayv = (JSONArray) value;
                to(sb, arrayv, level + 1);
            }
            if (i == array.length() - 1)
            {
                sb.append(wrap);
            }
            else
            {
                sb.append(" ,");
                sb.append(wrap);
            }
        }
        for (int j = 0; j < level - 1; j++)
        {
            sb.append(prefixChar);
        }
        sb.append("]");
    }

    public static void main(String[] args) throws JSONException
    {
        JsonFormatter jm = new JsonFormatter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("sdfdsf1", "cxvxv");
        jsonObject.put("sdfdsf2", "cxvxv");
        jsonObject.put("sdfdsf3", "cxvxv");
        jsonObject.put("sdfdsf4", "cxvxv");
        jsonObject.put("sdfdsf5", "cxvxv");
        jsonObject.put("sdfdsf57", "cxvxv");
        jsonObject.put("sdfdsf51", "cxvxv");
        jsonObject.put("sdfdsf52", "cxvxv");
        jsonObject.put("sdfdsf53", "cxvxv");
        jsonObject.put("sdfdsf54", "cxvxv");
        jsonObject.put("sdfdsf55", "cxvxv");
        jsonObject.put("sdfdsf56", "cxvxv");

        System.out.println(jm.to(jsonObject));
        ;
    }

}

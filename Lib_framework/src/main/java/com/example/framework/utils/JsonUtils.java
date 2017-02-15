package com.example.framework.utils;

import com.example.framework.BaseConstants.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * function:JsonUtils工具类
 * Email：yangchaozhi@outlook.com
 * @author by vinko on 2017/2/7
 */
public class JsonUtils {

    private static boolean isPrintException = Constants.IS_DEBUG;//日志开关
    /**
     * get Long from jsonObject
     */
    public static long getLong(JSONObject jsonObject, String key, Long defaultValue) {
        if (jsonObject == null || "".equals(key) || key == null) {
            return defaultValue;
        }
        try {
            return jsonObject.getLong(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Long from jsonData
     */
    public static long getLong(String jsonData, String key, Long defaultValue) {
        if ("".equals(jsonData) || jsonData == null) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getLong(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Int from jsonObject
     */
    public static int getInt(JSONObject jsonObject, String key, Integer defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getInt(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Int from jsonData
     */
    public static int getInt(String jsonData, String key, Integer defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getInt(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Double from jsonObject
     */
    public static double getDouble(JSONObject jsonObject, String key, Double defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }
        try {
            return jsonObject.getDouble(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Double from jsonData
     */
    public static double getDouble(String jsonData, String key, Double defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getDouble(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get String from jsonObject
     */
    public static String getString(JSONObject jsonObject, String key, String defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getString(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static String getString(JSONObject jsonObject, String key) {
        return getString(jsonObject, key, "");
    }

    /**
     * get String from jsonData
     */
    public static String getString(String jsonData, String key, String defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getString(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get String array from jsonObject
     */
    public static String[] getStringArray(JSONObject jsonObject, String key, String[] defaultValue) {
        if (jsonObject == null ||  key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            JSONArray statusArray = jsonObject.getJSONArray(key);
            if (statusArray != null) {
                String[] value = new String[statusArray.length()];
                for (int i = 0; i < statusArray.length(); i++) {
                    value[i] = statusArray.getString(i);
                }
                return value;
            }
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
        return defaultValue;
    }

    /**
     * get String array from jsonData
     */
    public static String[] getStringArray(String jsonData, String key, String[] defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getStringArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONObject from jsonObject
     */
    public static JSONObject getJSONObject(JSONObject jsonObject, String key, JSONObject defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONObject(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    public static JSONObject getJSONObject(JSONObject jsonObject, String key) {
        return getJSONObject(jsonObject, key, null);
    }

    /**
     * get JSONObject from jsonData
     */
    public static JSONObject getJSONObject(String jsonData, String key, JSONObject defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONObject(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * Convert string data to JSONObject
     */
    public static JSONObject getJSONObject(String jsonData) {
        if (jsonData == null || "".equals(jsonData)) {
            return null;
        }

        JSONObject object = null;
        try {
            object = new JSONObject(jsonData);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
        }
        return object;
    }

    /**
     * get JSONArray from jsonObject
     */
    public static JSONArray getJSONArray(JSONObject jsonObject, String key, JSONArray defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getJSONArray(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONArray from jsonData
     */
    public static JSONArray getJSONArray(String jsonData, String key, JSONArray defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getJSONArray(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get JSONArray from a jsonData
     */
    public static JSONArray getJSONArray(String jsonData) {
        if (jsonData == null || "".equals(jsonData)) {
            return null;
        }

        JSONArray array = null;
        try {
            array = new JSONArray(jsonData);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return array;
    }

    public static JSONArray getJSONArray(JSONObject jsonObject, String arrName) {
        JSONArray array = null;
        try {
            array = jsonObject.getJSONArray(arrName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return array;
    }

    /**
     * get Boolean from jsonObject
     */
    public static boolean getBoolean(JSONObject jsonObject, String key, Boolean defaultValue) {
        if (jsonObject == null || key == null || "".equals(key)) {
            return defaultValue;
        }

        try {
            return jsonObject.getBoolean(key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get Boolean from jsonData
     */
    public static boolean getBoolean(String jsonData, String key, Boolean defaultValue) {
        if (jsonData == null || "".equals(jsonData)) {
            return defaultValue;
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getBoolean(jsonObject, key, defaultValue);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return defaultValue;
        }
    }

    /**
     * get map from jsonObject.
     */
    public static Map<String, String> getMap(JSONObject jsonObject, String key) {
        return JsonUtils.parseKeyAndValueToMap(JsonUtils.getString(jsonObject, key, null));
    }

    /**
     * get map from jsonData.
     */
    public static Map<String, String> getMap(String jsonData, String key) {

        if (jsonData == null) {
            return null;
        }
        if (jsonData.length() == 0) {
            return new HashMap<String, String>();
        }

        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            return getMap(jsonObject, key);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * parse key-value pairs to map. ignore empty key, if getValue exception, put empty value
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, String> parseKeyAndValueToMap(JSONObject sourceObj) {
        if (sourceObj == null) {
            return null;
        }

        Map<String, String> keyAndValueMap = new HashMap<String, String>();
        for (Iterator iter = sourceObj.keys(); iter.hasNext();) {
            String key = (String)iter.next();
            putMapNotEmptyKey(keyAndValueMap, key, getString(sourceObj, key, ""));

        }
        return keyAndValueMap;
    }

    /**
     * parse key-value pairs to map. ignore empty key, if getValue exception, put empty value
     *
     * @param source key-value pairs json
     */
    public static Map<String, String> parseKeyAndValueToMap(String source) {
        if (source == null || "".equals(source)) {
            return null;
        }

        try {
            JSONObject jsonObject = new JSONObject(source);
            return parseKeyAndValueToMap(jsonObject);
        } catch (JSONException e) {
            if (isPrintException) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private static Gson gson;
    /**
     * 根据传入的json和数据类型得到解析结果
     *
     * @param content json数据
     * @param clazz 类型
     * @return Json解析结果
     */
    public static <T> T jsonToBean(String content, Class<T> clazz) {

        T result = null;
        //为空直接返回
        if(content == null || "".equals(content))
            return result;

        Gson gson = new Gson();
        try {
            result = gson.fromJson(content, clazz);
        } catch (JsonSyntaxException e) {
            if (isPrintException)
            {
                e.printStackTrace();
            }

        }
        return result;
    }

    /**
     * 将对象直接转成json字符串
     * @param src
     * @return
     */
    public static String toJson(Object src) {
        Gson gson = new Gson();
        return gson.toJson(src);
    }

    /**
     * add key-value pair to map, and key need not null or empty
     *
     * @param map
     * @param key
     * @param value
     * @return <ul>
     *         <li>if map is null, return false</li>
     *         <li>if key is null or empty, return false</li>
     *         <li>return {@link Map#put(Object, Object)}</li>
     *         </ul>
     */
    public static boolean putMapNotEmptyKey(Map<String, String> map, String key, String value) {
        if (map == null || key == null || "".equals(key)) {
            return false;
        }

        map.put(key, value);
        return true;
    }

}

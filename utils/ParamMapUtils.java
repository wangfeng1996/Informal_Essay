package com.sinocarbon.polaris.commons.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class ParamMapUtils {

    /**
     * 从一个参数中得到整数，如果没有值，返回缺省值
     * 
     * @param params
     * @param name
     * @defaultvalue 缺省值
     * @return
     */
    static public Integer getInteger(Map<String, Object> params, String name, Integer defaultvalue) {
        Object obj = params.get(name);
        if (obj != null && !"".equals(obj)) {
            String p = obj.toString();
            if (StringUtils.isNumeric(p)) {
                return Integer.valueOf(p);
            }
        }
        return defaultvalue;
    }

    /**
     * 从一个参数中得到整数，如果没有值，返回null
     * 
     * @param params
     * @param name
     * @return
     */
    static public Integer getInteger(Map<String, Object> params, String name) {
        Object obj = params.get(name);
        if (obj != null && !"".equals(obj)) {
            String p = obj.toString();
            if (StringUtils.isNumeric(p)) {
                return Integer.valueOf(p);
            }
        }
        return null;
    }

    public static int getInt(Map<String, Object> params, String name, int value) {
        Object obj = params.get(name);
        int val = value;
        if (obj != null && !"".equals(obj)) {
            String p = obj.toString();
            if (StringUtils.isNumeric(p)) {
                val = Integer.parseInt(p);
            }
        }
        return val;
    }

    public static int getInt(Map<String, Object> params, String name) {
        return ParamMapUtils.getInt(params, name, 0);
    }

    /**
     * 从一个参数中得到字符串
     * 
     * @param params
     * @param name
     * @defaultvalue
     * @return
     */
    static public String getString(Map<String, Object> params, String name, String defaultvalue) {
        Object obj = params.get(name);
        if (obj != null && !"".equals(obj)) {
            return obj.toString().trim();
        }
        return defaultvalue;
    }

    /**
     * 从一个参数中得到字符串，缺省=""
     * 
     * @param request
     * @param name
     * @return
     */
    static public String getString(Map<String, Object> params, String name) {
        Object obj = params.get(name);
        if (obj != null && !"null".equals(obj) && !"".equals(obj)) {
            return obj.toString().trim();
        }
        return null;
    }

    /**
     * 从一个参数中得到中文字符串，缺省="" gm 2011-4-27
     * 
     * @param request
     * @param name
     * @return
     */
    static public String getGBKString(Map<String, Object> params, String name) {
        try {
            Object obj = params.get(name);
            if (obj != null && !"".equals(obj)) {
                String value = URLDecoder.decode(obj.toString(), "utf-8");
                return value.trim();
            }
        } catch (UnsupportedEncodingException e) {
            return "编码转换错误:" + e.getMessage();
        }
        return "";
    }

    /**
     * 从getParameterMa中得到String gm 2011-4-29
     * 
     * @param map
     * @param name
     * @return
     */
    static public String getMapString(Map<String, Object> map, String name) {
        String newValue = "";
        String[] value = (String[])map.get(name);
        if (value == null || "null".equals(value[0]))
            return "";
        else {
            newValue = String.valueOf(value[0]);
        }
        return newValue;
    }

    /**
     * 解析成Double值
     * 
     * @param objValue
     * @return
     * @throws Exception
     */
    public static Double parseDouble(Object objValue) throws Exception {
        if (StringUtils.isEmpty(objValue.toString().trim())) {
            return null;
        } else {
            return Double.parseDouble(objValue.toString());
        }
    }

    /**
     * Object转BigDecimal类型
     *
     * @param value 要转的object类型
     * @return 转成的BigDecimal类型数据
     */
    public static BigDecimal getBigDecimal(Map<String, Object> map, String name) {
        Object value = map.get(name);
        BigDecimal ret = null;
        if (value != null && !"".equals(value)) {
            if (value instanceof BigDecimal) {
                ret = (BigDecimal)value;
            } else if (value instanceof String) {
                ret = new BigDecimal((String)value);
            } else if (value instanceof BigInteger) {
                ret = new BigDecimal((BigInteger)value);
            } else if (value instanceof Number) {
                ret = BigDecimal.valueOf(((Number)value).doubleValue());
            }
        }
        return ret;
    }
    
	@SuppressWarnings("unchecked")
	static public Object[] getArray(Map<String, Object> params, String name) {
		List<Object> list = (List<Object>) params.get(name);
		if (list != null && list.size() != 0) {
			return list.toArray(new Object[list.size()]);
		}
		return null;
	}


}

package com.util.validator;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 验证�?
 *
 * @author 李小勇
 */
public class ValidatorUtil {

    /**
     * 非空
     *
     * @param obj
     * @return true=非空 false=�?
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 为空
     *
     * @param obj
     * @return true=�?false=非空
     */
    public static boolean isNull(Object obj) {
        if (obj instanceof Long) {
            // Long
            return isNullByLong((Long) obj);
        } else if (obj instanceof Integer) {
            // Integer
            return isNullByInteger((Integer) obj);
        } else if (obj instanceof Float) {
            // Float
            return isNullByFloat((Float) obj);
        } else if (obj instanceof Double) {
            // Double
            return isNullByDouble((Double) obj);
        } else if (obj instanceof String) {
            // String
            return isNullByString((String) obj);
        } else if (obj instanceof String[]) {
            // String[]
            return isNullByStringArry((String[]) obj);
        } else if (obj instanceof List) {
            // List
            return isNullByList((List<?>) obj);
        } else if (obj instanceof Map) {
            // Map
            return isNullByMap((Map<?, ?>) obj);
        } else if (obj instanceof java.util.Date) {
            // java.util.Date
            return isNullByDate((java.util.Date) obj);
        } else if (obj instanceof File) {
            //File
            return isNullByFile((File) obj);
        } else {
            return null == obj;
        }
    }

    /**
     * 为空
     *
     * @param l
     * @return
     */
    private static boolean isNullByLong(Long l) {
        try {
            return null == l || l == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param i
     * @return
     */
    private static boolean isNullByInteger(Integer i) {
        try {
            return null == i || i == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param f
     * @return
     */
    private static boolean isNullByFloat(Float f) {
        try {
            return null == f || f == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param d
     * @return
     */
    private static boolean isNullByDouble(Double d) {
        try {
            return null == d || d == 0;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param s
     * @return
     */
    private static boolean isNullByString(String s) {
        try {
            if (null == s) {
                return true;
            } else {
                s = s.trim();
                // 长度大于零或者等于null
                return 0 >= s.length() || s.equalsIgnoreCase("null");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param str
     * @return
     */
    private static boolean isNullByStringArry(String[] str) {
        try {
            return null == str || 0 >= str.length;
        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param list
     * @return
     */
    public static boolean isNullByList(List<?> list) {
        try {
            return null == list || list.isEmpty() || 0 >= list.size();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param map
     * @return
     */
    public static boolean isNullByMap(Map<?, ?> map) {
        try {
            return null == map || map.isEmpty() || 0 >= map.size();
        } catch (NullPointerException nex) {
            nex.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param date
     * @return
     */
    public static boolean isNullByDate(java.util.Date date) {
        try {
            return null == date;
        } catch (NullPointerException nex) {
            nex.printStackTrace();
            return true;
        }
    }

    /**
     * 为空
     *
     * @param file
     * @return
     */
    public static boolean isNullByFile(final File file) {
        try {
            return null == file || !file.exists() || file.length() == 0;
        } catch (NullPointerException nex) {
            nex.printStackTrace();
            return true;
        }
    }

    /**
     * 比较
     *
     * @param s1
     * @param s2
     * @return true=相同 false=不同
     */
    public static boolean equals(String s1, String s2) {
        if (null == s1) {
            return null == s2;
        }
        return s1.equals(s2);
    }
}
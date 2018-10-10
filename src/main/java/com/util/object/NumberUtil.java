package com.util.object;

import com.util.validator.ValidatorUtil;

import java.text.DecimalFormat;

/**
 * NumberUtil
 *
 * @author 李小勇
 */
public class NumberUtil {

    //浮点格式 1,000.00
    private static String FORMAT_STYLE_1=",###.##";
    //浮点格式 1.00
    @SuppressWarnings("unused")
	private static String FORMAT_STYLE_2="#.##";

    /**
     * 转换为整�?
     *
     * @param number
     * @return
     */
    public static int parseInt(String number) {
        if (ValidatorUtil.isNotNull(number)) {
            if (number
                    .matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                return Integer.parseInt(number);
            }
        }
        return 0;
    }

    /**
     * 转换为长整型
     *
     * @param number
     * @return
     */
    public static long parseLong(String number) {
        if (ValidatorUtil.isNotNull(number)) {
            if (number
                    .matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$")) {
                return Long.parseLong(number);
            }
        }
        return 0;
    }

    /**
     * 格式浮点�?
     *
     * @param number 浮点�?
     * @param format 格式
     * @return
     */
    public static String doubleFormat(double number) {
        try {
            if (ValidatorUtil.isNotNull(number)) {
                DecimalFormat decimalFormat = new DecimalFormat(FORMAT_STYLE_1);
                return decimalFormat.format(number);
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    /**
     * 格式浮点�?
     *
     * @param number 浮点�?
     * @param format 格式
     * @return
     */
    public static String doubleFormat(double number, String format) {
        try {
            if (ValidatorUtil.isNotNull(number)) {
                DecimalFormat decimalFormat = new DecimalFormat(format);
                return decimalFormat.format(number);
            } else {
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }


}

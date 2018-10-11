package com.thsword.utils.object;

import java.util.Random;

/**
 * 获得指定长度的随机字符串
 *
 * @author Zhou FengFeng
 */
public class RandomUtil {

    private static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final int allLength = 36;
    private static final int allLength2 = 10;
    private static final char[] allChars = {'a', 'b', 'c', 'd', 'e', 'f', 'g',
            'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
            'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9'};
    private static final char[] allChar2 = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9'};

    /**
     * 返回指定长的随机字符串(只包含大小写字母、数字
     *
     * @param length 随机字符串长度
     * @return String
     */
    public static String generateString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回指定长的随机数字母字符串只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return String
     */
    public static String generateMixString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(letterChar.length())));
        }
        return sb.toString();
    }

    /**
     * 返回指定长的随机纯小写字母字符串只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return String
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回指定长的随机纯大写字母字符串只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return String
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 生成随机字符
     *
     * @param strLength 随机字符串长度
     * @return String
     */
    public static String getRandomStr(int strLength) {
        Random rand = new Random();
        char[] charTmp = new char[strLength];
        for (int i = 0; i < strLength; i++) {
            charTmp[i] = allChars[rand.nextInt(allLength)];
        }

        return String.valueOf(charTmp);// charTmp.toString();
    }

    /**
     * 生成随机数
     *
     * @param strLength 随机字符串长串
     * @return String
     */
    public static String getRandomNum(int strLength) {
        Random rand = new Random();
        char[] charTmp = new char[strLength];
        for (int i = 0; i < strLength; i++) {
            charTmp[i] = allChar2[rand.nextInt(allLength2)];
        }
        return String.valueOf(charTmp);// charTmp.toString();
    }

    /**
     * 根据length长度来获取该范围内的随机数
     *
     * @param length 随机字符串长度
     * @return String
     */
    public static String randomNum(int length) {
        Random rand = new Random();
        String result = "";
        for (int i = 0; i < length; i++) {
            result += rand.nextInt(10);
        }
        return result;
    }

}

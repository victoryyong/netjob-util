package com.thsword.utils.object;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符
 * 
 * @author 李小勇
 * 
 */
public class StringUtil {

	/**
	 * 发�?加密信息�?�?��编码
	 * 
	 * @param str
	 *            加密的内�?
	 * @param encoding
	 *            编码
	 * @return
	 */
	public static String urlEncode(String str, String encoding) {
		try {
			return URLEncoder.encode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 解码信息
	 * 
	 * @param str解码信息
	 * @param encoding编码
	 * @return
	 */
	public static String urlDecoder(String str, String encoding) {
		try {
			return URLDecoder.decode(str, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 验证输入的邮箱格式是否符�?
	 * 
	 * @param email
	 * @return 是否合法
	 */
	public static boolean isEmail(String email) {
		Pattern p = Pattern
				.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
		Matcher m = p.matcher(email);
		return m.find();
	}

}

package com.util.object;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import com.util.validator.ValidatorUtil;

/**
 * url工具
 * 
 * @author Zhou FengFeng
 * 
 */
public class UrlUtil {

	private static String encoding = "UTF-8";

	/**
	 * 转译成url
	 * 
	 * @param str
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encoder(final String str)
			throws UnsupportedEncodingException {
		if (ValidatorUtil.isNotNull(str)) {
			return URLEncoder.encode(str, encoding);
		} else {
			return null;
		}

	}

	/**
	 * 转成String
	 * 
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String decoder(final String str)
			throws UnsupportedEncodingException {
		if (!ValidatorUtil.isNotNull(str)) {
			return URLDecoder.decode(str, encoding);
		} else {
			return null;
		}
	}
}

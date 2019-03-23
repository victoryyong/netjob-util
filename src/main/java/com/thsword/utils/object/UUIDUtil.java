package com.thsword.utils.object;

import java.util.Date;
import java.util.UUID;

import com.thsword.utils.date.DateUtil;

/**
 * 生成32位id
 * 
 * @ClassName: UUIDUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author yong
 * @date 2017年5月15日 上午12:15:44
 *
 */
public class UUIDUtil {

	public static String get32UUID() {
		String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
		return uuid.toUpperCase();
	}

	public static String get32TradeNo() {
		return DateUtil.getString(new Date(),"yyyyMMdd") + System.currentTimeMillis()
				+ getRandomNumber(11);
	}

	private static String getRandomNumber(int len) {
		String str = "";
		for (int i = 0; i < len; i++) {
			Double number = Double.valueOf(Math.random() * 10.0D);
			str = str + number.intValue();
		}
		return str;
	}
}

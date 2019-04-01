package com.thsword.utils.object;

import java.util.Properties;

import com.thsword.utils.validator.ValidatorUtil;

/**
 * 资源文件读取
 * 
 * @author 李小勇
 * 
 */
public class PropertiesUtil {

	private static Properties properties;

	/**
	 * 读取资源文件
	 * 
	 * @param path
	 *            路径+文件
	 * @param key
	 * @return
	 */
	public static String getPropertiesByKey(final String path, final String key) {
		try {
			if (ValidatorUtil.isNotNull(key)) {
				if (null == properties) {
					properties = new Properties();
					// 加载文件
					properties.load(PropertiesUtil.class.getClassLoader()
							.getResourceAsStream(path));
				}
				return properties.getProperty(key);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

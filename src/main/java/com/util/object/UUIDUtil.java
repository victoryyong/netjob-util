package com.util.object;

import java.util.UUID;
/**
 * 生成32位id
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
}

package com.tarena.common;

import java.util.UUID;

/**
 *	生成随机ID
 */
public class UUIDUtil {

	public static String getUID() {
		return UUID.randomUUID().toString();
	}

}

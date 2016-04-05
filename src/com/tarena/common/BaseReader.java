package com.tarena.common;

import java.io.IOException;
import java.util.Properties;

public class BaseReader {
	
	private static int pageSize;

	static {
		Properties p = new Properties();
		try {
			p.load(BaseReader.class.getClassLoader()
					.getResourceAsStream("base.properties"));
			pageSize = Integer.valueOf(p.getProperty("page_size"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getPageSize() {
		return pageSize;
	}
	
	public static void main(String[] args) {
		
	}

}

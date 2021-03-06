/*
 * This class is helpful for fetching the data from the .properties file
 */

package com.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class Getconfig {

	public static String getProperties(String key) {
		Properties p = new Properties();
		String FilePath = System.getProperty("user.dir") + "/src/main/resources/config.properties";
		FileInputStream fi = null;
		try {
		fi = new FileInputStream(FilePath);
		p.load(fi);
		return (String) p.getProperty(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

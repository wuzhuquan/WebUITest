package com.xmd.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.io.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

/**
 * @author wangkai<br>
 * @Desription 从.properties文件中读取相关测试数据<br>
 * 
 * */
public class PropertiesDataProvider {

	public static String getTestData(String configFilePath, String key) {
		Configuration config = null;
		try {
			config = new PropertiesConfiguration(configFilePath);
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return String.valueOf(config.getProperty(key));

	}
	
	public static void writeProperties(String key, String value) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("config/goods.properties"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		OutputStream output = null;
		try {
			output = new FileOutputStream("config/goods.properties");
	          for (Enumeration<?> e = properties.propertyNames(); e.hasMoreElements();) {
	                String s = (String) e.nextElement(); // 遍历所有元素
	                if (s.equals(key)) {
	                    /**如果键相同则覆盖*/
	                	properties.setProperty(s, value);
	                } else {
	                	/**否则就原样写入*/
	                	properties.setProperty(key, value);
	                }
	            }
			
		//	properties.setProperty(key,value);//保存键值对到内存
			properties.store(output, "wangkai modify" + new Date().toString());// 保存键值对到文件中
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}


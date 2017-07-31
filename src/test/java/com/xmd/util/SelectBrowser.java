package com.xmd.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestContext;

import java.util.Properties;

/**
 * @author wangkai
 * @decription 在不同的平台上选择对应的浏览器,系统平台程序自动判断是什么平台
 * */
public class SelectBrowser {
	static Logger logger = Logger.getLogger(SelectBrowser.class.getName());

	public WebDriver selectExplorerByName(String browser, ITestContext context) {
		Properties props = System.getProperties(); // 获得系统属性集
		String currentPlatform = props.getProperty("os.name"); // 操作系统名称
		logger.info("当前操作系统是 [" + currentPlatform + "]");
		logger.info("启动测试浏览器[" + browser + "]");
		String driverConfgFilePath = context.getCurrentXmlTest().getParameter("driverConfgFilePath");
		/** 声明好驱动的路径 */
		String chromedriver_win = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_win");
		String chromedriver_linux = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_linux");
		String chromedriver_mac = PropertiesDataProvider.getTestData(driverConfgFilePath, "chromedriver_mac");
		String iedriver = PropertiesDataProvider.getTestData(driverConfgFilePath, "iedriver");
		if (currentPlatform.toLowerCase().contains("win")) {

			if (browser.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver", iedriver);
				DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
				ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
				return new InternetExplorerDriver(ieCapabilities);
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_win);
				
				//在chromedriver中添加属性
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities = DesiredCapabilities.chrome();
				ChromeOptions options = new ChromeOptions();
				//start-maximized最大化窗口，可以设置一些属性
				//https://sites.google.com/a/chromium.org/chromedriver/capabilities
				//http://peter.sh/experiments/chromium-command-line-switches/#condition-5
				//http://www.ithao123.cn/content-7377274.html
				/* 百度搜索chronium启动参数即可获得*/
				//options.addArguments("test-type", "start-maximized","no-default-browser-check","user-data-dir=C:/Users/wangkai/AppData/Local/Google/Chrome/User Data");    
				options.addArguments("test-type", "disable-extensions=Chrome Automation Extension");
				options.addArguments("enable-automation");
				options.addArguments("disable-infobars");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
				return new ChromeDriver(capabilities);
				
//				return new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
//				System.setProperty("webdriver.firefox.bin","D:/Program Files (x86)/Mozilla Firefox/firefox.exe");
				return new FirefoxDriver();

			} else {

				logger.error("The [" + browser + "]" + " explorer is not applicable  for  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");

			}

		} else if (currentPlatform.toLowerCase().contains("linux")) {

			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_linux);
//				Map<String, Object> chromeOptions = new HashMap<String, Object>();
//				chromeOptions.put("binary", "/usr/bin/google-chrome-stable");
//				DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//				capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
//				return new ChromeDriver(capabilities);
				return new ChromeDriver();

			} else if (browser.equalsIgnoreCase("firefox")) {
				return new FirefoxDriver();
			} else {
				logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
			}

		} else if (currentPlatform.toLowerCase().contains("mac")) {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromedriver_mac);
				return new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				return new FirefoxDriver();
			} else {
				logger.error("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
				Assert.fail("The [" + browser + "]" + " explorer does not apply to  [" + currentPlatform + "] OS");
			}

		} else
			logger.error("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");
		Assert.fail("The [" + currentPlatform + "] is not supported for this automation frame,please change the OS(Windows,MAC or LINUX)");

		return null;

	}

}

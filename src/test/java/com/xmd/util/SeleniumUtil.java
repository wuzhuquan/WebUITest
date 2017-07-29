package com.xmd.util;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;

import java.util.logging.Logger;

/**
 * @Classname ：SeleniumUtil
 * @Description ：(这里用一句话描述这个类的作用)
 * @Author ：xuph
 * @Date ：2017/7/29
 */
public class SeleniumUtil {
    public static Logger logger =Logger.getLogger(SeleniumUtil.class.getName());
    public ITestResult it = null;
    public WebDriver driver = null;
    public WebDriver window = null;
    public void waitForElementToLoad(int timeOut,final By By){
        logger.info("开始查找元素["+By+"]");
        try {
            (new WebDriverWait(driver,timeOut)).until(new ExpectedCondition<Boolean>(){

                public Boolean apply(WebDriver driver){
                    WebElement element = driver.findElement(By);
                    return element.isDisplayed();
                }
            });
        } catch (TimeoutException e){
            logger.info("超时！！！"+timeOut+"秒内未找到元素["+By+"]");
            Assert.fail("超时！！！"+timeOut+"秒内未找到元素["+By+"]");
        }
        logger.info("找到了元素["+By+"]");
    }
}

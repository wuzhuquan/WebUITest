package com.xmd.util;


import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;

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

    /**等待元素加载*/
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

    /**
     * 退出
     */
    public void quit() {
        driver.quit();
    }

    /**
     * 暂停当前用例的执行，暂停的时间为：sleepTime，单位为毫秒
     */
    public void pause(int sleepTime) {
        if (sleepTime <= 0) {
            return;
        }
        try {
            Thread.sleep(sleepTime);
            logger.info("当前进程暂停" + sleepTime + "毫秒。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**点击*/
    public void click(By by){
        driver.findElement(by).click();
        logger.info("点击元素：[" + by + "]");
    }
    /**
     * 验证当前会所
     *
     * @param byTitleId
     *            会所标题的id
     * @param timeOut
     *            等待页面加载时间
     * @param titleName
     *            期望会所的标题
     */
    public void verifyClubTitle(int timeOut, By byTitleId, String titleName) {
        waitForElementToLoad(timeOut, byTitleId);
        String title = driver.findElement(byTitleId).getText().trim();
        isTextCorrect(title, titleName);
    }

    /**
     * 判断文本是不是和需求要求的文本一致
     **/
    public void isTextCorrect(String actual, String expected) {
        try {
            Assert.assertEquals(actual, expected);
        } catch (AssertionError e) {
            logger.info("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
            Assert.fail("期望的文字是 [" + expected + "] 但是找到了 [" + actual + "]");
        }
        logger.info("找到了期望的文字: [" + expected + "]");
    }
    /**
     * 原本第二个参数的类型为：CharSequence...，但是注意这里使用CharSequence...输出的为字符的物理地址，
     * 没有修改为String类型
     */
    public void sendKeys(By by, CharSequence... keysToSend) {
        driver.findElement(by).sendKeys(keysToSend);
        logger.info("在文本框：【" + by + "】中输入字符：【" + keysToSend[0] + "】。");
    }

    /**
     * 最大化浏览器操作
     */
    public void maxWindow(String browserName) {
        logger.info("最大化浏览器:" + browserName);
        driver.manage().window().maximize();
    }
    /**
     * pageLoadTimeout。页面加载时的超时时间。因为webdriver会等页面加载完毕在进行后面的操作，
     * 所以如果页面在这个超时时间内没有加载完成，那么webdriver就会抛出异常
     */

    public void waitForPageLoading(int pageLoadTime) {
        driver.manage().timeouts().pageLoadTimeout(pageLoadTime, TimeUnit.SECONDS);
    }

    /** 根据元素来获取此元素的定位值 */
    public String getLocatorByElement(WebElement element, String expectText) {
        String text = element.toString();
        String expect = null;
        try {
            expect = text.substring(text.indexOf(expectText) + 1, text.length() - 1);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("failed to find the string [" + expectText + "]");

        }

        return expect;

    }

    /**
     * get方法包装
     */
    public void get(String url) {
        driver.get(url);
    }

    /**
     * 刷新方法包装
     */
    public void refresh() {
        driver.navigate().refresh();
    }

    /**启动浏览器，打开页面*/
    public void launchBrowser(String browserName, ITestContext context){
        String webUrl = context.getCurrentXmlTest().getParameter("testUrl");
        int timeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));
        SelectBrowser selectBrowser = new SelectBrowser();
        driver = selectBrowser.selectExplorerByName(browserName, context);
        try {
            maxWindow(browserName);
            waitForPageLoading(timeOut);
            get(webUrl);
            logger.info("打开测试页面:[" + webUrl + "]");

        } catch (Exception e) {
            refresh();
            logger.info("页面刷新成功！");
        }
    }
}

package com.xmd.base;

import com.xmd.util.SeleniumUtil;
import junit.framework.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

import java.util.logging.Logger;

/**
 * @Classname ：BasePrepare
 * @Description ：(这里用一句话描述这个类的作用)
 * @Author ：wuzq
 * @Date ：2017/7/31
 */
public class BasePrepare {
    static Logger logger = Logger.getLogger(BasePrepare.class.getName());
    protected SeleniumUtil seleniumUtil = null;
    protected ITestContext testContext  = null;

    @BeforeClass
   /** 启动浏览器，打开测试页面*/
    public void startTest(ITestContext context){
        seleniumUtil = new SeleniumUtil();
        this.testContext = context;
        String browserName = context.getCurrentXmlTest().getParameter("browserName");
        try {
            seleniumUtil.launchBrowser(browserName,context);
        }catch (Exception e){
            logger.error("浏览器不能正常工作，请检查是不是被手动关闭或者其他原因",e);
            Assert.fail("浏览器不能正常工作，请检查是不是被手动关闭或者其他原因");
        }
        // 设置一个testng上下文属性，将driver存起来，之后可以使用context随时取到
        testContext.setAttribute("SELENIUM_DRIVER", seleniumUtil.driver);
    }
    public void endTest(){
        if(seleniumUtil.driver != null){
            seleniumUtil.quit();

        }
    }
}


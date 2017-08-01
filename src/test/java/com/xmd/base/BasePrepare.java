package com.xmd.base;

import com.xmd.util.SeleniumUtil;
import junit.framework.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import com.xmd.util.ExcelDataProvider;
import java.io.IOException;
import java.util.Iterator;
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
    protected ITestContext testContext = null;

    @BeforeClass
    /** 启动浏览器，打开测试页面*/
    public void startTest(ITestContext context) {
        seleniumUtil = new SeleniumUtil();
        this.testContext = context;
        String browserName = context.getCurrentXmlTest().getParameter("browserName");
        try {
            seleniumUtil.launchBrowser(browserName, context);
        } catch (Exception e) {
            logger.info("浏览器不能正常工作，请检查是不是被手动关闭或者其他原因");
            Assert.fail("浏览器不能正常工作，请检查是不是被手动关闭或者其他原因");
        }
        // 设置一个testng上下文属性，将driver存起来，之后可以使用context随时取到
        testContext.setAttribute("SELENIUM_DRIVER", seleniumUtil.driver);
    }

    public void endTest() {
        if (seleniumUtil.driver != null) {
            seleniumUtil.quit();
        } else {
            logger.info("浏览器driver没有获得对象,退出操作失败");
            Assert.fail("浏览器driver没有获得对象,退出操作失败");
        }
    }

/**
 * 测试数据提供者 - 方法
 * */
    @DataProvider(name = "data")
    public Iterator<Object[]> dataForTestMethod() throws IOException{
        String modelName = null;
        String caseNum = null;
        String className = this.getClass().getName();
        int lastIndexNum = className.lastIndexOf(".");
        int underlineIndexNum =className.indexOf("_");
        if (lastIndexNum > 0){
            modelName = className.substring(0,lastIndexNum);
            modelName = modelName.substring(modelName.lastIndexOf(".") + 1);
        }
        if (underlineIndexNum > 0){
            caseNum = className.substring(underlineIndexNum + 1,underlineIndexNum + 4);
        }
        return new ExcelDataProvider(modelName,caseNum);
    }

}
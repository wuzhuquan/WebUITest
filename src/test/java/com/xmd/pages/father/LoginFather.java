package com.xmd.pages.father;
import com.xmd.pages.pagesHelper.LoginPageHelper;
import org.testng.ITestContext;
import com.xmd.util.SeleniumUtil;
import com.xmd.base.BasePrepare;
/**
 * @Classname ：LoginFather
 * @Description ：(这里用一句话描述这个类的作用)
 * @Author ：xuph
 * @Date ：2017/7/29
 */
public class LoginFather extends BasePrepare{
    public static int timeOut = 0;
    public static int sleepTime = 0;

    public static void loginPrepare(ITestContext context,SeleniumUtil seleniumUtil){
        timeOut = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));
        sleepTime = Integer.valueOf(context.getCurrentXmlTest().getParameter("timeOut"));

        LoginPageHelper.waitLoginPageToLoad(timeOut,seleniumUtil);
    }
}

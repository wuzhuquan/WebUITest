package com.xmd.pages.pagesHelper;
import com.xmd.pages.LoginPage;
import com.xmd.util.SeleniumUtil;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

/**
 * @Classname ：LoginPageHelper
 * @Description ：(这里用一句话描述这个类的作用)
 * @Author ：xuph
 * @Date ：2017/7/29
 */
public class LoginPageHelper {
    public static Logger logger = Logger.getLogger(LoginPageHelper.class.getName());

   /** 等待登录页元素加载*/
    public static void waitLoginPageToLoad(int timeOut,SeleniumUtil seleniumUtil){
        seleniumUtil.pause(1000);
        logger.info("登录页面--用户名框加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_USERNAME);
        logger.info("登录页面--密码框加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_PASSWORD);
        logger.info("登录页面--登录按钮加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_BUTTON_SUMMIT);
        logger.info("登录页面加载完成");
    }

    /**输入数据,登录*/
    public static void login(int timeOut,SeleniumUtil seleniumUtil,String userName,String userPassword){
        logger.info("开始输入登录数据");
        seleniumUtil.sendKeys(LoginPage.LP_INPUT_USERNAME,userName);
        seleniumUtil.sendKeys(LoginPage.LP_INPUT_PASSWORD,userPassword);
        logger.info("登录页面，用户名和密码的输入完毕");
        logger.info("点击提交登录按钮");
        seleniumUtil.click(LoginPage.LP_BUTTON_SUMMIT);
        seleniumUtil.pause(1000);

    }
    /** 校验用户是否登录成功 */
    public static void verifyLogin(int timeOut,SeleniumUtil seleniumUtil,String expected){
        seleniumUtil.verifyClubTitle(timeOut,LoginPage.LP_CLUBTITLE,expected);
    }
    /**退出当前登录*/
    public static void logout(int timeOut,SeleniumUtil seleniumUtil){
    	logger.info("退出当前登录");
    	seleniumUtil.click(LoginPage.INDEX_LOGOUT);
    }

}

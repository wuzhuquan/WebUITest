package com.xmd.pages.pagesHelper;
import com.xmd.pages.LoginPage;
import com.xmd.util.SeleniumUtil;

import java.util.logging.Logger;

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
        logger.info("登录页面--用户名框加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_USERNAME);
        logger.info("登录页面--密码框加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_INPUT_PASSWORD);
        logger.info("登录页面--登录按钮加载");
        seleniumUtil.waitForElementToLoad(timeOut, LoginPage.LP_BUTTON_SUMMIT);
        logger.info("登录页面加载完成");
    }

    /**输入数据,登录*/
    public static void login(int timeOut,SeleniumUtil seleniumUtil,String username,String password){

    }
}

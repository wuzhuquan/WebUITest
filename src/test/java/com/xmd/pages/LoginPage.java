package com.xmd.pages;

import org.openqa.selenium.By;

/**
 * @Classname ：LoginPage
 * @Description ：(这里用一句话描述这个类的作用)
 * @Author ：xuph
 * @Date ：2017/7/29
 */
public class LoginPage {
    public static final By LP_INPUT_USERNAME = By.xpath("//*[@id=\"user-name\"]");
    public static final By LP_INPUT_PASSWORD = By.xpath(("//*[@id=\"user-pw\"]"));
    public static final By LP_BUTTON_SUMMIT = By.xpath("//*[@id=\"login-dialog\"]/form/input[2]");
    //登录失败提醒
    public static final By LP_INFO_FAIL = By.xpath("//*[@id=\"login-dialog\"]/form/div[4]");

}

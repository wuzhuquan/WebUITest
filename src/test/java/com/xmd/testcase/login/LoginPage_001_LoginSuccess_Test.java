package com.xmd.testcase.login;

import com.xmd.pages.father.LoginFather;
import com.xmd.pages.pagesHelper.LoginPageHelper;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPage_001_LoginSuccess_Test extends LoginFather{
    @Test(dataProvider = "data")
    public void loginSuccessTest(ITestContext context, Map<String, String> data){
        LoginFather.loginPrepare(context,seleniumUtil);
        LoginPageHelper.waitLoginPageToLoad(timeOut,seleniumUtil);
        LoginPageHelper.login(timeOut,seleniumUtil,data.get("LOGIN_NAME"),data.get("LOGIN_PASSWORD"));
        LoginPageHelper.verifyLogin(timeOut,seleniumUtil,data.get("CLUB_NAME"));
        LoginPageHelper.logout(timeOut, seleniumUtil);
    }
}

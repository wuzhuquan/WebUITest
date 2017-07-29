package com.xmd.testcase.login;

import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class LoginPage_001_LoginSuccess_Test extends LoginFather{
    @Test(dataProvider = "data")
    public void loginSuccessTest(ITestContext context, Map<String, String> data){

    }
}

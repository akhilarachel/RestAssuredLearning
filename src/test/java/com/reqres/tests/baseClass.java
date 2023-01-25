package com.reqres.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.reqres.utility.Helper;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;

public class baseClass {
    public ExtentReports report;
    public String randomFilename;
    public ExtentTest logger;
    public String randomUUID;

    //Setup before each claa
    @Parameters("url")
    @BeforeClass
    public void setUp(String url){
        Reporter.log("Test config is being prepared" , true);
        RestAssured.baseURI = url;
        randomFilename = Helper.getRandomValue();
        randomUUID = Helper.getRandomUUID();
        ExtentHtmlReporter htmlReport = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Reports/TestReport_"+randomUUID+".html"));
        report = new ExtentReports();
        report.attachReporter(htmlReport);
        Reporter.log("Test can be started" , true);
    }

    //Report generation
    @AfterMethod
    public void finalTearDown(ITestResult result) throws IOException {
        if(result.getStatus() == ITestResult.FAILURE){
            logger.fail("Testcase failed");
        } else if (result.getStatus() == ITestResult.SUCCESS){
            logger.pass("Testcase passed");
        } else if (result.getStatus() == ITestResult.SKIP){
            logger.skip("Testcase skipped");
        }
        report.flush();
    }
}

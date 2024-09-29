package com.vinod.listners;

import com.vinod.utility.ExtentReportManager;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

@Slf4j
public class TestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        ExtentReportManager.logTest(result.getMethod().getMethodName());
        ExtentReportManager.logInfo("Starting test " + result.getMethod().getMethodName());
        log.info("Starting test " + result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Test " + result.getMethod().getMethodName() + " passed.");
        ExtentReportManager.logInfo("Test " + result.getMethod().getMethodName() + " passed.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        ExtentReportManager.logFailure("Test " + result.getMethod().getMethodName() + " failed. Error: " + result.getThrowable());
        log.error("Test " + result.getMethod().getMethodName() + " failed. Error: " + result.getThrowable());

    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReportManager.flushReports();
    }
}
package com.vinod.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportManager {
    private static final String REPORT_FOLDER = "reports/";
    private static final String FILE_NAME = "ExtentReport_" +
            new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".html";
    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static synchronized ExtentReports getReporter() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(REPORT_FOLDER + FILE_NAME);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setDocumentTitle("Automation Report");
            sparkReporter.config().setReportName("Test Execution Report");
            sparkReporter.config().setEncoding("utf-8");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Organization", "Fan Code");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
        }
        return extent;
    }

    public static synchronized void logTest(String testName) {
        ExtentTest extentTest = getReporter().createTest(testName);
        test.set(extentTest);
    }

    public static synchronized ExtentTest getTest() {
        return test.get();
    }



    public static synchronized void logInfo(String message) {
        getTest().info(message);
    }

    public static synchronized void error(String message) {
        getTest().log(Status.WARNING,message);
    }

    public static synchronized void warn(String message) {
        getTest().log(Status.WARNING,message);
    }
    public static synchronized void logFailure(String message) {
        getTest().fail(message);
    }

    public static synchronized void flushReports() {
        if (extent != null) {
            extent.flush();
        }
    }
}

package org.openlane.autohawk.testutils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import java.util.Date;

public class ExtentReporterNG extends AndroidBaseTest {
    static ExtentReports extent;

    public static ExtentReports extentReportObject() {
        Date date = new Date();
        String presentTime = String.valueOf(date.getTime());
        String path = System.getProperty("user.dir") + "/reports/index" + presentTime + ".html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("AutoHawkDemo");
        report.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Sagar S");

        return extent;
    }

    public static ExtentReports getExtent() {
        if (extent == null) {
            return extentReportObject();
        }
        return extent;
    }
}

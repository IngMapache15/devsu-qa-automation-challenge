package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getReportInstance() {

        if (extentReports == null) {

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss")
                            .format(new Date());

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(
                            "target/reports/API_PetStore_Report_" +
                                    timestamp +
                                    ".html");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo(
                    "Project",
                    "PetStore API Automation");

            extentReports.setSystemInfo(
                    "Framework",
                    "RestAssured + Java + JUnit5");

            extentReports.setSystemInfo(
                    "QA",
                    "Alejandro Rodriguez");
        }

        return extentReports;
    }
}
package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReportManager {

    private static ExtentReports extentReports;

    public static ExtentReports getReportInstance() {

        if (extentReports == null) {

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));

            String reportPath =
                    "target/reports/SauceDemo_E2E_Report_" + timestamp + ".html";

            ExtentSparkReporter sparkReporter =
                    new ExtentSparkReporter(reportPath);

            sparkReporter.config().setDocumentTitle("SauceDemo Automation Report");
            sparkReporter.config().setReportName("E2E Purchase Flow Execution");

            extentReports = new ExtentReports();
            extentReports.attachReporter(sparkReporter);

            extentReports.setSystemInfo("Project", "SauceDemo E2E Automation");
            extentReports.setSystemInfo("Framework", "Selenium WebDriver + Java + JUnit 5");
            extentReports.setSystemInfo("Design Pattern", "Page Object Model (POM)");
            extentReports.setSystemInfo("Browser", "Google Chrome");
            extentReports.setSystemInfo("QA", "Alejandro Rodriguez");
            extentReports.setSystemInfo("Execution Date", timestamp);
        }

        return extentReports;
    }
}
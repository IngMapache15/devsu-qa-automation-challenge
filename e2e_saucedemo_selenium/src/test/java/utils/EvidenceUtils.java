package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EvidenceUtils {

    public static String takeScreenshot(WebDriver driver, String screenshotName) {
        try {
            Files.createDirectories(Paths.get("target/screenshots"));

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS"));

            String fileName = timestamp + "_" + screenshotName + ".png";

            File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            String destination = "target/screenshots/" + fileName;

            Files.copy(source.toPath(), Paths.get(destination));

            return "../screenshots/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("Error taking screenshot: " + e.getMessage());
        }
    }

    public static void visualDelay() {
        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
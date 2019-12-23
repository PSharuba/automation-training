package by.mmf.sharubapv.util;

import by.mmf.sharubapv.driver.DriverSingleton;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;


public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();
    private final Logger logger = LogManager.getRootLogger();
    private final String SCREENSHOTS_PATH = ".//target/screenshots/";

    public void onTestStart(ITestResult iTestResult) {

    }

    public void onTestSuccess(ITestResult iTestResult) {

    }

    public void onTestFailure(ITestResult iTestResult) {
        saveScreenshot();
    }

    public void onTestSkipped(ITestResult iTestResult) {

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {

    }

    public void onStart(ITestContext iTestContext) {
        logger.info("Test started");
    }

    public void onFinish(ITestContext iTestContext) {
        logger.info("Test finished");
    }

    private void saveScreenshot() {
        File screenCapture = ((TakesScreenshot) DriverSingleton
                .getDriver())
                .getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenCapture, new File(
                    ".//target/screenshots/"
                            + getCurrentTimeAsString() +
                            ".png"));
        } catch (IOException e) {
            log.error("Failed to save screenshot: " + e.getLocalizedMessage());
        }
//        try {
//            Thread.sleep(120);
//            Robot r = new Robot();
//            Rectangle capture =
//                    new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
//            BufferedImage Image = r.createScreenCapture(capture);
//            ImageIO.write(Image, "jpg", new File(SCREENSHOTS_PATH));
//            System.out.println("Screenshot saved");
//        } catch (AWTException | IOException | InterruptedException ex) {
//            System.out.println("Error taking screenshot: " + ex);
//        }
    }

    private String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}

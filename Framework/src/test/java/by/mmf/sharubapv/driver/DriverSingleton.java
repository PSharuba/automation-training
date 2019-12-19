package by.mmf.sharubapv.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverSingleton {

    private static WebDriver driver;


    private DriverSingleton() {
    }

    public static WebDriver getDriver() {
        if (null == driver) {
            System.out.println("Properties: ");
            System.out.println("Browser: " + System.getProperty("browser"));
            System.out.println("Environment: " + System.getProperty("environment"));
            System.out.println("XML: " + System.getProperty("surefire.suitexml"));
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                    WebDriverManager.chromedriver().setup();
                    /*ChromeOptions option = new ChromeOptions();
                    option.addArguments("--headless", "--no-sandbox", "--disable-dev-shm-usage");
                    DesiredCapabilities chrome = DesiredCapabilities.chrome();
                    chrome.setJavascriptEnabled(true);
                    option.setCapability(ChromeOptions.CAPABILITY, option);*/
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

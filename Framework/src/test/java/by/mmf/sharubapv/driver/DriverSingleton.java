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
//
//                    ChromeOptions options = new ChromeOptions();
//                    //options.addArguments("start-maximized"); // open Browser in maximized mode
//                    options.addArguments("disable-infobars"); // disabling infobars
//                    options.addArguments("--disable-extensions"); // disabling extensions
//                    options.addArguments("--disable-gpu"); // applicable to windows os only
//                    options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
//                    options.addArguments("--no-sandbox"); // Bypass OS security model
//                    WebDriverManager.chromedriver().version("79.0.3945.36").setup();
//                    driver = new ChromeDriver(options);
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--disable-extensions");
                    options.addArguments("--headless");
                    options.addArguments("--disable-gpu");
                    options.addArguments("--no-sandbox");
                    options.addArguments("--window-size=1920,1080");

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);

                }
            }
            //driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}

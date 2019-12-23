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
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default: {
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized"); // https://stackoverflow.com/a/26283818/1689770
                    options.addArguments("enable-automation"); // https://stackoverflow.com/a/43840128/1689770
                    options.addArguments("--headless"); // only if you are ACTUALLY running headless
                    options.addArguments("--no-sandbox"); //https://stackoverflow.com/a/50725918/1689770
                    options.addArguments("--disable-infobars"); //https://stackoverflow.com/a/43840128/1689770
                    options.addArguments("--disable-dev-shm-usage"); //https://stackoverflow.com/a/50725918/1689770
                    options.addArguments("--disable-browser-side-navigation"); //https://stackoverflow.com/a/49123152/1689770
                    options.addArguments("--disable-gpu"); //https://stackoverflow.com/questions/51959986/how-to-solve-selenium-chromedriver-timed-out-receiving-message-from-renderer-exc
                    options.addArguments("--window-size=1920,1080");

                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);

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

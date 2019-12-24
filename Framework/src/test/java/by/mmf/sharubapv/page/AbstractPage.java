package by.mmf.sharubapv.page;

import by.mmf.sharubapv.driver.DriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {

    protected final Logger logger = LogManager.getRootLogger();
    protected WebDriver driver;

    protected abstract AbstractPage openPage();

    protected final int WAIT_TIMEOUT_SECONDS = 30;

    protected AbstractPage() {
        this.driver = DriverSingleton.getDriver();
        logger.info("Page object created");
    }
}

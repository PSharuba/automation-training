package by.mmf.sharubapv.page;

import by.mmf.sharubapv.model.SearchQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    private final String BASE_URL = "https://ru.hotels.com/";

    @FindBy(xpath = "//*[@id='qf-0q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Поиск')]")
    private WebElement searchButton;


    public SearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
        logger.info("Search page class created");
    }

    public SearchPage enterDataToSearchFor(SearchQuery searchData) {
        citySearchField.sendKeys(searchData.getCity());
        driver.findElement(By.xpath("//*[@id='hds-marquee']")).click();
        dateToEnterField.sendKeys(searchData.getDateToEnterHotel());
        dateToLeaveField.sendKeys(searchData.getDateToLeaveHotel());
        logger.info("Search fields filled");
        return this;
    }

    public MainPage clickSearchButton() {
        searchButton.click();
        logger.info("Going to main page");
        return new MainPage(driver);
    }

    public String getErrorMessage() {
        logger.info("Searching for error message");
        return driver.findElement(By.xpath("//*[@id='widget-overlay-title-1']")).getText();
    }

    @Override
    public SearchPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Opening search page");
        return this;
    }
}

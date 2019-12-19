package by.mmf.sharubapv.page;

import by.mmf.sharubapv.model.SearchQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends AbstractPage {

    private final String BASE_URL = "https://ru.hotels.com/";

    private final By WELCOME_TEXT_LOCATOR = By.xpath("//h1[@class='widget-query-heading']");
    private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//*[@id='header-sign-in']");
    private final By ACCOUNT_BUTTON_LOCATOR = By.xpath("//*[@class='nav-section-toggle']/label");
    private final By USD_LOCATOR = By.xpath("//a[@data-currency='USD']");
    private final By ERROR_LOCATOR = By.xpath("//*[@id='widget-overlay-title-1']");
    private final By ALERT_LOCATOR = By.xpath("//div[@class='form-error']/span");

    @FindBy(xpath = "//*[@id='hdr-deals']")
    private WebElement specialOffersLink;

    @FindBy(xpath = "//*[@class='widget-query-sub-title']")
    private WebElement searchHeader;

    @FindBy(xpath = "//*[@id='header-toggle-currency']")
    private WebElement moneyChangeButton;

    @FindBy(xpath = "//*[@id='qf-0q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//*[@id='hds-marquee']/div[2]/div[1]/div/form/div[4]/button")
    private WebElement searchButton;


    public SearchPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    private boolean checkIfLoggedIn() {
        return driver.findElement(WELCOME_TEXT_LOCATOR).getText()
                .replaceAll(",", "").length() > 0;
    }

    public SearchPage enterDataToSearchFor(SearchQuery searchData) {
        citySearchField.clear();
        citySearchField.sendKeys(searchData.getCity());
        searchHeader.click();
        dateToEnterField.clear();
        dateToEnterField.sendKeys(searchData.getDateToEnterHotel());
        dateToLeaveField.clear();
        dateToLeaveField.sendKeys(searchData.getDateToLeaveHotel());
        searchHeader.click();
        logger.info("Search fields filled");
        return this;
    }

    public SpecialOffersSearchPage goToSpecialOffers() {
        specialOffersLink.click();
        return new SpecialOffersSearchPage();
    }

    public MainPage clickSearchButton() {
        searchButton.click();
        logger.info("Going to main page");
        return new MainPage();
    }

    public SearchPage changeMoneyToUSD() {
        moneyChangeButton.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.presenceOfElementLocated(USD_LOCATOR)).click();
        return this;
    }

    public String getDateAlertMessage() {
        searchButton.click();
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(ALERT_LOCATOR))
                .findElement(ALERT_LOCATOR).getText();
    }

    public String getErrorMessage() {
        logger.info("Searching for error message");
        searchButton.click();
        WebElement error = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(ERROR_LOCATOR))
                .findElement(ERROR_LOCATOR);
        return error.getText();
    }

    @Override
    public SearchPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Opening search page");
        return this;
    }
}

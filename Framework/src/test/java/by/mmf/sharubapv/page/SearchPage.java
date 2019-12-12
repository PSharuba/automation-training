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

    private final String BASE_URL = "https://ru.hotels.com/";

    private final By WELCOME_TEXT_LOCATOR = By.xpath("//h1[@class='widget-query-heading']");
    private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//*[@id='header-sign-in']");
    private final By ACCOUNT_BUTTON_LOCATOR = By.xpath("//*[@class='nav-section-toggle']/label");
    private final By USD_LOCATOR = By.xpath("//a[@data-currency='USD']");

    @FindBy(xpath = "//*[@id='hdr-deals']")
    private WebElement specialOffersLink;

    @FindBy(xpath = "//*[@id='header-toggle-currency']")
    private WebElement moneyChangeButton;

    @FindBy(xpath = "//*[@id='qf-0q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='qf-0q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Поиск')]")
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
        citySearchField.sendKeys(searchData.getCity());
        driver.findElement(By.xpath("//*[@id='hds-marquee']")).click();
        dateToEnterField.sendKeys(searchData.getDateToEnterHotel());
        dateToLeaveField.sendKeys(searchData.getDateToLeaveHotel());
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
        driver.findElement(USD_LOCATOR).click();
        return this;
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

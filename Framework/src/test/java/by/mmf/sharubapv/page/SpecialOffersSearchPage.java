package by.mmf.sharubapv.page;

import by.mmf.sharubapv.model.SearchQuery;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpecialOffersSearchPage extends AbstractPage {
    private final String BASE_URL = "ru.hotels.com/skidki-na-oteli/";

    private final By WELCOME_TEXT_LOCATOR = By.xpath("//h1[@class='widget-query-heading']");
    private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//*[@id='header-sign-in']");
    private final By ACCOUNT_BUTTON_LOCATOR = By.xpath("//*[@class='nav-section-toggle']/label");

    private final By SEARCH_HEADER_LOCATOR = By.xpath("//*[@class='cont-hd-alt widget-query-heading']");
    private final By DESTINATION_LOCATOR = By.xpath("//*[@id='qf-1q-destination']");
    private final By SEARCH_BUTTON_LOCATOR = By.xpath("//*[@type='submit']");
    private final By DATE_IN_LOCATOR = By.xpath("//*[@id='qf-1q-localised-check-in']");
    private final By DATE_OUT_LOCATOR = By.xpath("//*[@id='qf-1q-localised-check-out']");

    @FindBy(xpath = "//*[@id='header-toggle-currency']")
    private WebElement moneyChangeButton;

    @FindBy(xpath = "//*[@class='resp-module']")
    private WebElement searchForm;


    public SpecialOffersSearchPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    public SpecialOffersSearchPage enterDataToSearchFor(SearchQuery searchData) {
        WebElement citySearchField = searchForm.findElement(DESTINATION_LOCATOR);
        WebElement dateToEnterField = searchForm.findElement(DATE_IN_LOCATOR);
        WebElement dateToLeaveField = searchForm.findElement(DATE_OUT_LOCATOR);
        WebElement searchHeader = searchForm.findElement(SEARCH_HEADER_LOCATOR);
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

    public MainPage clickSearchButton() {
        searchForm.findElement(SEARCH_BUTTON_LOCATOR).click();
        logger.info("Going to main page");
        return new MainPage();
    }

    @Override
    public SpecialOffersSearchPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Opening search page");
        return this;
    }
}

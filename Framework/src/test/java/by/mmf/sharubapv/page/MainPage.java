package by.mmf.sharubapv.page;

import by.mmf.sharubapv.service.CurrencyFinder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
    private String baseUrl;

    @FindBy(xpath = "//*[@id='header-toggle-currency']")
    private WebElement moneyChangeButton;

    private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//*[@id='header-sign-in']");
    private final By ACCOUNT_BUTTON_LOCATOR = By.xpath("//*[@class='nav-section-toggle']/label");
    private final By HOTELS_LOCATOR = By.xpath("//li[@class='hotel']");
    private final By HOTEL_BUTTON_LOCATOR = By.xpath("//h3[contains(class(), 'p-name')]/a");
    private final By HOTEL_LIST_LOCATOR = By.xpath("//*[@id='listings']/ol");
    private final By FIVE_STARS_LOCATOR = By.xpath("//*[@id='f-star-rating-5']");
    private final By FREE_WIFI_LOCATOR = By.xpath("//*[@id='f-popular-527']");
    private final By BOOK_HOTEL_BUTTON_LOCATOR = By.xpath("//a[@class='cta']");
    private final By FLAT_FILTER_LOCATOR = By.xpath("//*[@id='f-accid-15']");
    private final By SPECIAL_DEAL_LOCATOR = By.xpath("//*[@class='special-deal-badge']");
    private final By PRICE_LOCATOR = By.xpath("//a[@class='price-link']/ins");

    @FindBy(xpath = "//*[@id='q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='filter-accommodation-type']")
    private WebElement hotelTypesList;

    @FindBy(xpath = "//*[@id='q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Найти')]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='listings']/ol")
    private WebElement hotelsList;

    private WebElement[] hotels;


    public MainPage() {
        super();
        baseUrl = driver.getCurrentUrl();
        PageFactory.initElements(this.driver, this);
        getHotelsList();
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(baseUrl);
        logger.info("Main page opened");
        return this;
    }

    public MainPage getHotelsList() {
        hotels = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(HOTEL_LIST_LOCATOR))
                .findElements(HOTELS_LOCATOR).toArray(new WebElement[0]);
        logger.info("Hotels list found");
        return this;
    }

    public String getPriceCurrency() {
        String price = driver.findElement(PRICE_LOCATOR).getText();
        return CurrencyFinder.getCurrency(price);
    }

    public MainPage setFilterFiveStars() {
        driver.findElement(FIVE_STARS_LOCATOR).click();
        return this;
    }

    public MainPage setFilterFreeWifi() {
        driver.findElement(FREE_WIFI_LOCATOR).click();
        return this;
    }

    public MainPage setFilterFlatHotelType() {
        hotelTypesList.click();
        driver.findElement(FLAT_FILTER_LOCATOR).click();
        return this;
    }

    public int findNumberOfSpecialOffers() {
        return driver.findElements(SPECIAL_DEAL_LOCATOR).size();
    }


    public int findNumberOfAvailableHotels() {
        return driver.findElement(HOTEL_LIST_LOCATOR).findElements(BOOK_HOTEL_BUTTON_LOCATOR).size();
    }

    public HotelPage goToHotelPage(int hotelNumberInList) {
        String hotelUrl;
        WebElement hotelLinkButton;
        if (hotelNumberInList < hotels.length) {
            hotelLinkButton = hotels[hotelNumberInList].
                    findElement(HOTEL_BUTTON_LOCATOR);
            logger.info("Hotel chosen correctly");
        } else {
            hotelLinkButton = hotels[0].
                    findElement(HOTEL_BUTTON_LOCATOR);
            logger.warn("No such hotel found. Taking first hotel");
        }
        hotelUrl = hotelLinkButton.getAttribute("href");
        hotelLinkButton.click();
        logger.info("Going to hotel page");
        return new HotelPage(hotelUrl).openPage();
    }

    public int getTotalFoundResultsNumber() {
        return hotels.length;
    }
}

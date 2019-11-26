package by.mmf.sharubapv.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends AbstractPage {
    private final Logger logger= LogManager.getRootLogger();
    private String baseUrl;

    @FindBy(xpath = "//*[@id='q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Найти')]")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@id='listings']/ol")
    private WebElement hotelsList;

    private WebElement[] hotels;

    private final By linkResultSearchHotelsListLocator = By.xpath("//*[@id='listings']/ol");

    public MainPage(WebDriver driver) {
        super(driver);
        baseUrl = driver.getCurrentUrl();
        PageFactory.initElements(this.driver, this);
        logger.info("Main page class created");
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
                .until(ExpectedConditions.presenceOfElementLocated(linkResultSearchHotelsListLocator))
                .findElements(By.xpath("//li[contains(class(), 'hotel')]")).toArray(new WebElement[0]);
        logger.info("Hotels list found");
        return this;
    }

    public HotelPage goToHotelPage(int hotelNumberInList) {
        String hotelUrl;
        WebElement hotelLinkButton;
        if (hotelNumberInList < hotels.length) {
            hotelLinkButton = hotels[hotelNumberInList].
                    findElement(By.xpath("//h3[contains(class(), 'p-name')]/a"));
            logger.info("Hotel chosen correctly");
        } else {
            hotelLinkButton = hotels[0].
                    findElement(By.xpath("//h3[contains(class(), 'p-name')]/a"));
            logger.warn("No such hotel found. Taking first hotel");
        }
        hotelUrl = hotelLinkButton.getAttribute("href");
        hotelLinkButton.click();
        logger.info("Going to hotel page");
        return new HotelPage(driver, hotelUrl).openPage();
    }

    public int getTotalFoundResultsNumber() {
        return hotels.length;
    }
}

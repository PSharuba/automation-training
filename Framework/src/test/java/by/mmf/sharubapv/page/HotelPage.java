package by.mmf.sharubapv.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelPage extends AbstractPage {

    private String baseUrl;

    private final By SIGN_IN_BUTTON_LOCATOR = By.xpath("//*[@id='header-sign-in']");
    private final By ACCOUNT_BUTTON_LOCATOR = By.xpath("//*[@class='nav-section-toggle']/label");
    private final By PAYMENT_MESSAGE_LOCATOR = By.xpath("/span[@class='payment-message']");
    private final By ONLINE_PAYMENT_LOCATOR = By.xpath("//*[@id='pay-now-etp-form']/button");
    private final String ROOM_BOOK_BUTTON_LOCATOR_PREFIX = "//*[@id='rooms-and-rates.room-";
    private final String ROOM_BOOK_BUTTON_LOCATOR_POSTFIX = "-rateplan-1']/button";

    @FindBy(xpath = "//*[@id='header-toggle-currency']")
    private WebElement moneyChangeButton;

    @FindBy(xpath = "//*[@id='q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Найти')]")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[contains(class(), 'rooms')]")
    private WebElement roomsList;

    private WebElement[] rooms;

    private final By linkRoomsListLocator = By.xpath("//ul[contains(class(), 'rooms')]");

    //private final By linkWidgetPaymentMethodLocator = By.xpath("//div[contains(class(), 'widget-overlay')]");

    public HotelPage(String baseUrl) {
        super();
        this.baseUrl = baseUrl;
        PageFactory.initElements(this.driver, this);
    }

    public HotelPage getRoomsList() {
        rooms = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(linkRoomsListLocator))
                .findElements(By.xpath("//li[contains(class(), 'room')]")).toArray(new WebElement[0]);
        logger.info("Got rooms list");
        return this;
    }

    public BookRoomPage goToRoomBookPage(int roomNumberInList) {
        WebElement bookRoomLinkButton;
        if (roomNumberInList < rooms.length) {
            bookRoomLinkButton = rooms[roomNumberInList].
                    findElement(By.xpath(ROOM_BOOK_BUTTON_LOCATOR_PREFIX + (roomNumberInList - 1) + ROOM_BOOK_BUTTON_LOCATOR_POSTFIX));
            logger.info("Got button for chosen room");
        } else {
            bookRoomLinkButton = rooms[0].
                    findElement(By.xpath(ROOM_BOOK_BUTTON_LOCATOR_PREFIX + 0 + ROOM_BOOK_BUTTON_LOCATOR_POSTFIX));
            logger.warn("No such room found. Taking first room");
        }
        try {
            driver.findElement(PAYMENT_MESSAGE_LOCATOR);
            driver.findElement(ONLINE_PAYMENT_LOCATOR).click();
        } catch (NoSuchElementException ignored) {
        }
        logger.info("Going to room booking page");
        return new BookRoomPage();
    }

    @Override
    public HotelPage openPage() {
        driver.navigate().to(baseUrl);
        getRoomsList();
        logger.info("Hotel page opened");
        return this;
    }
}

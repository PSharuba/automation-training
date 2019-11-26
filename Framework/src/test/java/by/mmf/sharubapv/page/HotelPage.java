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

    private final Logger logger= LogManager.getRootLogger();

    private String baseUrl;

    @FindBy(xpath = "//*[@id='q-destination']")
    private WebElement citySearchField;

    @FindBy(xpath = "//*[@id='q-localised-check-in']")
    private WebElement dateToEnterField;

    @FindBy(xpath = "//*[@id='q-localised-check-out']")
    private WebElement dateToLeaveField;

    @FindBy(xpath = "//button[contains(text(), 'Найти')]")
    //"//*[@id=\"hds-marquee\"]/div[2]/div[1]/div/form/div[4]/button")
    private WebElement searchButton;

    @FindBy(xpath = "//ul[contains(class(), 'rooms')]")
    private WebElement roomsList;

    private WebElement[] rooms;

    private final By linkRoomsListLocator = By.xpath("//ul[contains(class(), 'rooms')]");

    private final By linkWidgetPaymentMethodLocator = By.xpath("//div[contains(class(), 'widget-overlay')]");

    public HotelPage(WebDriver driver, String baseUrl) {
        super(driver);
        this.baseUrl = baseUrl;
        PageFactory.initElements(this.driver, this);
        logger.info("Hotel page class created");
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
                    findElement(By.xpath("//*[@id=\"rooms-and-rates.room-" + (roomNumberInList + 1) + "-rateplan-1\"]/button"));
            logger.info("Got button for chosen room");
        } else {
            bookRoomLinkButton = rooms[0].
                    findElement(By.xpath("//*[@id=\"rooms-and-rates.room-1-rateplan-1\"]/button"));
            logger.warn("No such room found. Taking first room");
        }
        if (checkIfPaymentWidgetExists()) {
            driver.findElement(By.xpath("//*[@id=\"pay-now-etp-form\"]/button")).click();
        }
        logger.info("Going to room booking page");
        return new BookRoomPage(driver);
    }

    private boolean checkIfPaymentWidgetExists() {
        logger.info("Checking payment methods");
        try {
            driver.findElement(linkWidgetPaymentMethodLocator);
        } catch (NoSuchElementException e) {
            logger.info("Only online payment");
            return false;
        }
        logger.info("Both ways of payment available");
        return true;

    }

    @Override
    public HotelPage openPage() {
        driver.navigate().to(baseUrl);
        getRoomsList();
        logger.info("Hotel page opened");
        return this;
    }
}

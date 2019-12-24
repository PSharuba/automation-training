package by.mmf.sharubapv.page;

import by.mmf.sharubapv.model.Guest;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.logging.log4j.Logger;

public class BookRoomPage extends AbstractPage {

    protected final int WAIT_TIMEOUT_SECONDS = 40;
    private String baseUrl;

    @FindBy(xpath = "//*[@id='headline']/h1")
    private WebElement finishPaymentHeader;

    @FindBy(xpath = "//*[@id='room-details-room-0-first-name']")
    private WebElement firstGuestFirstName;

    @FindBy(xpath = "//*[@id='room-details-room-0-last-name']")
    private WebElement firstGuestLastName;

    @FindBy(xpath = "//*[@id='room-details-room-0-other-guest-0-full-name']")
    private WebElement secondGuestName;

    @FindBy(xpath = "//*[@id='contact-details-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='contact-details-phone']")
    private WebElement contactPhoneField;

    private final By linkBookFormElementLocator = By.xpath("//*[@id='headline']/h1");

    public BookRoomPage() {
        super();
        baseUrl = driver.getCurrentUrl();
        PageFactory.initElements(this.driver, this);
    }

    public BookRoomPage bookRoom(Guest firstGuest, Guest secondGuest) {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(linkBookFormElementLocator));
        firstGuestFirstName.sendKeys(firstGuest.getFirstName());
        firstGuestLastName.sendKeys(firstGuest.getLastName());
        secondGuestName.sendKeys(secondGuest.getLastName() + " " + secondGuest.getLastName());
        emailField.sendKeys(firstGuest.getEmail());
        contactPhoneField.sendKeys(firstGuest.getPhone());
        logger.info("Guests personal data filled");
        return this;
    }

    public boolean isBookingComplete() {
        return !(finishPaymentHeader.getText().isEmpty());
    }

    @Override
    public BookRoomPage openPage() {
        driver.navigate().to(baseUrl);
        logger.info("URL: "+baseUrl);
        logger.info("Booking page opened");
        return this;
    }
}

package by.mmf.sharubapv.page;

import by.mmf.sharubapv.model.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends AbstractPage {

    private final String BASE_URL = "https://ru.hotels.com/profile/signin.html";

    @FindBy(xpath = "//*[@id='sign-in-email']")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='sign-in-password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    public LogInPage() {
        super();
        PageFactory.initElements(this.driver, this);
    }

    public SearchPage logIn(User user) {
        emailField.sendKeys(user.getEmail());
        passwordField.sendKeys(user.getPassword());
        submitButton.click();
        return new SearchPage();
    }

    @Override
    public LogInPage openPage() {
        driver.navigate().to(BASE_URL);
        logger.info("Log in page opened");
        return this;
    }
}

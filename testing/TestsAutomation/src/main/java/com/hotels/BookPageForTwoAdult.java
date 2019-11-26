package com.hotels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BookPageForTwoAdult {
    private WebDriver webDriver;
    private By firstNameField = By.xpath("//*[@id='room-details-room-0-first-name']");
    private By firstSurnameField = By.xpath("//*[@id='room-details-room-0-last-name']");
    private By secondNameField = By.xpath("//*[@id='room-details-room-0-other-guest-0-full-name']");

    private By emailField = By.xpath("//*[@id='contact-details-email']");
    private By phoneField = By.xpath("//*[@id='contact-details-phone']");
    private By spamCheckbox = By.xpath("//*[@id='contact-details-newsletter']");

    public BookPageForTwoAdult(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void book(String[] data) {
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        webDriver.findElement(firstNameField).clear();
        webDriver.findElement(firstNameField).sendKeys(data[0]);
        webDriver.findElement(firstSurnameField).clear();
        webDriver.findElement(firstSurnameField).sendKeys(data[1]);
        webDriver.findElement(secondNameField).clear();
        webDriver.findElement(secondNameField).sendKeys(data[2] + " " + data[3]);

        webDriver.findElement(emailField).clear();
        webDriver.findElement(emailField).sendKeys(data[4]);
        webDriver.findElement(phoneField).clear();
        webDriver.findElement(phoneField).sendKeys(data[5]);
        webDriver.findElement(spamCheckbox).click();
    }
}

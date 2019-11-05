package com.hotels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class HotelPage {
    private WebDriver webDriver;
    private By bookButton = By.xpath("//*[@id='rooms-and-rates.room-1-rateplan-1']/button[@type='submit']");
    private By payInHotelButton = By.xpath("//*[@id='pay-later-etp-form']/button");

    public HotelPage(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public BookPageForTwoAdult startBooking() {
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        if (webDriver.findElements(By.xpath("//*[@id='rooms-and-rates.room-1-rateplan-1']/span")).size() > 0) {
            webDriver.findElement(bookButton).click();
            webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
            webDriver.findElement(payInHotelButton).click();
        } else webDriver.findElement(bookButton).click();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(webDriver.getCurrentUrl());
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return new BookPageForTwoAdult(webDriver);
    }
}

package com.hotels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

//  https://ru.hotels.com/

public class HotelsCom {
    private WebDriver webDriver;
    private By searchButton = By.xpath("//*[@id='hds-marquee']/div[2]/div[1]/div/form/div[5]/button");
    //private By searchButton = By.xpath("//*[@class='widget-query-group widget-query-ft']/button");
    private By citySearchBox = By.name("q-destination");
    private By checkInBox = By.name("q-localised-check-in");
    private By checkOutBox = By.name("q-localised-check-out");
    private By hotelButton = By.xpath("//*[@id='listings']/ol/li[1]/article/section/aside/a");

    public HotelsCom(final WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public HotelPage enterDataInFieldsForBooking(String[] data) {
        fieldsFill(data);

        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        String nextUrl = webDriver.findElement(hotelButton).getAttribute("href");
        webDriver.get(nextUrl);

        return new HotelPage(webDriver);
    }

    public String fillFieldsToGetError(String[] data) {
        fieldsFill(data);

        return webDriver.findElement(By.xpath("//*[@id='widget-overlay-title-1']")).getText();
    }

    private void fieldsFill(String[] data) {
        webDriver.findElement(citySearchBox).clear();
        webDriver.findElement(citySearchBox).sendKeys(data[0]);

        webDriver.findElement(checkInBox).clear();
        webDriver.findElement(checkInBox).sendKeys(data[1]);

        webDriver.findElement(By.xpath("//*[@id='hds-marquee']/div[2]/div[1]/div/h1")).click();
        webDriver.findElement(searchButton).click();
    }
}

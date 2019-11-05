import com.hotels.HotelsCom;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test {
    private static WebDriver webDriver;

    private String getCurrentDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        int day = calendar.get(GregorianCalendar.DATE) + 1;
        int month = calendar.get(GregorianCalendar.MONTH) + 1;
        String dayS;
        String monthS;
        int year = calendar.get(GregorianCalendar.YEAR);
        if (day < 10) {
            dayS = "0" + day;
        } else {
            dayS = "" + day;
        }
        if (month < 10) {
            monthS = "0" + month;
        } else {
            monthS = "" + month;
        }
        return dayS + '/' + monthS + '/' + year;
    }

    private static void initDriver() throws MalformedURLException {
        webDriver = new RemoteWebDriver(new URL("http://localhost:9515"), DesiredCapabilities.chrome());
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    private static void launchApp() {
        webDriver.get("https://ru.hotels.com/");
    }

    private static void shutDownDriver() {
        webDriver.close();
        webDriver.quit();
    }


    private boolean bookRoom() {
        HotelsCom hotelsCom = new HotelsCom(webDriver);
        String[] startData = new String[2];
        String[] bookData = new String[6];
        startData[0] = "Минск";
        startData[1] = getCurrentDate();//"10/11/2019";
        bookData[0] = "Pavel";
        bookData[1] = "Sharuba";
        bookData[2] = "Vsevolod";
        bookData[3] = "Shatalov";
        bookData[4] = "mynameisfich@gmail.com";
        bookData[5] = "+375445698346";
        hotelsCom.enterDataInFieldsForBooking(startData)
                .startBooking()
                .book(bookData);
        return true;
    }

    private String getError() {
        HotelsCom hotelsCom = new HotelsCom(webDriver);
        String[] startData = new String[2];
        startData[0] = "МинскСтолицаСША";
        startData[1] = getCurrentDate();//"10/11/2019";
        return hotelsCom.fillFieldsToGetError(startData);
    }

    @org.junit.jupiter.api.Test
    public void checkErrorMessage() throws MalformedURLException {

        initDriver();
        launchApp();
        assertEquals(this.getError(), "К сожалению, нам не удалось найти МинскСтолицаСША");
        shutDownDriver();
    }

    @org.junit.jupiter.api.Test
    public void checkAbilityToBookRoom() throws MalformedURLException {
        initDriver();
        launchApp();
        assertTrue(this.bookRoom());
        shutDownDriver();
    }

}

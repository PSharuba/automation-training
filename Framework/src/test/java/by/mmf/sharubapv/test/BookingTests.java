package by.mmf.sharubapv.test;

import by.mmf.sharubapv.model.Guest;
import by.mmf.sharubapv.model.SearchQuery;
import by.mmf.sharubapv.page.SearchPage;
import by.mmf.sharubapv.service.GuestCreator;
import by.mmf.sharubapv.service.SearchQueryCreator;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class BookingTests extends CommonConditions {
    /*@Test
    public void oneCanBookRoomForTwoAdults() {
        logger.info("Started test.");
        Guest firstGuest = GuestCreator.withCredentialsFromProperty(0);
        Guest secondGuest = GuestCreator.withCredentialsFromProperty(1);
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty();
        boolean isSearchComplete = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .goToHotelPage(0)
                .openPage()
                .goToRoomBookPage(0)
                .bookRoom(firstGuest, secondGuest)
                .isBookingComplete();
        assertThat(isSearchComplete, is(equalTo(true)));
    }*/
}

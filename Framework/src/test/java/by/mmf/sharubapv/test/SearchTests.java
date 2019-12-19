package by.mmf.sharubapv.test;

import by.mmf.sharubapv.model.SearchQuery;
import by.mmf.sharubapv.page.SearchPage;
import by.mmf.sharubapv.service.DateService;
import by.mmf.sharubapv.service.SearchQueryCreator;
import by.mmf.sharubapv.util.ExistingCities;
import by.mmf.sharubapv.util.StringUtils;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;


public class SearchTests extends CommonConditions {
    private static final int RANDOM_CITY_NAME_LENGTH = 20;
    private static final String EXPECTED_ERROR_MESSAGE_PREFIX = "К сожалению, нам не удалось найти ";
    private static final String EXPECTED_ALERT_MESSAGE = "Указана дата заезда раньше сегодняшнего дня";

    //+
    @Test
    public void searchForRandomStringGivesError() {
        String randomCityName = StringUtils.getRandomString(RANDOM_CITY_NAME_LENGTH);

        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(randomCityName);
        String textOfReceivedError = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .getErrorMessage();

        assertThat(textOfReceivedError, containsString(randomCityName));
    }

    // +
    @Test
    public void searchForExistingCityGivesResult() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(ExistingCities.MINSK.name());
        int totalFoundResultsNumber = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .getTotalFoundResultsNumber();
        assertThat(0, is(lessThan(totalFoundResultsNumber)));
    }

    // +
    @Test
    public void mustBeAtLeastOneRoomForTomorrow() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(DateService.getNextDayDate(),
                DateService.getNextWeekDate());
        int totalAvailableResultsNumber = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .findNumberOfAvailableHotels();
        assertThat(0, is(lessThan(totalAvailableResultsNumber)));
    }

    // +
    @Test
    public void mustBeAtLeastOneFlatFound() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty();
        int totalAvailableFlatsFound = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .setFilterFlatHotelType()
                .findNumberOfAvailableHotels();
        assertThat(0, is(lessThan(totalAvailableFlatsFound)));
    }

    // +
    @Test
    public void mustBeAtLeastOneFiveStarRoomWithWifiFound() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty();
        int totalAvailableRoomsFound = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .setFilterFiveStars()
                .setFilterFreeWifi()
                .findNumberOfAvailableHotels();
        assertThat(0, is(lessThan(totalAvailableRoomsFound)));
    }

    // +
    @Test
    public void mustNotBeAnyRoomForLastWeek() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(DateService.getLastWeekDate(),
                DateService.getNextWeekDate());
        String alertMessage = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .getDateAlertMessage();
        assertThat(alertMessage, is(equalTo(EXPECTED_ALERT_MESSAGE)));
    }

    // +
    @Test
    public void searchInSpecialOffersMustFindAtLeastOneSpecialOffer() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty();
        int totalSpecialOffersFound = new SearchPage()
                .openPage()
                .goToSpecialOffers()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .findNumberOfSpecialOffers();
        assertThat(0, is(lessThan(totalSpecialOffersFound)));
    }

    // +
    @Test
    public void whenMoneyChangedToUsdPricesMustBeAtUsd() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty();
        String currencyOfPrices = new SearchPage()
                .openPage()
                .changeMoneyToUSD()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .getPriceCurrency();
        assertThat(currencyOfPrices, is(equalTo("USD")));
    }

}

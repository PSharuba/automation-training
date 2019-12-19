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

public class SingleTest {
    private static final int RANDOM_CITY_NAME_LENGTH = 20;
    private static final String EXPECTED_ERROR_MESSAGE_PREFIX = "К сожалению, нам не удалось найти ";
    private static final String DATE_IN = "25/12/2019";
    private static final String DATE_OUT = "05/01/2020";
    private static final String CITY_NAME = "Minsk";
    private static final String EXPECTED_ALERT_MESSAGE = "Указана дата заезда раньше сегодняшнего дня";
    //  CITY_NAME,DATE_IN,DATE_OUT

    @Test
    public void searchForRandomStringGivesError() {
        String randomCityName = StringUtils.getRandomString(RANDOM_CITY_NAME_LENGTH);

        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(randomCityName,DATE_IN,DATE_OUT);
        String textOfReceivedError = new SearchPage()
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .getErrorMessage();

        assertThat(textOfReceivedError, containsString(randomCityName));
    }
}

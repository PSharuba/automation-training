package by.mmf.sharubapv.test;

import by.mmf.sharubapv.model.Guest;
import by.mmf.sharubapv.model.SearchQuery;
import by.mmf.sharubapv.page.SearchPage;
import by.mmf.sharubapv.service.SearchQueryCreator;
import by.mmf.sharubapv.util.StringUtils;
import by.mmf.sharubapv.service.GuestCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.testng.Assert.assertTrue;


public class SearchTests extends CommonConditions {
    protected static final int RANDOM_CITY_NAME_LENGTH = 20;
    protected static final String EXPECTED_ERROR_MESSAGE_PREFIX = "К сожалению, нам не удалось найти ";

    @Test
    public void searchForRandomStringGivesError() {
        String randomCityName = StringUtils.getRandomString(RANDOM_CITY_NAME_LENGTH);

        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(randomCityName);
        String textOfReceivedError = new SearchPage(driver)
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .getErrorMessage();

        assertThat(textOfReceivedError, is(equalTo(EXPECTED_ERROR_MESSAGE_PREFIX + randomCityName)));
    }

    @Test
    public void searchForExistingCityGivesResult() {
        SearchQuery searchQuery = SearchQueryCreator.withCredentialsFromProperty(ExistingCities.MINSK.name());
        int totalFoundResultsNumber = new SearchPage(driver)
                .openPage()
                .enterDataToSearchFor(searchQuery)
                .clickSearchButton()
                .getTotalFoundResultsNumber();
        assertTrue(totalFoundResultsNumber > 0);
    }


}

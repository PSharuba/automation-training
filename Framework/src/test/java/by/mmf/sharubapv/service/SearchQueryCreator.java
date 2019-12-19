package by.mmf.sharubapv.service;

import by.mmf.sharubapv.model.SearchQuery;

public class SearchQueryCreator {
    public static final String TESTDATA_QUERY_CITY = "testdata.query.city";
    public static final String TESTDATA_QUERY_DATE_IN = "testdata.query.datein";
    public static final String TESTDATA_QUERY_DATE_OUT = "testdata.query.dateout";

    public static SearchQuery withCredentialsFromProperty() {
        return new SearchQuery(TestDataReader.getTestData(TESTDATA_QUERY_CITY),
                TestDataReader.getTestData(TESTDATA_QUERY_DATE_IN),
                TestDataReader.getTestData(TESTDATA_QUERY_DATE_OUT));
    }

    public static SearchQuery withCredentialsFromProperty(String searchCityName) {
        return new SearchQuery(searchCityName,
                TestDataReader.getTestData(TESTDATA_QUERY_DATE_IN),
                TestDataReader.getTestData(TESTDATA_QUERY_DATE_OUT));
    }

    public static SearchQuery withCredentialsFromProperty(String dateIn, String dateOut) {
        return new SearchQuery(TestDataReader.getTestData(TESTDATA_QUERY_CITY), dateIn, dateOut);
    }

    public static SearchQuery withCredentialsFromProperty(String searchCityName,String dateIn, String dateOut) {
        return new SearchQuery(searchCityName, dateIn, dateOut);
    }
}

package by.mmf.sharubapv.service;

import by.mmf.sharubapv.model.Guest;

public class GuestCreator {

    public static final String TESTDATA_GUEST_FIRSTNAME = "testdata.user.name";
    public static final String TESTDATA_GUEST_LASTNAME = "testdata.user.surname";
    public static final String TESTDATA_GUEST_EMAIL = "testdata.user.email";
    public static final String TESTDATA_GUEST_PHONE = "testdata.user.phone";

    public static Guest withCredentialsFromProperty(int guestNumber) {
        return new Guest(TestDataReader.getTestData(TESTDATA_GUEST_FIRSTNAME + guestNumber),
                TestDataReader.getTestData(TESTDATA_GUEST_LASTNAME + guestNumber),
                TestDataReader.getTestData(TESTDATA_GUEST_EMAIL + guestNumber),
                TestDataReader.getTestData(TESTDATA_GUEST_PHONE + guestNumber));
    }
}

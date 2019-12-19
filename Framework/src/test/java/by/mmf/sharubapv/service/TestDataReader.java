package by.mmf.sharubapv.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        System.out.println("Environment: " + System.getProperty("environment"));
        return RESOURCE_BUNDLE.getString(key);
    }
}

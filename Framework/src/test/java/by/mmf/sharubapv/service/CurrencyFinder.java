package by.mmf.sharubapv.service;

public class CurrencyFinder {
    public static String getCurrency(String price) {
        if (price.contains("$"))
            return "USD";
        if (price.contains("€"))
            return "EUR";
        if (price.contains("RUB"))
            return "RUB";
        return "No currency found";
    }
}

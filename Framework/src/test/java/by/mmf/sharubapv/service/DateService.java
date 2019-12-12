package by.mmf.sharubapv.service;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateService {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public static String getCurrentDate() {
        Date currentDate = new Date();
        return dateFormat.format(currentDate);
    }

    public static String getNextDayDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return dateFormat.format(calendar.getTime());
    }

    public static String getNextWeekDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        return dateFormat.format(calendar.getTime());
    }

    public static String getLastWeekDate() {
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DAY_OF_MONTH, -7);
        return dateFormat.format(calendar.getTime());
    }
}

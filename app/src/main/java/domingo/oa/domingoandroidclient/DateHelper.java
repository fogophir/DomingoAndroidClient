package domingo.oa.domingoandroidclient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by ophir-pc on 08/08/2014.
 */
public class DateHelper {

    private static SimpleDateFormat fullDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private static SimpleDateFormat todayDateFormat = new SimpleDateFormat("HH:mm");

    public static String unixToDate(String unix_timestamp) {
        long timestamp = Long.parseLong(unix_timestamp);
        java.util.Date time = new java.util.Date(timestamp*1000);

        Calendar today = Calendar.getInstance(); // today
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(time);

        // if today - only get the time back
        if (today.get(Calendar.YEAR) == cal1.get(Calendar.YEAR) &&
                today.get(Calendar.DAY_OF_YEAR) == cal1.get(Calendar.DAY_OF_YEAR)){
            return todayDateFormat.format(cal1.getTime());
        }
        else { // if not today, showing full date
            return fullDateFormat.format(cal1.getTime());
        }
    }
}

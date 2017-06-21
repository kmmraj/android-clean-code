package util;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * Created by mkaratadipalayam on 20/06/17.
 */

public class CalendarUtil {

    @NonNull
    public static Calendar getCalendar(String date) {
        //Date should be in the format YYYY/MM/DD if not return
        if (date != null && !date.isEmpty() && date.length() == 10 ) {
            int year = Integer.parseInt(date.substring(0, 4));
            int month = Integer.parseInt(date.substring(5, 7));
            int day = Integer.parseInt(date.substring(8, 10));
            Calendar startingTime = Calendar.getInstance();
            startingTime.set(year, month - 1, day, 0, 0, 0);
            return startingTime;
        }
        return null;
    }

    public static  long getDaysDiff(long startTime,long endTime) {
        long msDiff;
        if (endTime > startTime) {
            msDiff = endTime - startTime;
        } else {
            msDiff = startTime - endTime;
        }
        // Log.e(TAG,"diff is  "+ daysDiff);
        return TimeUnit.MILLISECONDS.toDays(msDiff);
    }
}

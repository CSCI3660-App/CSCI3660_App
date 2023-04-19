package com.zybooks.csci3660app;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;

public class SetCalendarEvents {

    public static ArrayList<EventDay> mEventList = new ArrayList<>();
    private static int mEventDay;

    public static int eventTimeSet(int time) {
        int mEventTime = time;
        return mEventTime;
    }

    public static void setEventDate(int day) {
        mEventDay = day;
    }

    public static int getEventDate() {
        return mEventDay;
    }

    public static void createCalendarEvents() {

        Calendar baseDate = Calendar.getInstance();
        baseDate.set(2023, Calendar.APRIL, 1); // Set to April 1, 2023

// Calculate the number of days between the reference date and the event date
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(2023, Calendar.APRIL, getEventDate()); // Set to April 10, 2023
        long timeDiff = eventDate.getTimeInMillis() - baseDate.getTimeInMillis();
        int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));

// Add the event to the calendar on the appropriate day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, daysDiff);
        mEventList.add(new EventDay(calendar, R.drawable.ic_dashboard_black_24dp));

        /*Calendar eventDate1 = Calendar.getInstance();
        eventDate1.set(2023, Calendar.APRIL, 12); // Set to April 10, 2023
        long timeDiff1 = eventDate1.getTimeInMillis() - baseDate.getTimeInMillis();
        int daysDiff1 = (int) (timeDiff1 / (24 * 60 * 60 * 1000));

// Add the event to the calendar on the appropriate day
        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(baseDate.getTime());
        calendar1.add(Calendar.DAY_OF_MONTH, daysDiff1);
        mEventList.add(new EventDay(calendar1, R.drawable.ic_dashboard_black_24dp));*/

        /*Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 0);
        mEventList.add(new EventDay(calendar1, R.drawable.ic_home_black_24dp));

        Calendar calendar2 = Calendar.getInstance();
        calendar2.add(Calendar.DAY_OF_MONTH, 7);
        mEventList.add(new EventDay(calendar2, R.drawable.ic_dashboard_black_24dp));

        Calendar calendar3 = Calendar.getInstance();
        calendar3.add(Calendar.DAY_OF_MONTH, 8);
        mEventList.add(new EventDay(calendar3, R.drawable.ic_notifications_black_24dp));

        Calendar calendar4 = Calendar.getInstance();
        calendar4.add(Calendar.DAY_OF_MONTH, 10);
        mEventList.add(new EventDay(calendar4, R.drawable.ic_notifications_black_24dp));*/
    }
}

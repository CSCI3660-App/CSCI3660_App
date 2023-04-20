package com.zybooks.csci3660app;

import android.content.Context;
import android.content.SharedPreferences;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class SetCalendarEvents {

    public static ArrayList<EventDay> mEventList = new ArrayList<>();
    //public static ArrayList<String> mEventListStrings = new ArrayList<>();
    private static int mEventDay;
    private static String eventString;
    public static EventList mEventListString;

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

/*// Calculate the number of days between the reference date and the event date
        Calendar eventDate = Calendar.getInstance();
        eventDate.set(2023, Calendar.APRIL, getEventDate()); // Set to April 10, 2023
        long timeDiff = eventDate.getTimeInMillis() - baseDate.getTimeInMillis();
        int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));

// Add the event to the calendar on the appropriate day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, daysDiff);
        mEventList.add(new EventDay(calendar, R.drawable.ic_dashboard_black_24dp));*/

        if (mEventListString.size()>0) {
            for (int i=0; i<mEventListString.size(); i++) {
                eventString = mEventListString.getItems()[i];
                if (eventString.compareTo(CreateReminders.item)==0) {
                    Calendar eventDate = Calendar.getInstance();
                    eventDate.set(2023, Calendar.APRIL, getEventDate());
                    long timeDiff = eventDate.getTimeInMillis() - baseDate.getTimeInMillis();
                    int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(baseDate.getTime());
                    calendar.add(Calendar.DAY_OF_MONTH, daysDiff);
                    mEventList.add(new EventDay(calendar, R.drawable.ic_dashboard_black_24dp));
                }
            }
        }

    }
    /*public static void buildEventString() {

        for (int i=0; i<mEventListStrings.size(); i++) {
            eventString = mEventListStrings.get(i);
            if (eventString.compareTo(CreateReminders.item)==0) {

            }
        }
    }*/

}

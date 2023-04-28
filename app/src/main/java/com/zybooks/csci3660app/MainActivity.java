package com.zybooks.csci3660app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {


    TextView textview;
    ImageButton buttonRefresh;
    static String currentDay;
    private TextView mItemListTextView;
    static int currentMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendarView);
        mItemListTextView = findViewById(R.id.item_list);

        calendarView.setOnDayClickListener(eventDay -> {
            currentDay = (eventDay.getCalendar().get(Calendar.MONTH) + 1) + "." + eventDay.getCalendar().get(Calendar.DAY_OF_MONTH) + "."
                    + eventDay.getCalendar().get(Calendar.YEAR);
        });

        calendarView.setOnDayLongClickListener(eventDay ->
        {
            Intent intent = new Intent(this, ListActivity.class);
            String newDate = (eventDay.getCalendar().get(Calendar.MONTH) + 1) + "." + eventDay.getCalendar().get(Calendar.DAY_OF_MONTH) + "."
                    + eventDay.getCalendar().get(Calendar.YEAR);

            intent.putExtra("DAY", newDate);

            startActivity(intent);
        });

        populateAllCalendarDays();
        displayReminders();
    }

    public void displayReminders() {
        String[] calendarDays = populateAllCalendarDays();
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        SharedPreferences times = getSharedPreferences("myTimes", MODE_PRIVATE);
        ArrayList<String> stringList;
        ArrayList<String> timesList;
        StringBuffer itemText = new StringBuffer();
        for (int i = 0; i < calendarDays.length; i++) {
            stringList = new ArrayList<>(data.getStringSet(calendarDays[i], new HashSet<>()));
            timesList = new ArrayList<>(times.getStringSet(calendarDays[i], new HashSet<>()));
            if (stringList.isEmpty()) {
                itemText.append("");
            }
            else {
                itemText.append("\n" + calendarDays[i]).append("\n").append("__________").append("\n");
                int loopCount = Math.min(stringList.size(), timesList.size());
                for (int j = 0; j < loopCount; j++) {
                    itemText.append(stringList.get(j) + "  - ").append(timesList.get(j)).append("\n");
                }
            }
            mItemListTextView.setText(itemText);
        }
    }

    public String[] populateAllCalendarDays() {
        String[] allCalendarDays = new String[372];
        int count=0;
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 31; j++) {
                allCalendarDays[count] = String.format("%d.%d.2023", i+1, j+1);
                count++;
            }
        }
        return allCalendarDays;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    protected void onResume() {
        super.onResume();
        displayReminders();
    }

}
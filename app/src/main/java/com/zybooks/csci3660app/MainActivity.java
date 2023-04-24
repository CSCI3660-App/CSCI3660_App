package com.zybooks.csci3660app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {


    TextView textview;
    ImageButton buttonRefresh;
    static String currentDay;
    private TextView mItemListTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CalendarView calendarView = findViewById(R.id.calendarView);
        mItemListTextView = findViewById(R.id.item_list);

        /*calendarView.setOnDayClickListener(eventDay ->
                *//*Toast.makeText(getApplicationContext(), eventDay.getCalendar().getTime().toString() + " "
                        + eventDay.isEnabled(), Toast.LENGTH_SHORT).show()*//*
                currentDay = eventDay.getCalendar().toString(),
                displayReminders());*/
        calendarView.setOnDayClickListener(eventDay -> {
            currentDay = eventDay.getCalendar().get(Calendar.MONTH) + "." + eventDay.getCalendar().get(Calendar.DAY_OF_MONTH) + "."
                    + eventDay.getCalendar().get(Calendar.YEAR);

        });

        calendarView.setOnDayLongClickListener(eventDay ->
        {
            Intent intent = new Intent(this, ListActivity.class);
            String newDate = eventDay.getCalendar().get(Calendar.MONTH) + "." + eventDay.getCalendar().get(Calendar.DAY_OF_MONTH) + "."
                    + eventDay.getCalendar().get(Calendar.YEAR);

            intent.putExtra("DAY", newDate);

            startActivity(intent);
        });

        displayReminders();
    }

    public void displayReminders() {
        String[] calendarDays = new String[]{"3.1.2023", "3.2.2023", "3.3.2023", "3.4.2023", "3.5.2023", "3.6.2023", "3.7.2023", "3.8.2023"
                , "3.9.2023", "3.10.2023", "3.11.2023", "3.12.2023", "3.13.2023", "3.14.2023", "3.15.2023", "3.16.2023", "3.17.2023", "3.18.2023", "3.19.2023"
                , "3.20.2023", "3.21.2023", "3.22.2023", "3.23.2023", "3.24.2023", "3.25.2023", "3.26.2023", "3.27.2023", "3.28.2023", "3.29.2023", "3.30.2023"
                , "3.31.2023"};
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        SharedPreferences times = getSharedPreferences("myTime", MODE_PRIVATE);
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
                itemText.append(calendarDays[i]).append("\n");
                int loopCount = Math.min(stringList.size(), timesList.size());
                for (int j = 0; j < loopCount; j++) {
                    itemText.append(stringList.get(j) + " - ").append(timesList.get(j)).append("\n");
                }
            }
            mItemListTextView.setText(itemText);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}
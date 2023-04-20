package com.zybooks.csci3660app;

import static com.zybooks.csci3660app.SetCalendarEvents.getEventDate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.metrics.Event;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.DatePicker;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.builders.DatePickerBuilder;
import com.applandeo.materialcalendarview.listeners.OnSelectDateListener;
import com.google.gson.JsonSerializationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    SharedPreferences prefs;
    Context context;
    ImageButton buttonRefresh;
    ImageButton buttonSave;
    TextView textView;
    String eventString;
    ArrayList<EventDay> mEventList = new ArrayList<>();
    public EventList mEventListStrings = new EventList(context);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mEventListStrings = getEventListStrings();
        prefs = getSharedPreferences("events", Context.MODE_PRIVATE);

        /*buttonSave = findViewById(R.id.save);
        buttonSave.setOnClickListener(vew -> saveData());*/

        buttonRefresh = findViewById(R.id.refresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDayClickListener(eventDay ->
                Toast.makeText(getApplicationContext(), eventDay.getCalendar().getTime().toString() + " "
                        + eventDay.isEnabled(), Toast.LENGTH_SHORT).show());

        /*SharedPreferences prefs = getSharedPreferences("events", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("events", String.valueOf(mEventListStrings));
        editor.commit();*/

        /*SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Set<String> set = sharedPreferences.getStringSet("events", null);
        if (set != null) {
            mEventListStrings.addAll(set);
        }*/

        createCalendarEvents();
        setCalendarEvents();
        createEventString();

        textView = findViewById(R.id.text);
        if (!mEventListStrings.isEmpty()) {
            textView.setText(mEventListStrings.get(0));
        }
        else {
            textView.setText("");
        }
    }



    public void setCalendarEvents() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(mEventList);
    }

    public void createEventString() {
        //mEventListStrings.add("hey");
    }

    public void createCalendarEvents() {

        Calendar baseDate = Calendar.getInstance();
        baseDate.set(2023, Calendar.APRIL, 1); // Set to April 1, 2023

// Calculate the number of days between the reference date and the event date
        /*Calendar eventDate = Calendar.getInstance();
        eventDate.set(2023, Calendar.APRIL, getEventDate()); // Set to April 10, 2023
        long timeDiff = eventDate.getTimeInMillis() - baseDate.getTimeInMillis();
        int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));

// Add the event to the calendar on the appropriate day
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(baseDate.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, daysDiff);
        mEventList.add(new EventDay(calendar, R.drawable.ic_dashboard_black_24dp));*/

        if (mEventListStrings.size()==0) {
            Calendar eventDate = Calendar.getInstance();
            eventDate.set(2023, Calendar.APRIL, getEventDate());
            long timeDiff = eventDate.getTimeInMillis() - baseDate.getTimeInMillis();
            int daysDiff = (int) (timeDiff / (24 * 60 * 60 * 1000));

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(baseDate.getTime());
            calendar.add(Calendar.DAY_OF_MONTH, daysDiff);
            mEventList.add(new EventDay(calendar, R.drawable.ic_dashboard_black_24dp));
        }
        else {
            for (int i=0; i<mEventListStrings.size(); i++) {
                eventString = mEventListStrings.get(i);
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


    public void createReminder(View view){
        Intent intent = new Intent(this, CreateReminders.class);
        startActivity(intent);
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    /*protected void onStop() {
        super.onStop();

        // Save list to SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("list", new HashSet<>(mEventListStrings));
        editor.apply();
    }*/

    /*public void setEventListStrings(ArrayList<String> events) {
        mEventListStrings = events;
    }

    public ArrayList<String> getEventListStrings() {
        return mEventListStrings;
    }*/
}
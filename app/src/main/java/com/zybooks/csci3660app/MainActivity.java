package com.zybooks.csci3660app;

import static com.zybooks.csci3660app.SetCalendarEvents.mEventList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    TextView textview;
    ImageButton buttonRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textview = findViewById(R.id.text);
        textview.setText("Click here to recall activity");
        textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                finish();
                overridePendingTransition(0, 0);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

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

        SetCalendarEvents.createCalendarEvents();
        setCalendarEvents();
    }

    public void setCalendarEvents() {
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(mEventList);
    }



    public void createReminder(View view){
        Intent intent = new Intent(this, CreateReminders.class);
        startActivity(intent);
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
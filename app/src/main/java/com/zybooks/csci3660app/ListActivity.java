package com.zybooks.csci3660app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.HashSet;


public class ListActivity extends AppCompatActivity {
    public static final int LISTNNUM = 5;
    private TextView[] listViews;
    private ImageButton[] buttonViews;
    boolean[] viewVisible = new boolean[LISTNNUM];
    FloatingActionButton addButton;
    private String currentText;
    boolean editing;


    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        Intent intent = getIntent();
        String currentDay = intent.getStringExtra("DAY");

        // Initializing the add button, setting the onclick listener
        addButton = findViewById(R.id.floatingActionButton2);
        addButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              onAdd(currentDay);
          }
      });

        // Initializing the arrays, listViews contains the edittext, buttonViews contains the X close buttons
        // There is another Array of booleans I declared earlier that contains a true or false for each listview/buttonview combo, determining if they are visible or hidden
        listViews = new TextView[LISTNNUM];
        listViews[0] = findViewById(R.id.rem1);
        listViews[1] = findViewById(R.id.rem2);
        listViews[2] = findViewById(R.id.rem3);
        listViews[3] = findViewById(R.id.rem4);
        listViews[4] = findViewById(R.id.rem5);

        buttonViews = new ImageButton[LISTNNUM];
        buttonViews[0] = findViewById(R.id.rem1minus);
        buttonViews[1] = findViewById(R.id.rem2minus);
        buttonViews[2] = findViewById(R.id.rem3minus);
        buttonViews[3] = findViewById(R.id.rem4minus);
        buttonViews[4] = findViewById(R.id.rem5minus);

        // This iterates through buttonViews to set an onclick listener for each one
        for (int i = 0; i < LISTNNUM; i++) {
            int current = i;
            buttonViews[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Calls removeReminder, sets the current view to false, and resets the text
                    viewVisible[current] = false;
                    removeReminder(currentDay, listViews[current]);
                    //listViews[current].setText("zSample Reminder");
                    fixText();
                    setVisibility();
                }
            });

            //Still inside the for loop, this creates a tempview variable that contains the current view so I can pass it into the edittext listener
            TextView tempView = listViews[i];

            //The following chunk closes the keyboard when the user clicks enter to stop typing, as well as a few other things
            tempView.setHorizontallyScrolling(false);
            tempView.setMaxLines(3);
            listViews[i].setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_DONE) {
                        String enteredText = tempView.getText().toString();
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(tempView.getWindowToken(), 0);
                        tempView.clearFocus();
                        updateReminder(currentDay, enteredText, currentText);
                        editing = false;
                        return true;
                    }
                    return false;
                }
            });

            //This sets an ontouch listener that returns the text of the current view to the currentText variable when the edittext is first touched
            //I use this later to determine which index to update/remove in the sharedpreferences stringsets
            listViews[i].setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (!editing) {
                        editing = true;
                        currentText = tempView.getText().toString();
                        Log.d("TOUCH CALLED", currentText);
                    }
                    return false;
                }
            });
        }

        //clears the screen initially, then calls setData for the current day to display that day's reminders
        clear();
        setVisibility();
        setData(currentDay);

    }

    // Clears the screen
    public void clear() {
        for (int i = 0; i < LISTNNUM; i++) {
            viewVisible[i] = false;
        }
    }
    // Iterates through the array, and sets each button's visibility based on the corresponding boolean value in viewVisible
    public void setVisibility() {
        for (int i = 0; i < LISTNNUM; i++) {
            if (viewVisible[i]) {
                listViews[i].setVisibility(View.VISIBLE);
                buttonViews[i].setVisibility(View.VISIBLE);
            }
            else {
                listViews[i].setVisibility(View.GONE);
                buttonViews[i].setVisibility(View.GONE);
            }
        }
    }
    // onAdd is called with the current date when the add button is clicked, I commented out some debugging logs
    public void onAdd (String day) {
        int index = readFromReminder(day).size();
        if (index < LISTNNUM){
            clear();
            addReminder(day, "zSample Reminder");
            viewVisible[index] = true;
         //   Log.d("onAdd", day + " size of data array = " + index);
         //   Log.d("onAdd", "Array Contents: " + readFromReminder(day).toString());
         //   Log.d("onAdd", "everything ran");
        }

    }
    // Returns an arraylist of strings from the current date key in sharedpreferences mydata.xml
    public ArrayList<String> readFromReminder(String day){
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        return new ArrayList<>(data.getStringSet(day, new HashSet<>()));
    }
    // Sets the visibility and text of each reminder according to the corresponding size of the stringlist
    public void setData(String day) {

        ArrayList<String> stringList = readFromReminder(day);
        for (int i = 0; i < (stringList.size()); i++ ) {
            viewVisible[i] = true;
            listViews[i].setText(stringList.get(i));
        }
        setVisibility();
    }
    // Creates an arraylist of strings then pushes it to the mydata.xml, then calls setdata to update the visibility
    public void addReminder(String day, String text) {
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        ArrayList<String> stringList = new ArrayList<>(data.getStringSet(day, new HashSet<>()));
        stringList.add(text);
        SharedPreferences.Editor editor = data.edit();
        editor.putStringSet(day, new HashSet<>(stringList));
        editor.apply();
        for(int i = 0; i < readFromReminder(day).size(); i++){
            viewVisible[i] = true;
        }
        setData(day);
    }
    //This code runs when the user finishes editing a text, and updates the xml file
    public void updateReminder(String day, String newText, String oldText) {
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        ArrayList<String> stringList = new ArrayList<>(data.getStringSet(day, new HashSet<>()));
            int index = stringList.indexOf(oldText);
            if (index != -1){
            stringList.set(index, newText);
            SharedPreferences.Editor editor = data.edit();
            editor.putStringSet(day, new HashSet<>(stringList));
            editor.apply();
                clear();
                setData(day);
        }}

    // This is called when a remove button is clicked, removes the data from the xml and updates the visibility
    public void removeReminder(String day, TextView view){
        SharedPreferences data = getSharedPreferences("myData", MODE_PRIVATE);
        ArrayList<String> stringList = new ArrayList<>(data.getStringSet(day, new HashSet<>()));
        stringList.remove(view.getText().toString());
        SharedPreferences.Editor editor = data.edit();
        editor.putStringSet(day, new HashSet<>(stringList));
        editor.apply();
        clear();
        setData(day);
    }

    public void fixText(){
        for (int i = 0; i < LISTNNUM; i++) {
            if (!viewVisible[i]) {
                listViews[i].setText("zSample Reminder");
            }
        }
    }
}

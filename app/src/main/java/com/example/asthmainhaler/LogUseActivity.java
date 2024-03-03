package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class LogUseActivity extends AppCompatActivity {

    ImageButton backButton;
    Button logUseButton;

    Spinner trigger;

    String selectedLocation, selectedDate, selectedTrigger;

    private EditText location, date;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_use);

        // Get elements from layout file
        backButton = findViewById(R.id.back_button);
        logUseButton = findViewById(R.id.log_use_button);
        location = findViewById(R.id.location);
        date = findViewById(R.id.date);
        trigger = findViewById(R.id.trigger_spinner);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Submit button code
        logUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedLocation = location.getText().toString();
                selectedDate = date.getText().toString();
                selectedTrigger = trigger.getSelectedItem().toString();

                if (selectedLocation.isEmpty() || selectedDate.isEmpty() || selectedTrigger.isEmpty()) {
                    Toast.makeText(LogUseActivity.this, "Please enter values for all prompts.", Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d("LogUseActivity", "Saving selected values: " + selectedLocation + ", " + selectedDate + ", " + selectedTrigger);
                saveSelection();
                Intent intent = new Intent(LogUseActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });


        // Back button code
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogUseActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });

    }
    private void saveSelection() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();

        // Fetch the existing list
        String json = sharedPreferences.getString("asthmaLog", null);
        Type type = new TypeToken<ArrayList<MyAsthmaData>>() {}.getType();
        List<MyAsthmaData> asthmaDataList = gson.fromJson(json, type);

        if (asthmaDataList == null) {
            asthmaDataList = new ArrayList<>();
        }

        // Add the new log entry
        MyAsthmaData newLog = new MyAsthmaData(selectedLocation, selectedDate, selectedTrigger);
        asthmaDataList.add(newLog);

        // Save the updated list back
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String updatedJson = gson.toJson(asthmaDataList);
        editor.putString("asthmaLog", updatedJson);
        editor.apply();
    }
}
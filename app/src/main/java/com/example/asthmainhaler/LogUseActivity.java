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

    int pollenCount, dustCount, smokeCount, weatherCount;

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

                increaseLikelyTriggersCount();

                Log.d("LogUseActivity", "Saving selected values: " + selectedLocation + ", " + selectedDate + ", " + selectedTrigger);
                saveSelection();

                Intent intent1 = new Intent(LogUseActivity.this, TrackActivity.class);
                startActivity(intent1);
                finish();


                Intent intent2 = new Intent(LogUseActivity.this, ManageActivity.class);
                // Pass counts to ManageActivity
                intent2.putExtra("pollenCount", pollenCount);
                intent2.putExtra("dustCount", dustCount);
                intent2.putExtra("smokeCount", smokeCount);
                intent2.putExtra("weatherCount", weatherCount);
                startActivity(intent2);
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

    private void increaseLikelyTriggersCount() {
        // Fetch the existing counts based on the selected trigger
        pollenCount = sharedPreferences.getInt("pollenTriggersCount", 0);
        dustCount = sharedPreferences.getInt("dustTriggersCount", 0);
        smokeCount = sharedPreferences.getInt("smokeTriggersCount", 0);
        weatherCount = sharedPreferences.getInt("weatherTriggersCount", 0);

        // Increment the count based on the selected trigger
        if ("pollen".equalsIgnoreCase(selectedTrigger)) {
            pollenCount++;
        } else if ("dust".equalsIgnoreCase(selectedTrigger)) {
            dustCount++;
        } else if ("smoke".equalsIgnoreCase(selectedTrigger)) {
            smokeCount++;
        } else if ("weather".equalsIgnoreCase(selectedTrigger)) {
            weatherCount++;
        }

        // Save the updated counts back to preferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("pollenTriggersCount", pollenCount);
        editor.putInt("dustTriggersCount", dustCount);
        Log.d("hi", "please bro: " + dustCount);
        editor.putInt("smokeTriggersCount", smokeCount);
        editor.putInt("weatherTriggersCount", weatherCount);
        editor.apply();
    }

    private void updateLikelyTriggersCountDisplay() {
        // Fetch the updated counts
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        int pollenCount = preferences.getInt("pollenTriggersCount", 0);
        int dustCount = preferences.getInt("dustTriggersCount", 0);
        int smokeCount = preferences.getInt("smokeTriggersCount", 0);
        int weatherCount = preferences.getInt("weatherTriggersCount", 0);

        Log.d("Log Use Activity", "Dust Count: " + dustCount);

    }

}
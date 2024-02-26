package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;
import android.widget.Toast;

public class OnboardingActivity extends AppCompatActivity {

    Button submitButton;
    Spinner pollenSpinner, dustSpinner, smokeSpinner, weatherSpinner;

    String selectedPollen, selectedDust, selectedSmoke, selectedWeather;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        // Get elements from layout file
        submitButton = findViewById(R.id.submit_button);
        pollenSpinner = findViewById(R.id.pollen_spinner);
        dustSpinner = findViewById(R.id.dust_spinner);
        smokeSpinner = findViewById(R.id.smoke_spinner);
        weatherSpinner = findViewById(R.id.weather_spinner);
        Log.d("hi","here");

        // Submit button code
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Submit button code
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve selected values from spinners
                selectedPollen = pollenSpinner.getSelectedItem().toString();
                selectedDust = dustSpinner.getSelectedItem().toString();
                selectedSmoke = smokeSpinner.getSelectedItem().toString();
                selectedWeather = weatherSpinner.getSelectedItem().toString();

                // Check for null values before saving
                if (selectedPollen.isEmpty() || selectedDust.isEmpty() ||
                        selectedSmoke.isEmpty() || selectedWeather.isEmpty()) {
                    // Handle empty values (e.g., display a toast message)
                    Toast.makeText(OnboardingActivity.this, "Please select values for all triggers.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Log.d("OnboardingActivity", "Saving selected values: " + selectedPollen + ", " + selectedDust + ", " + selectedSmoke + ", " + selectedWeather);

                // Save selected values to SharedPreferences
                saveSelection();

                // Start HomeActivity
                Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveSelection() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("Pollen", selectedPollen);
        editor.putString("Dust", selectedDust);
        editor.putString("Smoke", selectedSmoke);
        editor.putString("Weather", selectedWeather);
        editor.apply();
    }
}
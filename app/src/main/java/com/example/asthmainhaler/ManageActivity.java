package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.content.Context;


public class ManageActivity extends AppCompatActivity {

    Button saveButton;
    ImageButton userButton;
    ImageButton homeButton;
    ImageButton infoButton;
    Spinner dropdownSmoke;
    Spinner dropdownPollen;
    Spinner dropdownDust;
    Spinner dropdownWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Get elements from layout file
        saveButton = findViewById(R.id.save_button);
        userButton = findViewById(R.id.user_button);
        homeButton = findViewById(R.id.home_button);
        infoButton = findViewById(R.id.info_button);
        dropdownSmoke = findViewById(R.id.smoke_spinner);
        dropdownPollen = findViewById(R.id.pollen_spinner);
        dropdownDust = findViewById(R.id.dust_spinner);
        dropdownWeather = findViewById(R.id.weather_spinner);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences(
                getApplicationContext().getPackageName() + "_preferences",
                Context.MODE_PRIVATE
        );
        String pollenValue = preferences.getString("Pollen", null);
        String dustValue = preferences.getString("Dust", null);
        String smokeValue = preferences.getString("Smoke", null);
        String weatherValue = preferences.getString("Weather", null);

        setSpinnerSelection(dropdownPollen, pollenValue);
        setSpinnerSelection(dropdownDust, dustValue);
        setSpinnerSelection(dropdownSmoke, smokeValue);
        setSpinnerSelection(dropdownWeather, weatherValue);

        // Save Changes button code
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndDisplayOption(dropdownPollen, "Pollen");
                saveAndDisplayOption(dropdownDust, "Dust");
                saveAndDisplayOption(dropdownSmoke, "Smoke");
                saveAndDisplayOption(dropdownWeather, "Weather");
            }
        });

        // User Profile button code (in navbar)
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        // Home button code (in navbar)
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // App Info button code (in navbar)
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSpinnerSelection(Spinner spinner, String value) {
        if (value != null) {
            int position = getIndexForOption(spinner, value);
            spinner.setSelection(position);
        }
    }

    private void saveAndDisplayOption(Spinner dropdown, String preferenceKey) {
        String selectedOption = dropdown.getSelectedItem().toString();

        // Save the selected option using SharedPreferences
        saveOption(preferenceKey, selectedOption);

        // Display a toast with the selected option
        Toast.makeText(ManageActivity.this, "Change saved: " + selectedOption, Toast.LENGTH_SHORT).show();
    }

    private void saveOption(String preferenceKey, String selectedOption) {
        // Use SharedPreferences to save the selected option
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferenceKey, selectedOption);
        editor.apply();
    }


    private int getIndexForOption(Spinner dropdown, String option) {
        // Get the index of the selected option in the Spinner's adapter
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) dropdown.getAdapter();
        return adapter.getPosition(option);
    }
}
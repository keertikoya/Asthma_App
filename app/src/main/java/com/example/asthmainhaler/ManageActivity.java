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

        Intent intent = getIntent();
        String pollenValue = intent.getStringExtra("Pollen");
        String dustValue = intent.getStringExtra("Dust");
        String smokeValue = intent.getStringExtra("Smoke");
        String weatherValue = intent.getStringExtra("Weather");

        // Set the retrieved values in the spinners
        setSpinnerSelection(dropdownPollen, pollenValue);
        setSpinnerSelection(dropdownDust, dustValue);
        setSpinnerSelection(dropdownSmoke, smokeValue);
        setSpinnerSelection(dropdownWeather, weatherValue);

        // Spinner options
        String[] options = {"Option 1", "Option 2", "Option 3"}; // Replace with your desired options

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        retrieveAndSetSavedOption(dropdownSmoke, "selected_option_1");
        retrieveAndSetSavedOption(dropdownPollen, "selected_option_2");
        retrieveAndSetSavedOption(dropdownDust, "selected_option_3");
        retrieveAndSetSavedOption(dropdownWeather, "selected_option_4");

        // Save Changes button code
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveAndDisplayOption(dropdownPollen, "selected_option_2");
                saveAndDisplayOption(dropdownDust, "selected_option_3");
                saveAndDisplayOption(dropdownSmoke, "selected_option_1");
                saveAndDisplayOption(dropdownWeather, "selected_option_4");
            }
        });

        // Save Changes button code
//        saveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(ManageActivity.this, HomeActivity.class);
//                startActivity(intent);
//            }
//        });

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
        Toast.makeText(ManageActivity.this, "Option saved: " + selectedOption, Toast.LENGTH_SHORT).show();
    }

    private void saveOption(String preferenceKey, String selectedOption) {
        // Use SharedPreferences to save the selected option
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(preferenceKey, selectedOption);
        editor.apply();
    }

    private void retrieveAndSetSavedOption(Spinner dropdown, String preferenceKey) {
        // Retrieve the saved option from SharedPreferences
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String savedOption = preferences.getString(preferenceKey, null);

        // Set the saved option in the Spinner if it exists
        if (savedOption != null) {
            int position = getIndexForOption(dropdown, savedOption);
            dropdown.setSelection(position);
        }
    }

    private int getIndexForOption(Spinner dropdown, String option) {
        // Get the index of the selected option in the Spinner's adapter
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) dropdown.getAdapter();
        return adapter.getPosition(option);
    }
}
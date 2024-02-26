package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
public class OnboardingActivity extends AppCompatActivity {

    Button submitButton;
    Spinner pollenSpinner, dustSpinner, smokeSpinner, weatherSpinner;

    String selectedPollen, selectedDust, selectedSmoke, selectedWeather;

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

        // Submit button code
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get selected values from spinners
                selectedPollen = pollenSpinner.getSelectedItem().toString();
                selectedDust = dustSpinner.getSelectedItem().toString();
                selectedSmoke = smokeSpinner.getSelectedItem().toString();
                selectedWeather = weatherSpinner.getSelectedItem().toString();

                // Create an Intent to start HomeActivity
                Intent intent = new Intent(OnboardingActivity.this, HomeActivity.class);

                // Pass selected values as extras
                intent.putExtra("Pollen", selectedPollen);
                intent.putExtra("Dust", selectedDust);
                intent.putExtra("Smoke", selectedSmoke);
                intent.putExtra("Weather", selectedWeather);

                // Start HomeActivity
                startActivity(intent);
            }
        });
    }
}

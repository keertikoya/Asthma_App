package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

//weather imporrts
import android.graphics.Color;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.TextView;
import android.widget.EditText;
/*
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
*/
import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InfoActivity extends AppCompatActivity {

    ImageButton userButton;
    ImageButton homeButton;
    ImageButton infoButton;

    private String temp;

  //  private TextView cityNameTextView;
  //  private TextView temperatureTextView;
  //  private TextView weatherDescriptionTextView;
   // private ImageView weatherIconImageView;

    private OpenWeatherMapService openWeatherMapService;
    private String apiKey = "b568b7e124d8ac2b36ef3ee9d2b1d81c";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        // Get elements from layout file
        userButton = findViewById(R.id.user_button);
        homeButton = findViewById(R.id.home_button);
        infoButton = findViewById(R.id.info_button);

        // User Profile button code (in navbar)
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        // Home button code (in navbar)
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // App Info button code (in navbar)
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });


     /*   cityNameTextView = findViewById(R.id.city_name_text_view);
        temperatureTextView = findViewById(R.id.temperature_text_view);
        weatherDescriptionTextView = findViewById(R.id.weather_description_text_view);
        weatherIconImageView = findViewById(R.id.weather_icon_image_view);*/

        openWeatherMapService = ApiClient.getInstance().create(OpenWeatherMapService.class);

       loadWeatherData("London");
    }

    private void loadWeatherData(String location) {
       // Toast.makeText(getApplicationContext(),"checkpoint1", Toast.LENGTH_SHORT).show();
        openWeatherMapService.getCurrentWeatherData(location, apiKey).enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                Toast.makeText(getApplicationContext(),"checkpoint2", Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    Toast.makeText(getApplicationContext(),"checkpoint3", Toast.LENGTH_SHORT).show();
                    WeatherData weatherData = response.body();
                    Toast.makeText(getApplicationContext(), "response body: " + response.body(), Toast.LENGTH_SHORT).show();

                    updateUI(weatherData);
                } else {
                    showErrorToast();
                }
            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {
                showErrorToast();
            }
        });
    }

    private void updateUI(WeatherData weatherData) {
      //  cityNameTextView.setText(weatherData.getCityName());
     //   temperatureTextView.setText(weatherData.getTemperature() + "°C");
      //  weatherDescriptionTextView.setText(weatherData.getWeatherDescription());
        // Load weather icon using a library like Picasso or Glide
        temp = weatherData.getTemperature();
        Toast.makeText(getApplicationContext(),"chec " + temp, Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(),"just city name: " + weatherData.getCityName(), Toast.LENGTH_SHORT).show();
    }

    public void weatherButton(View view) {
        Toast.makeText(this, "temp " + temp, Toast.LENGTH_SHORT).show();
    }

    private void showErrorToast() {
        Toast.makeText(InfoActivity.this, "Failed to load weather data", Toast.LENGTH_SHORT).show();
    }


}
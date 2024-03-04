package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TrackActivity extends AppCompatActivity {

    Button logUseButton;
    ImageButton userButton;
    ImageButton homeButton;
    ImageButton infoButton;
    RecyclerView recyclerView;
    List myAsthmaDataList;

    private MyAsthmaAdapter myAsthmaAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadData();
        // Get elements from layout file
        logUseButton = findViewById(R.id.track_log_use_button);
        userButton = findViewById(R.id.user_button);
        homeButton = findViewById(R.id.home_button);
        infoButton = findViewById(R.id.info_button);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPreferences.getString("Location", "");
        String date = sharedPreferences.getString("Date", "");
        String trigger = sharedPreferences.getString("Trigger", "");

        MyAsthmaData[] myAsthmaDataList = {new MyAsthmaData(location, date, trigger)};

        MyAsthmaAdapter myAsthmaAdapter = new MyAsthmaAdapter(myAsthmaDataList, this);
        recyclerView.setAdapter(myAsthmaAdapter);

        // Log Use Button code
        logUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TrackActivity", "entered");
                Intent intent = new Intent(TrackActivity.this, LogUseActivity.class);
                startActivity(intent);
            }
        });

        // User Profile button code (in navbar)
        userButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackActivity.this, UserProfileActivity.class);
                startActivity(intent);
            }
        });

        // Home button code (in navbar)
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        // App Info button code (in navbar)
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrackActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("asthmaLog", null);
        Type type = new TypeToken<ArrayList<MyAsthmaData>>() {}.getType();
        myAsthmaDataList = gson.fromJson(json, type);

        if (myAsthmaDataList == null) {
            myAsthmaDataList = new ArrayList<>();
        }

        MyAsthmaData[] myAsthmaDataArray = new MyAsthmaData[myAsthmaDataList.size()];
        myAsthmaDataList.toArray(myAsthmaDataArray);

        myAsthmaAdapter = new MyAsthmaAdapter(myAsthmaDataArray, this);
        recyclerView.setAdapter(myAsthmaAdapter);
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

        // A toast with the selected option
        Toast.makeText(TrackActivity.this, "Change saved: " + selectedOption, Toast.LENGTH_SHORT).show();
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
    protected void onResume() {
        super.onResume();
        loadData();
    }
}
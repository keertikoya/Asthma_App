package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class TrackActivity extends AppCompatActivity {

    Button logUseButton;
    ImageButton userButton;
    ImageButton homeButton;
    ImageButton infoButton;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        // Get elements from layout file
        logUseButton = findViewById(R.id.log_use_button);
        userButton = findViewById(R.id.user_button);
        homeButton = findViewById(R.id.home_button);
        infoButton = findViewById(R.id.info_button);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Populate Recycler View
        MyAsthmaData[] myAsthmaDataList = new MyAsthmaData[3];
        MyAsthmaData myAsthmaData1 = new MyAsthmaData("Fremont", "12/25/2023", "Smoke");
        MyAsthmaData myAsthmaData2 = new MyAsthmaData("San Jose", "12/26/2023", "Dust");
        MyAsthmaData myAsthmaData3 = new MyAsthmaData("Santa Clara", "12/28/2023", "Smoke");
        myAsthmaDataList[0] = myAsthmaData1;
        myAsthmaDataList[1] = myAsthmaData2;
        myAsthmaDataList[2] = myAsthmaData3;
        MyAsthmaAdapter myAsthmaAdapter = new MyAsthmaAdapter(myAsthmaDataList, TrackActivity.this);
        recyclerView.setAdapter(myAsthmaAdapter);

        // Log Use Button code
        logUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
}
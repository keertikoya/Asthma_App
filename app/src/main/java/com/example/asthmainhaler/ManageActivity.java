package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ManageActivity extends AppCompatActivity {

    Button saveButton;
    ImageButton userButton;
    ImageButton homeButton;
    ImageButton infoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Get elements from layout file
        saveButton = findViewById(R.id.save_button);
        userButton = findViewById(R.id.user_button);
        homeButton = findViewById(R.id.home_button);
        infoButton = findViewById(R.id.info_button);

        // Save Changes button code
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, HomeActivity.class);
                startActivity(intent);
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
}
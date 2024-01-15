package com.example.asthmainhaler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LogUseActivity extends AppCompatActivity {

    ImageButton backButton;
    Button logUseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_use);

        // Get elements from layout file
        backButton = findViewById(R.id.back_button);
        logUseButton = findViewById(R.id.log_use_button);

        // Back button code
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogUseActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });

        // Log Use button code
        logUseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogUseActivity.this, TrackActivity.class);
                startActivity(intent);
            }
        });
    }
}
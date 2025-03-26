package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // send user back to main activity
            Intent intent = new Intent(DisplayActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Close the current activity
        });

        // set on click listener for app notifications being pressed
        TextView lockscreenDisplay = findViewById(R.id.lockscreen_display);
        lockscreenDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayActivity.this, LockScreenActivity.class);
            startActivity(intent);
        });

        TextView touchsensitivityDisplay = findViewById(R.id.touchsensitivity_display);
        touchsensitivityDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayActivity.this, TouchSensitivityActivity.class);
            startActivity(intent);
        });
    }
}
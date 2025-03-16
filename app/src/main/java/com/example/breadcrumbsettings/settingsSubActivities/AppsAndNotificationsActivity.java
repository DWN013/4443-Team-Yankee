package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;

public class AppsAndNotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_and_notifications);

        // fetch the linear layouts i defined in my source xml
        LinearLayout notificationsSection = findViewById(R.id.notificationsSection);
        LinearLayout assistantSection = findViewById(R.id.assistantSection);
        LinearLayout advancedSection = findViewById(R.id.advancedSection);

        // fetch the toolbar so i can add functionality to the back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // set on click listeners for each
        notificationsSection.setOnClickListener(v -> {
                    Intent intent = new Intent(AppsAndNotificationsActivity.this, NotificationsActivity.class);
                    startActivity(intent);
                });
        assistantSection.setOnClickListener(v ->
                Toast.makeText(this, "Placeholder", Toast.LENGTH_SHORT).show());
        advancedSection.setOnClickListener(v ->
                Toast.makeText(this, "Placeholder", Toast.LENGTH_SHORT).show());

        // set on click listener for the back button being pressed
        toolbar.setNavigationOnClickListener(v -> {
            // send the user back to the main activity
            Intent intent = new Intent(AppsAndNotificationsActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Close the current activity
        });
    }
}
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

public class NotificationsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        // fetch the toolbar so i can add functionality to the back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // set on click listener for the back button being pressed
        toolbar.setNavigationOnClickListener(v -> {
            // send the user back to the apps and notifications activity
            Intent intent = new Intent(NotificationsActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // close the current activity
        });

        // set on click listener for app notifications being pressed
        TextView appNotifications = findViewById(R.id.app_notifications);
        appNotifications.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationsActivity.this, NotificationsAppActivity.class);
            startActivity(intent);
        });

        // set on click listener for notifications history being pressed
        TextView history_notifications = findViewById(R.id.history_notifications);
        history_notifications.setOnClickListener(v -> {
            Intent intent = new Intent(NotificationsActivity.this, NotificationsHistoryActivity.class);
            startActivity(intent);
        });
    }
}
package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.breadcrumbsettings.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

import org.w3c.dom.Text;

public class NotificationsHistoryActivity extends AppCompatActivity {

    // initialize the elements
    private SwitchMaterial historySwitch;
    private TextView statusText, subText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notifications_history);

        // fetch the toolbar so i can add functionality to the back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        // set on click listener for the back button being pressed
        toolbar.setNavigationOnClickListener(v -> {
            // send the user back to the apps and notifications activity
            Intent intent = new Intent(NotificationsHistoryActivity.this, NotificationsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Close the current activity
        });


        // fetch the views
        historySwitch = findViewById(R.id.switch_notification_history);
        statusText = findViewById(R.id.statusText);
        subText = findViewById(R.id.statusSubText);

        // setting initial switch state to off
        historySwitch.setChecked(false);

        // logic for the toggle changing the text on screen
        historySwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                // switch is moved to on
                statusText.setText("No recent notifications");
                subText.setText("Your recent and snoozed notifications will appear here");
            } else {
                // switch is moved to off
                statusText.setText("Notification history turned off");
                subText.setText("Turn on notification history to see recent notifications and snoozed notifications");
            }
        });
    }
}
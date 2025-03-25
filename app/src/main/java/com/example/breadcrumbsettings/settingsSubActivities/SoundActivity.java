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
import com.google.android.material.slider.Slider;

public class SoundActivity extends AppCompatActivity {
    Slider media_slider, call_slider, ring_slider, notification_slider, alarm_slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sound);

        media_slider = findViewById(R.id.media_slider);
        call_slider = findViewById(R.id.call_slider);
        ring_slider = findViewById(R.id.ring_slider);
        notification_slider = findViewById(R.id.notification_slider);
        alarm_slider = findViewById(R.id.alarm_slider);

        // fetch the toolbar so i can add functionality to the back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // set on click listener for the back button being pressed
        toolbar.setNavigationOnClickListener(v -> {
            // send the user back to the main activity
            Intent intent = new Intent(SoundActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // close the current activity
        });

        // set on click listener for dnd being pressed
        TextView dnd = findViewById(R.id.dnd);
        dnd.setOnClickListener(v -> {
            Intent intent = new Intent(SoundActivity.this, SoundDNDActivity.class);
            startActivity(intent);
        });
    }
}
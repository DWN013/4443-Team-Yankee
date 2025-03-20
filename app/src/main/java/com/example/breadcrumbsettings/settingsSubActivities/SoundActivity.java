package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

    }
}
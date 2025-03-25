package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;

public class SoundDNDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sound_dnd);

        // fetch button to add on click listener to it being pressed
        Button dndButton = findViewById(R.id.dndButton);

        // fetch the toolbar so i can add functionality to the back button
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // set on click listener for the back button being pressed
        toolbar.setNavigationOnClickListener(v -> {
            // send the user back to the main activity
            Intent intent = new Intent(SoundDNDActivity.this, SoundActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // close the current activity
        });

        // on click listener for button being pressed
        dndButton.setOnClickListener(v -> {
            String currentText = dndButton.getText().toString();
            if (currentText.equals("Turn on now")) {
                dndButton.setText("Turn off now");
            } else {
                dndButton.setText("Turn on now");
            }
        });
    }
}
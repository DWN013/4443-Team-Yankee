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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import com.google.android.material.slider.Slider;

public class SoundActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;
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

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // Deserialize breadcrumbs if present
        if (getIntent().hasExtra("breadcrumbs")) {
            String serializedBreadcrumbs = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serializedBreadcrumbs);
        }
        breadcrumbsViewModel.addBreadcrumb("Sound", SoundActivity.class);

        showBreadcrumbsFragment();

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
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
            finish(); // close the current activity
        });

        // set on click listener for dnd being pressed
        TextView dnd = findViewById(R.id.dnd);
        dnd.setOnClickListener(v -> {
            Intent intent = new Intent(SoundActivity.this, SoundDNDActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });
    }

    private void showBreadcrumbsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BreadcrumbsFragment breadcrumbsFragment = new BreadcrumbsFragment();
        fragmentTransaction.add(R.id.breadcrumbs_container, breadcrumbsFragment);
        fragmentTransaction.commit();
    }
}
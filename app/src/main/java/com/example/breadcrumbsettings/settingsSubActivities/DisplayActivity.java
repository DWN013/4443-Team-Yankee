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

import androidx.lifecycle.ViewModelProvider;
import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class DisplayActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // Deserialize breadcrumbs if present
        if (getIntent().hasExtra("breadcrumbs")) {
            String serializedBreadcrumbs = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serializedBreadcrumbs);
        }
        breadcrumbsViewModel.addBreadcrumb("Display & touch", DisplayActivity.class);

        showBreadcrumbsFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // send user back to main activity
            Intent intent = new Intent(DisplayActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
            finish(); // Close the current activity
        });

        // set on click listener for app notifications being pressed
        TextView lockscreenDisplay = findViewById(R.id.lockscreen_display);
        lockscreenDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayActivity.this, LockScreenActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        TextView touchsensitivityDisplay = findViewById(R.id.touchsensitivity_display);
        touchsensitivityDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayActivity.this, TouchSensitivityActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        TextView screentimeoutDisplay = findViewById(R.id.screentimeout_display);
        screentimeoutDisplay.setOnClickListener(v -> {
            Intent intent = new Intent(DisplayActivity.this, ScreenTimeoutActivity.class);
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
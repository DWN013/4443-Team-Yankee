package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import com.example.breadcrumbsettings.utils.SharedPreferencesUtil;

public class AppsActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_apps);

        SharedPreferencesUtil.incrementTapCount(this);
        int totalTaps = SharedPreferencesUtil.getTapCount(this);
        Toast.makeText(this, "Total taps: " + totalTaps, Toast.LENGTH_SHORT).show();

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // Deserialize breadcrumbs if present
        if (getIntent().hasExtra("breadcrumbs")) {
            String serializedBreadcrumbs = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serializedBreadcrumbs);
        }
        breadcrumbsViewModel.clearBreadcrumbs();
        breadcrumbsViewModel.addBreadcrumb("Apps", AppsActivity.class);


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
            Intent intent = new Intent(AppsActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish(); // Close the current activity
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
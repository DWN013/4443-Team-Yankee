package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

public class SecurityAndPrivacyActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_privacy);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // Deserialize passed breadcrumbs if any
        if (getIntent().hasExtra("breadcrumbs")) {
            String serialized = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serialized);
        }
        // Clear any previous breadcrumbs to ensure a fresh trail
        breadcrumbsViewModel.clearBreadcrumbs();
        // Add the current screen to breadcrumbs
        breadcrumbsViewModel.addBreadcrumb("Security & privacy", SecurityAndPrivacyActivity.class);

        // Show breadcrumbs fragment
        showBreadcrumbsFragment();

        // Setup Toolbar with back navigation
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
            finish();
        });

        // Set click listeners for each security & privacy item
        findViewById(R.id.app_security_item).setOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, AppSecurityActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.device_unlock_item).setOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, DeviceUnlockActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.account_security_item).setOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, AccountSecurityActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.device_finders_item).setOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, DeviceFindersActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.system_updates_item).setOnClickListener(v -> {
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, SystemUpdatesActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });
    }

    private void showBreadcrumbsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BreadcrumbsFragment breadcrumbsFragment = new BreadcrumbsFragment();
        // Add the BreadcrumbsFragment to the container defined in activity_security_privacy.xml
        fragmentTransaction.add(R.id.breadcrumbs_container, breadcrumbsFragment);
        fragmentTransaction.commit();
    }
}

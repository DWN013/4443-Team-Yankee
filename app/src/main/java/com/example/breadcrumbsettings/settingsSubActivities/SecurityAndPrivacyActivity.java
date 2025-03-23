package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;

public class SecurityAndPrivacyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_privacy);

        // Edge-to-edge insets
        View mainView = findViewById(R.id.main);
        ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // Return to MainSettingsActivity
            Intent intent = new Intent(SecurityAndPrivacyActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        });

        // Click listeners for each item
        findViewById(R.id.lock_screen_item).setOnClickListener(v ->
                startActivity(new Intent(SecurityAndPrivacyActivity.this, LockScreenDetailActivity.class)));

        findViewById(R.id.encryption_item).setOnClickListener(v ->
                startActivity(new Intent(SecurityAndPrivacyActivity.this, EncryptionDetailActivity.class)));

        findViewById(R.id.find_my_device_item).setOnClickListener(v ->
                startActivity(new Intent(SecurityAndPrivacyActivity.this, FindMyDeviceDetailActivity.class)));

        findViewById(R.id.permissions_item).setOnClickListener(v ->
                startActivity(new Intent(SecurityAndPrivacyActivity.this, PermissionsDetailActivity.class)));
    }
}

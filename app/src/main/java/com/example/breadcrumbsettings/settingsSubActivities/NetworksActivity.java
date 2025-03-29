package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

public class NetworksActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_networks);

        // Get the breadcrumbs ViewModel shared among activities
        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // If breadcrumbs were passed from a previous activity, deserialize them
        if (getIntent().hasExtra("breadcrumbs")) {
            String serialized = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serialized);
        }
        // Add this screen to breadcrumbs
        breadcrumbsViewModel.addBreadcrumb("Networks", NetworksActivity.class);

        // Show breadcrumbs fragment using add(), like in DisplayActivity
        showBreadcrumbsFragment();

        View mainView = findViewById(R.id.main);
        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Setup Toolbar with minimal style
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // When back is clicked, return breadcrumbs to the previous activity
            Intent intent = new Intent(NetworksActivity.this, MainSettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
            finish();
        });

        // Setup click listeners to launch detail activities.
        findViewById(R.id.wi_fi_item).setOnClickListener(v -> {
            Intent intent = new Intent(NetworksActivity.this, WifiDetailActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.mobile_data_item).setOnClickListener(v -> {
            Intent intent = new Intent(NetworksActivity.this, MobileDataDetailActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.vpn_item).setOnClickListener(v -> {
            Intent intent = new Intent(NetworksActivity.this, VPNDetailActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });

        findViewById(R.id.hotspot_item).setOnClickListener(v -> {
            Intent intent = new Intent(NetworksActivity.this, HotspotDetailActivity.class);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
        });
    }

    private void showBreadcrumbsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BreadcrumbsFragment breadcrumbsFragment = new BreadcrumbsFragment();
        // Add the fragment to the container, matching DisplayActivity's behavior
        fragmentTransaction.add(R.id.breadcrumbs_container, breadcrumbsFragment);
        fragmentTransaction.commit();
    }
}

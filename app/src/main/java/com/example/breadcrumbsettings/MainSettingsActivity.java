package com.example.breadcrumbsettings;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;

import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import com.example.breadcrumbsettings.model.SettingsItem;
import com.example.breadcrumbsettings.settingsSubActivities.AppsActivity;
import com.example.breadcrumbsettings.settingsSubActivities.DisplayActivity;
import com.example.breadcrumbsettings.settingsSubActivities.SoundActivity;
import com.example.breadcrumbsettings.settingsSubActivities.NetworksActivity;
import com.example.breadcrumbsettings.settingsSubActivities.NotificationsActivity;

import java.util.ArrayList;
import java.util.List;

public class MainSettingsActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);
        showBreadcrumbsFragment();

        RecyclerView recyclerView = findViewById(R.id.settings_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<SettingsItem> settingsList = new ArrayList<>();
        settingsList.add(new SettingsItem(R.drawable.ic_network, "Network & internet", "Wi-Fi, mobile, and data usage"));
        settingsList.add(new SettingsItem(R.drawable.ic_bluetooth, "Connected devices", "Bluetooth"));
        settingsList.add(new SettingsItem(R.drawable.ic_apps, "Apps", "Recent apps, default apps"));
        settingsList.add(new SettingsItem(R.drawable.ic_notifications, "Notifications", "Notifications history, conversations"));
        settingsList.add(new SettingsItem(R.drawable.ic_battery, "Battery", "100%"));
        settingsList.add(new SettingsItem(R.drawable.ic_storage, "Storage", "81% used - 2.98 GB free"));
        settingsList.add(new SettingsItem(R.drawable.ic_sound, "Sound", "Volume, vibration, Do Not Disturb"));
        settingsList.add(new SettingsItem(R.drawable.ic_display, "Display & touch", "Wallpaper, sleep, font size"));
        settingsList.add(new SettingsItem(R.drawable.ic_privacy, "Security & Privacy", "Permissions, account activity, personal data"));
        settingsList.add(new SettingsItem(R.drawable.ic_location, "Location", "On - 4 apps have access to location"));

        SettingsAdapter adapter = new SettingsAdapter(settingsList);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SettingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0: // Network & internet
                        startActivity(new Intent(MainSettingsActivity.this, NetworksActivity.class));
                        break;
//                    case 1: // Connected Devices
//                        startActivity(new Intent(MainSettingsActivity.this, NotificationsActivity.class));
//                        break;
                    case 2: // Apps
                        startActivity(new Intent(MainSettingsActivity.this, AppsActivity.class));
                        break;
                    case 3: // Notifications
                        startActivity(new Intent(MainSettingsActivity.this, NotificationsActivity.class));
                        break;
//                    case 4: // Battery
//                        startActivity(new Intent(MainSettingsActivity.this, NotificationsActivity.class));
//                        break;
//                    case 5: // Storage
//                        startActivity(new Intent(MainSettingsActivity.this, NotificationsActivity.class));
//                        break;
                    case 6: // Sound
                        startActivity(new Intent(MainSettingsActivity.this, SoundActivity.class));
                        break;
                    case 7: // Display and Touch
                        startActivity(new Intent(MainSettingsActivity.this, DisplayActivity.class));
                        break;
                    // Add more cases for other items if needed
                }
            }
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
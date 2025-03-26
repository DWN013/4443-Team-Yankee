package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;

public class HotspotDetailActivity extends AppCompatActivity {

    private Switch hotspotSwitch;
    private TextView hotspotInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotspot_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        hotspotSwitch = findViewById(R.id.hotspot_switch);
        hotspotInfo = findViewById(R.id.hotspot_info);

        // Simulate loading hotspot details
        boolean isHotspotEnabled = false; // for simulation
        hotspotSwitch.setChecked(isHotspotEnabled);
        updateHotspotInfo(isHotspotEnabled);

        hotspotSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                updateHotspotInfo(isChecked);
            }
        });
    }

    private void updateHotspotInfo(boolean enabled) {
        if(enabled) {
            hotspotInfo.setText("Hotspot is enabled.\nSSID: MyHotspot\nPassword: 12345678");
        } else {
            hotspotInfo.setText("Hotspot is disabled.\nTap the switch to enable.");
        }
    }
}

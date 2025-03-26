package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;

public class WifiDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Simulate loading Wi-Fi information
        TextView info = findViewById(R.id.info_text);
        info.setText("Connected to: Home_WiFi\nSignal Strength: 80%\nIP Address: 192.168.1.5\n\nAvailable networks:\n• Home_WiFi (Connected)\n• NeighborNet (Secured)\n• Public_Free_WiFi (Open)");
    }
}

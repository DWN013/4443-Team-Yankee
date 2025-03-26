package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;

public class MobileDataDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_data_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        // Simulate loading mobile data details
        TextView info = findViewById(R.id.info_text);
        info.setText("Mobile Data is enabled.\nNetwork Type: LTE\nData Usage Today: 350 MB\nData Usage This Month: 3.2 GB");
    }
}

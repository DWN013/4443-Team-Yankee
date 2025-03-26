package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;

public class PermissionsDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());

        TextView info = findViewById(R.id.info_text);
        info.setText(
                "Permissions Manager:\n" +
                        "• Location: 5 apps allowed\n" +
                        "• Camera: 3 apps allowed\n" +
                        "• Microphone: 2 apps allowed\n" +
                        "• Contacts: 8 apps allowed\n\n" +
                        "Tap to revoke or manage permissions individually."
        );
    }
}

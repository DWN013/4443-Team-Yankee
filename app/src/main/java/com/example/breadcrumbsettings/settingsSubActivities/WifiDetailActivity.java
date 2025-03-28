package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

public class WifiDetailActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_detail);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);
        if(getIntent().hasExtra("breadcrumbs")){
            String serialized = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serialized);
        }
        breadcrumbsViewModel.addBreadcrumb("Wi‑Fi", WifiDetailActivity.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}

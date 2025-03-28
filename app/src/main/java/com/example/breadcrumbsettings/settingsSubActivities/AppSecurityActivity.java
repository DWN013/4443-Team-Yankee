package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;
import androidx.lifecycle.ViewModelProvider;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

public class AppSecurityActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_security);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);
        if (getIntent().hasExtra("breadcrumbs")) {
            String serialized = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serialized);
        }
        breadcrumbsViewModel.addBreadcrumb("App security", AppSecurityActivity.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });
    }
}

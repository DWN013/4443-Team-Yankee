package com.example.breadcrumbsettings.settingsSubActivities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.breadcrumbsettings.R;
import androidx.lifecycle.ViewModelProvider;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

public class AccountSecurityActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_security);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);
        if(getIntent().hasExtra("breadcrumbs")) {
            String serialized = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serialized);
        }
        breadcrumbsViewModel.addBreadcrumb("Account security", AccountSecurityActivity.class);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> finish());
    }
}

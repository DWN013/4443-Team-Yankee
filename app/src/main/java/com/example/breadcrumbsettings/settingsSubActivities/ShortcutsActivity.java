package com.example.breadcrumbsettings.settingsSubActivities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.lifecycle.ViewModelProvider;
import com.example.breadcrumbsettings.MainSettingsActivity;
import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.breadcrumbs.BreadcrumbsFragment;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ShortcutsActivity extends AppCompatActivity {
    private BreadcrumbsViewModel breadcrumbsViewModel;
    private ImageView previouslySelectedIcon = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_display_lockscreen_shortcut);

        breadcrumbsViewModel = new ViewModelProvider(this).get(BreadcrumbsViewModel.class);

        // Deserialize breadcrumbs if present
        if (getIntent().hasExtra("breadcrumbs")) {
            String serializedBreadcrumbs = getIntent().getStringExtra("breadcrumbs");
            breadcrumbsViewModel.deserializeBreadcrumbs(serializedBreadcrumbs);
        }
        breadcrumbsViewModel.addBreadcrumb("Shortcuts", DisplayActivity.class);

        showBreadcrumbsFragment();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setNavigationOnClickListener(v -> {
            // send user back to main activity
            Intent intent = new Intent(ShortcutsActivity.this, LockScreenActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("breadcrumbs", breadcrumbsViewModel.serializeBreadcrumbs());
            startActivity(intent);
            finish(); // Close the current activity
        });

        TextView leftTab = findViewById(R.id.tab_left);
        TextView rightTab = findViewById(R.id.tab_right);

        ImageView deviceIcon = findViewById(R.id.ic_device_controls);
        ImageView dndIcon = findViewById(R.id.ic_dnd);
        ImageView muteIcon = findViewById(R.id.ic_mute);
        ImageView qrIcon = findViewById(R.id.ic_qr_scanner);
        ImageView videoIcon = findViewById(R.id.ic_video_camera);

        // Shared click logic
        View.OnClickListener iconClickListener = view -> {
            if (previouslySelectedIcon != null) {
                previouslySelectedIcon.setBackground(ContextCompat.getDrawable(this, R.drawable.shortcut_tab_background));
            }

            view.setBackground(ContextCompat.getDrawable(this, R.drawable.shortcut_icon_selected));
            previouslySelectedIcon = (ImageView) view;
        };

        View.OnClickListener tabClickListener = view -> {
            // Reset both tabs to default
            leftTab.setBackground(ContextCompat.getDrawable(this, R.drawable.tab_unselected));
            leftTab.setTextColor(ContextCompat.getColor(this, android.R.color.black));

            rightTab.setBackground(ContextCompat.getDrawable(this, R.drawable.tab_unselected));
            rightTab.setTextColor(ContextCompat.getColor(this, android.R.color.black));

            // Highlight the one that was clicked
            view.setBackground(ContextCompat.getDrawable(this, R.drawable.shortcut_tab_selected));
            ((TextView) view).setTextColor(ContextCompat.getColor(this, android.R.color.white));
        };

        leftTab.setOnClickListener(tabClickListener);
        rightTab.setOnClickListener(tabClickListener);

        deviceIcon.setOnClickListener(iconClickListener);
        dndIcon.setOnClickListener(iconClickListener);
        muteIcon.setOnClickListener(iconClickListener);
        qrIcon.setOnClickListener(iconClickListener);
        videoIcon.setOnClickListener(iconClickListener);
    }
    private void showBreadcrumbsFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        BreadcrumbsFragment breadcrumbsFragment = new BreadcrumbsFragment();
        fragmentTransaction.add(R.id.breadcrumbs_container, breadcrumbsFragment);
        fragmentTransaction.commit();
    }
}
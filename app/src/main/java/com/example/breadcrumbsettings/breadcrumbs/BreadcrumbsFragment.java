package com.example.breadcrumbsettings.breadcrumbs;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class BreadcrumbsFragment extends Fragment implements BreadcrumbsAdapter.BreadcrumbClickListener {
    private BreadcrumbsViewModel breadcrumbsViewModel;
    private RecyclerView breadcrumbsRecyclerView;
    private BreadcrumbsAdapter breadcrumbsAdapter;
    private static final String TAG = "BreadcrumbsFragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breadcrumbs, container, false);
        breadcrumbsRecyclerView = view.findViewById(R.id.breadcrumbs_recycler_view);
        breadcrumbsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        breadcrumbsAdapter = new BreadcrumbsAdapter(this);
        breadcrumbsRecyclerView.setAdapter(breadcrumbsAdapter);
        Log.d(TAG, "BreadcrumbsFragment view created");
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        breadcrumbsViewModel = new ViewModelProvider(requireActivity()).get(BreadcrumbsViewModel.class);
        breadcrumbsViewModel.getBreadcrumbs().observe(getViewLifecycleOwner(), this::updateBreadcrumbs);
        Log.d(TAG, "BreadcrumbsViewModel observed");
    }

    private void updateBreadcrumbs(Stack<String> breadcrumbs) {
        breadcrumbsAdapter.setBreadcrumbs(breadcrumbs);
        Log.d(TAG, "Breadcrumbs updated: " + breadcrumbs);
    }

    public void updateBackgroundColor() {
        try {
            InputStream inputStream = getContext().openFileInput("breadcrumb_background.xml");
            Drawable backgroundDrawable = Drawable.createFromStream(inputStream, null);
            // Assuming there's a view or layout that uses this background drawable
            // For example: breadcrumbsRecyclerView.setBackground(backgroundDrawable);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBreadcrumbClick(int position) {
        // Navigate back to the corresponding activity
        String breadcrumb = breadcrumbsViewModel.getBreadcrumbs().getValue().get(position);
        Class<?> activityClass = breadcrumbsViewModel.getActivityForBreadcrumb(breadcrumb);
        if (activityClass != null) {
            Intent intent = new Intent(getContext(), activityClass);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }
        Log.d(TAG, "Breadcrumb clicked: " + position);
    }
}
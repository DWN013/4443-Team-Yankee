package com.example.breadcrumbsettings.breadcrumbs;

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

    @Override
    public void onBreadcrumbClick(int position) {
        // Handle breadcrumb click
        breadcrumbsViewModel.removeBreadcrumbsAfter(position);
        Log.d(TAG, "Breadcrumb clicked: " + position);
    }
}
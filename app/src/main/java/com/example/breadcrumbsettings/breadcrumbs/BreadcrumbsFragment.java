package com.example.breadcrumbsettings.breadcrumbs;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.breadcrumbsettings.R;
import com.example.breadcrumbsettings.model.BreadcrumbsViewModel;
import java.util.Stack;

public class BreadcrumbsFragment extends Fragment {
    private BreadcrumbsViewModel breadcrumbsViewModel;
    private RecyclerView breadcrumbsRecyclerView;
    private BreadcrumbsAdapter breadcrumbsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breadcrumbs, container, false);
        breadcrumbsRecyclerView = view.findViewById(R.id.breadcrumbs_recycler_view);
        breadcrumbsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        breadcrumbsAdapter = new BreadcrumbsAdapter();
        breadcrumbsRecyclerView.setAdapter(breadcrumbsAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        breadcrumbsViewModel = new ViewModelProvider(requireActivity()).get(BreadcrumbsViewModel.class);
        breadcrumbsViewModel.getBreadcrumbs().observe(getViewLifecycleOwner(), this::updateBreadcrumbs);
    }

    private void updateBreadcrumbs(Stack<String> breadcrumbs) {
        breadcrumbsAdapter.setBreadcrumbs(breadcrumbs);
    }
}
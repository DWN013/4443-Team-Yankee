package com.example.breadcrumbsettings.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Stack;

public class BreadcrumbsViewModel extends ViewModel {
    private final MutableLiveData<Stack<String>> breadcrumbs;

    public BreadcrumbsViewModel() {
        breadcrumbs = new MutableLiveData<>(new Stack<>());
    }

    public LiveData<Stack<String>> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void addBreadcrumb(String breadcrumb) {
        Stack<String> currentBreadcrumbs = breadcrumbs.getValue();
        if (currentBreadcrumbs != null) {
            currentBreadcrumbs.push(breadcrumb);
            breadcrumbs.setValue(currentBreadcrumbs);
        }
    }

    public void removeLastBreadcrumb() {
        Stack<String> currentBreadcrumbs = breadcrumbs.getValue();
        if (currentBreadcrumbs != null && !currentBreadcrumbs.isEmpty()) {
            currentBreadcrumbs.pop();
            breadcrumbs.setValue(currentBreadcrumbs);
        }
    }
}
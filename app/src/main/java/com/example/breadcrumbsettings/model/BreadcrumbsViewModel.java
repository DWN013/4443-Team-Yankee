package com.example.breadcrumbsettings.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.Stack;

public class BreadcrumbsViewModel extends ViewModel {
    private final MutableLiveData<Stack<String>> breadcrumbs;

    public BreadcrumbsViewModel() {
        breadcrumbs = new MutableLiveData<>(new Stack<>());
        addBreadcrumb("Home"); // Add "Home" breadcrumb by default
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

    public void removeBreadcrumbsAfter(int position) {
        Stack<String> currentBreadcrumbs = breadcrumbs.getValue();
        if (currentBreadcrumbs != null) {
            while (currentBreadcrumbs.size() > position + 1) {
                currentBreadcrumbs.pop();
            }
            breadcrumbs.setValue(currentBreadcrumbs);
        }
    }

    public String serializeBreadcrumbs() {
        Stack<String> currentBreadcrumbs = breadcrumbs.getValue();
        if (currentBreadcrumbs != null) {
            return String.join(">", currentBreadcrumbs);
        }
        return "";
    }

    public void deserializeBreadcrumbs(String serializedBreadcrumbs) {
        Stack<String> newBreadcrumbs = new Stack<>();
        if (!serializedBreadcrumbs.isEmpty()) {
            String[] breadcrumbArray = serializedBreadcrumbs.split(">");
            for (String breadcrumb : breadcrumbArray) {
                newBreadcrumbs.push(breadcrumb);
            }
        }
        breadcrumbs.setValue(newBreadcrumbs);
    }
}
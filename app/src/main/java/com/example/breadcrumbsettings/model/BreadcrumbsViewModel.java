package com.example.breadcrumbsettings.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.breadcrumbsettings.MainSettingsActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BreadcrumbsViewModel extends ViewModel {
    private final MutableLiveData<Stack<String>> breadcrumbs;
    private final Map<String, Class<?>> breadcrumbActivities;

    public BreadcrumbsViewModel() {
        breadcrumbs = new MutableLiveData<>(new Stack<>());
        breadcrumbActivities = new HashMap<>();
        addBreadcrumb("Home", MainSettingsActivity.class); // Add "Home" breadcrumb by default
    }

    public LiveData<Stack<String>> getBreadcrumbs() {
        return breadcrumbs;
    }

    public void addBreadcrumb(String breadcrumb, Class<?> activityClass) {
        Stack<String> currentBreadcrumbs = breadcrumbs.getValue();
        if (currentBreadcrumbs != null) {
            currentBreadcrumbs.push(breadcrumb);
            breadcrumbs.setValue(currentBreadcrumbs);
            breadcrumbActivities.put(breadcrumb, activityClass);
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

    public Class<?> getActivityForBreadcrumb(String breadcrumb) {
        return breadcrumbActivities.get(breadcrumb);
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
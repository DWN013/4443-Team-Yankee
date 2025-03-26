package com.example.breadcrumbsettings.breadcrumbs;

import java.util.Stack;

public class BreadcrumbsManager {
    private Stack<String> breadcrumbsStack;

    public BreadcrumbsManager() {
        breadcrumbsStack = new Stack<>();
    }

    public void addBreadcrumb(String breadcrumb) {
        breadcrumbsStack.push(breadcrumb);
    }

    public void removeLastBreadcrumb() {
        if (!breadcrumbsStack.isEmpty()) {
            breadcrumbsStack.pop();
        }
    }

    public Stack<String> getBreadcrumbs() {
        return breadcrumbsStack;
    }
}

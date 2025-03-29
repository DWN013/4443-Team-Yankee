package com.example.breadcrumbsettings.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {

    public static final String PREFS_NAME = "TotalTapsPrefs";
    public static final String TOTAL_TAPS_KEY = "totalTaps";

    public static void incrementTapCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        int totalTaps = sharedPreferences.getInt(TOTAL_TAPS_KEY, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TOTAL_TAPS_KEY, totalTaps + 1);
        editor.apply();
    }

    public static int getTapCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(TOTAL_TAPS_KEY, 0);
    }

    public static void clearTapCount(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TOTAL_TAPS_KEY, 0);
        editor.apply();
    }
}
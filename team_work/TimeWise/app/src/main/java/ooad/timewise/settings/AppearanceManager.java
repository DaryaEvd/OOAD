package ooad.timewise.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;

import java.util.Locale;

import ooad.timewise.R;

public class AppearanceManager {
    final static String THEME_KEY = "theme";
    public final static String LANGUAGE_KEY = "language";
    public final static String SHARED_PREF_NAME_FOR_THEME = "Appearance";

    public void saveTheme(Context context, String theme) {
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(THEME_KEY, theme);
        passwordEditor.apply();
    }

    public void saveLanguage(Context context, String language) {
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(LANGUAGE_KEY, language);
        passwordEditor.apply();
    }

    public void switchToCurrentTheme(Context context) {
        SharedPreferences themePref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);

        String actualTheme = themePref.getString(THEME_KEY, "");

        if (actualTheme.equals(context.getString(R.string.yellow_orange_theme))) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }


    public void applyCurrentTheme(Context context) {
        switchToCurrentTheme(context);
    }

    public String getCurrentTheme(Context context) {
        SharedPreferences themePref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);
        return themePref.getString(THEME_KEY, "");
    }

}

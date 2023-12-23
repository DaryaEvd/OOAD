package ooad.timewise.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatDelegate;

import ooad.timewise.R;

public class AppearanceManager {
    final static String KEY = "theme";
    final static String SHARED_PREF_NAME_FOR_THEME = "Theme";

    public void saveTheme(Context context, String theme){
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(KEY, theme);
        passwordEditor.apply();
    }

    public void switchToCurrentTheme(Context context){
        SharedPreferences themePref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);

        String actualTheme = themePref.getString(KEY, "");

        if (actualTheme.equals(context.getString(R.string.yellow_orange_theme))){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
    }
}

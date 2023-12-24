package ooad.timewise.settings;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import java.util.Locale;

import ooad.timewise.R;

public class LanguageManager {
    public final static String LANGUAGE_KEY = "language";
    public final static String SHARED_PREF_NAME_FOR_LANG = "Lang";

    public void saveLanguage(Context context, String language) {
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_LANG, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(LANGUAGE_KEY, language);
        passwordEditor.apply();
    }

    public String getCurrentLang(Context context) {
        SharedPreferences themePref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_LANG, MODE_PRIVATE);
        return themePref.getString(LANGUAGE_KEY, "");
    }

    public void applyCurrentLang(Context context) {
        switchToCurrentLanguage(context);
    }

    public void switchToCurrentLanguage(Context context) {
        SharedPreferences themePref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_LANG, MODE_PRIVATE);

        String actualTheme = themePref.getString(LANGUAGE_KEY, "");

        if (actualTheme.equals(context.getString(R.string.english))) {
            AppCompatDelegate.setApplicationLocales(switchLocale(context, "en"));
        } else if (actualTheme.equals(context.getString(R.string.rus))) {
            AppCompatDelegate.setApplicationLocales(switchLocale(context, "ru"));
        }
    }

    public static LocaleListCompat switchLocale(Context context, String tag) {
        Locale locale = Locale.forLanguageTag(tag);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        context.getResources().updateConfiguration(configuration, null);

        return LocaleListCompat.create(locale);
    }

}

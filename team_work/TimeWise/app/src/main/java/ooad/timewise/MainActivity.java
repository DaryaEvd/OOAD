package ooad.timewise;

import static ooad.timewise.settings.AppearanceManager.SHARED_PREF_NAME_FOR_THEME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import java.util.Locale;

import ooad.timewise.password.PasswordManager;
import ooad.timewise.settings.AppearanceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        switchToCurrentLanguage();

        AppearanceManager appearanceManager = new AppearanceManager();
        appearanceManager.switchToCurrentTheme(this);
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.chooseEnterPasswordActivity(this);
    }
    private void switchToCurrentLanguage(){
        SharedPreferences themePref = getSharedPreferences(SHARED_PREF_NAME_FOR_THEME, MODE_PRIVATE);

        String actualTheme = themePref.getString(AppearanceManager.LANGUAGE_KEY, "");

        if (actualTheme.equals(getString(R.string.english))){
            switchLocale("en");
        } else if (actualTheme.equals(getString(R.string.rus))){
            switchLocale("ru");
        }
    }

    private void switchLocale(String tag){
        Locale locale = Locale.forLanguageTag(tag);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, null);
    }

}
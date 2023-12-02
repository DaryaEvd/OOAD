package ooad.timewise.password;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.content.Context;
import android.content.SharedPreferences;

public class PasswordManager {
    final static String KEY = "password";
    final static String SHARED_PREF_NAME_FOR_PASSWORD = "Password";

    public void chooseEnterPasswordActivity(Context context) {
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD,
                Context.MODE_PRIVATE);

        String defaultStr = "no password";
        String actualPassword = passwordPref.getString(KEY, defaultStr);
        if (actualPassword.equals(defaultStr)) {
            switchToActivity(NewPasswordEnteringPageActivity.class, context);
        } else {
            switchToActivity(PasswordEnteringPageActivity.class, context);
        }
    }
}

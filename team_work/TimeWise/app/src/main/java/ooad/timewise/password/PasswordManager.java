package ooad.timewise.password;

import static android.content.Context.MODE_PRIVATE;
import static ooad.timewise.ActivitiesUtils.showInfo;
import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.content.Context;
import android.content.SharedPreferences;

import ooad.timewise.StartPageActivity;

public class PasswordManager {

    public final static String NOT_EQUAL_PASSWORDS_MSG = "Passwords aren't equal :(";
    public final static String EMPTY_NEW_PASSWORD_MSG = "Please, enter new password";
    public final static String KEY = "password";
    public final static String SHARED_PREF_NAME_FOR_PASSWORD = "Password";


    public void chooseEnterPasswordActivity(Context context) {
        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD,
                MODE_PRIVATE);

        String defaultStr = "no password";
        String actualPassword = passwordPref.getString(KEY, defaultStr);
        if (actualPassword.equals(defaultStr)) {
            switchToActivity(NewPasswordEnteringPageActivity.class, context);
        } else {
            switchToActivity(PasswordEnteringPageActivity.class, context);
        }
    }

    public void saveNewPassword(String enteredNewPassword, String confirmingNewPassword, Context context) {
        if (enteredNewPassword.equals("")) {
            showInfo(EMPTY_NEW_PASSWORD_MSG, context);
            return;
        }

        if (!enteredNewPassword.equals(confirmingNewPassword)) {
            showInfo(NOT_EQUAL_PASSWORDS_MSG, context);
            return;
        }

        SharedPreferences passwordPref = context.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(KEY, enteredNewPassword);
        passwordEditor.apply();

        switchToActivity(StartPageActivity.class, context);
    }
}

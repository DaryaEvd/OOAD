package ooad.timewise.password;

import android.content.Intent;
import android.content.SharedPreferences;

public class PasswordManager {
    SharedPreferences.Editor passwordEditor;
    public PasswordManager(SharedPreferences password ){
        passwordEditor = password.edit();

//        if(password.contains("Password")){
//            Intent intent = new Intent(this, PasswordEnteringPageActivity.class);
//            startActivity(intent);
//        }
    }
}

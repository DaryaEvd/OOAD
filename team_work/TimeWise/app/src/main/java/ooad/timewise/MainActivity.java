package ooad.timewise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import ooad.timewise.password.NewPasswordEnteringPageActivity;
import ooad.timewise.password.PasswordEnteringPageActivity;
import ooad.timewise.password.PasswordManager;

public class MainActivity extends AppCompatActivity {
    String key = "key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("HUHUHU");
        SharedPreferences password = getSharedPreferences("Password", MODE_PRIVATE);
        PasswordManager passwordManager =  new PasswordManager(getSharedPreferences("Password", MODE_PRIVATE));
        SharedPreferences.Editor passwordEditor = password.edit();

//        passwordEditor.putString(key, "020020202");
//        passwordEditor.apply();
//        passwordEditor.clear();
//        passwordEditor.apply();

        if(!password.getString(key, "пусто").equals("пусто")){
            Intent intent = new Intent(this, PasswordEnteringPageActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, NewPasswordEnteringPageActivity.class);
            startActivity(intent);
        }
    }
}
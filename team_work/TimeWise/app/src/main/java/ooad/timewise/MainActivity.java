package ooad.timewise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ooad.timewise.password.PasswordManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.chooseEnterPasswordActivity(this);
    }
}
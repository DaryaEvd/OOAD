package ooad.timewise;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ooad.timewise.password.PasswordManager;
import ooad.timewise.settings.AppearanceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppearanceManager appearanceManager = new AppearanceManager();
        appearanceManager.switchToCurrentTheme(this);
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.chooseEnterPasswordActivity(this);
    }
}
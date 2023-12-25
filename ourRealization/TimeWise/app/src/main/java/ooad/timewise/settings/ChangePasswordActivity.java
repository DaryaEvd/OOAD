package ooad.timewise.settings;

import static ooad.timewise.ActivitiesUtils.showInfo;
import static ooad.timewise.ActivitiesUtils.switchToActivity;
import static ooad.timewise.password.PasswordManager.EMPTY_NEW_PASSWORD_MSG;
import static ooad.timewise.password.PasswordManager.KEY;
import static ooad.timewise.password.PasswordManager.NOT_EQUAL_PASSWORDS_MSG;
import static ooad.timewise.password.PasswordManager.SHARED_PREF_NAME_FOR_PASSWORD;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class ChangePasswordActivity extends AppCompatActivity {
    private EditText confirmingNewPassword;
    private EditText enteredNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changing_password_layout);

        Button saveBtn = findViewById(R.id.save_password_settings_new_pass_btn);
        Button backBtn = findViewById(R.id.setting_new_passw_back_btn);
        confirmingNewPassword = findViewById(R.id.confirming_settings_new_password);
        enteredNewPassword = findViewById(R.id.entered_settings_new_password);

        backBtn.setOnClickListener(this::clickOnBackBtn);
        saveBtn.setOnClickListener(this::saveNewPassword);
      }
    private void clickOnBackBtn(View v){
        ActivitiesUtils.switchToActivity(StartPageActivity.class, this);
    }

    private void saveNewPassword(View v){
        saveNewPassword(enteredNewPassword.getText().toString(), confirmingNewPassword.getText().toString());
    }

    private void saveNewPassword(String enteredNewPassword, String confirmingNewPassword) {
        if (enteredNewPassword.equals("")) {
            showInfo(EMPTY_NEW_PASSWORD_MSG, this);
            return;
        }

        if (!enteredNewPassword.equals(confirmingNewPassword)) {
            showInfo(NOT_EQUAL_PASSWORDS_MSG, this);
            return;
        }

        SharedPreferences passwordPref = this.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(KEY, enteredNewPassword);
        passwordEditor.apply();

        switchToActivity(StartPageActivity.class, this);
    }
}

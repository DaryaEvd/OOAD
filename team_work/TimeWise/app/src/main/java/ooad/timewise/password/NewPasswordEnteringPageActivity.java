package ooad.timewise.password;

import static ooad.timewise.ActivitiesUtils.showInfo;
import static ooad.timewise.ActivitiesUtils.switchToActivity;
import static ooad.timewise.password.PasswordManager.KEY;
import static ooad.timewise.password.PasswordManager.SHARED_PREF_NAME_FOR_PASSWORD;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class NewPasswordEnteringPageActivity extends AppCompatActivity {
    private final static String NOT_EQUAL_PASSWORDS_MSG = "Passwords aren't equal :(";
    private final static String EMPTY_NEW_PASSWORD_MSG = "Please, enter new password";
    private EditText confirmingNewPassword;
    private EditText enteredNewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password_entering_page);

        Button saveBtn = findViewById(R.id.save_password_new_pass_btn);
        Button exitBtn = findViewById(R.id.exit_new_passw_btn);
        confirmingNewPassword = findViewById(R.id.confirming_new_password);
        enteredNewPassword = findViewById(R.id.entered_new_password);

        exitBtn.setOnClickListener(v -> finishAffinity());
        saveBtn.setOnClickListener(v -> saveNewPassword());
    }

    private void saveNewPassword() {
        if (enteredNewPassword.getText().toString().equals("")) {
            showInfo(EMPTY_NEW_PASSWORD_MSG, this);
            return;
        }

        String newPassword = enteredNewPassword.getText().toString();
        String confNewPassword = confirmingNewPassword.getText().toString();

        if (!newPassword.equals(confNewPassword)) {
            showInfo(NOT_EQUAL_PASSWORDS_MSG, this);
            return;
        }

        SharedPreferences passwordPref = this.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD, MODE_PRIVATE);
        SharedPreferences.Editor passwordEditor = passwordPref.edit();
        passwordEditor.putString(KEY, newPassword);
        passwordEditor.apply();

        switchToActivity(StartPageActivity.class, this);
    }
}

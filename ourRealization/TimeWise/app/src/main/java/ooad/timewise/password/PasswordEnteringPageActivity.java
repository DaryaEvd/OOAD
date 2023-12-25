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

public class PasswordEnteringPageActivity extends AppCompatActivity {
    private final static String INCORRECT_PASSWORD_MSG = "Password is incorrect :(";
    private EditText enteredPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_entering_page);

        Button continueBtn = findViewById(R.id.continue_passw_btn);
        Button exitBtn = findViewById(R.id.exit_passw_btn);
        enteredPassword = findViewById(R.id.entered_new_password);

        exitBtn.setOnClickListener(v -> finishAffinity());

        SharedPreferences passwordPref = this.getSharedPreferences(SHARED_PREF_NAME_FOR_PASSWORD, MODE_PRIVATE);
        String actualPassword = passwordPref.getString(KEY, "");
        continueBtn.setOnClickListener(v -> clickOnContinueBtn(actualPassword));
    }

    private void clickOnContinueBtn(String actualPassword) {
        if (enteredPassword.getText().toString().equals(actualPassword)) {
            switchToActivity(StartPageActivity.class, this);
        } else {
            showInfo(INCORRECT_PASSWORD_MSG, this);
        }
    }
}

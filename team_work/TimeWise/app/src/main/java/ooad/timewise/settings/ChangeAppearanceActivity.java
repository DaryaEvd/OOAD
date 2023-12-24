package ooad.timewise.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;

public class ChangeAppearanceActivity extends AppCompatActivity {
    private static final AppearanceManager appearanceManager = new AppearanceManager();
    private static final LanguageManager langManager = new LanguageManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changing_appearance_layout);

        Button backBtn = findViewById(R.id.change_appearance_back_btn);
        backBtn.setOnClickListener(this::clickOnBackBtn);
        Button switchToDarkThemeBtn = findViewById(R.id.switch_to_dark_theme_btn);
        switchToDarkThemeBtn.setOnClickListener(this::clickOnDarkThemeBtn);
        Button switchToYellowOrangeThemeBtn = findViewById(R.id.switch_to_orange_yellow_theme);
        switchToYellowOrangeThemeBtn.setOnClickListener(this::clickOnYellowOrangeThemeBtn);
    }


    private void clickOnDarkThemeBtn(View v) {
        handleThemeChange(R.string.dark_theme, R.string.confirm_password_dark, R.string.theme_already_set_dark);
    }

    private void clickOnYellowOrangeThemeBtn(View v) {
        handleThemeChange(R.string.yellow_orange_theme, R.string.confirm_password_dark, R.string.theme_already_set_light);
    }

    private void handleThemeChange(int themeResId, int confirmMessageResId, int alreadySetMessageResId) {
        String themeString = getString(themeResId);

        if (!appearanceManager.getCurrentTheme(this).equals(themeString)) {
            appearanceManager.saveTheme(this, themeString);
            showPasswordConfirmationMessage(confirmMessageResId, alreadySetMessageResId);
            appearanceManager.applyCurrentTheme(this);
        } else {
            showPasswordConfirmationMessage(alreadySetMessageResId, alreadySetMessageResId);
        }
    }

    private void showPasswordConfirmationMessage(int confirmMessageResId, int alreadySetMessageResId) {
        int messageResId = (langManager.getCurrentLang(this).equals(getString(R.string.english)))
                ? confirmMessageResId
                : alreadySetMessageResId;

        ActivitiesUtils.showInfo(getString(messageResId), this);
    }


    private void clickOnBackBtn(View v) {
        ActivitiesUtils.switchToActivity(SettingsActivity.class, this);
    }
}

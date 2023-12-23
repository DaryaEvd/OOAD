package ooad.timewise.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;

public class ChangeAppearanceActivity extends AppCompatActivity {

    private static final AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_appearance_page);

        Button backBtn = findViewById(R.id.change_appearance_back_btn);
        backBtn.setOnClickListener(this::clickOnBackBtn);
        Button switchToDarkThemeBtn = findViewById(R.id.switch_to_dark_theme_btn);
        switchToDarkThemeBtn.setOnClickListener(this::clickOnDarkThemeBtn);
        Button switchToYellowOrangeThemeBtn = findViewById(R.id.switch_to_orange_yellow_theme);
        switchToYellowOrangeThemeBtn.setOnClickListener(this::clickOnYellowOrangeThemeBtn);
    }

    private void clickOnDarkThemeBtn(View v) {
        if (!appearanceManager.getCurrentTheme(this).equals(getString(R.string.dark_theme))) {
            appearanceManager.saveTheme(this, getString(R.string.dark_theme));
            ActivitiesUtils.showInfo("Please, confirm your password", this);
            appearanceManager.applyCurrentTheme(this); // Apply the theme without recreating the activity
        } else {
            ActivitiesUtils.showInfo("You already have the Dark theme", this);
        }

//        appearanceManager.saveTheme(this, getString(R.string.dark_theme));
//        appearanceManager.applyCurrentTheme(this); // Apply the theme without recreating the activity
    }

    private void clickOnYellowOrangeThemeBtn(View v) {
        if (!appearanceManager.getCurrentTheme(this).equals(getString(R.string.yellow_orange_theme))) {
            appearanceManager.saveTheme(this, getString(R.string.yellow_orange_theme));
            ActivitiesUtils.showInfo("Please, confirm your password", this);
            appearanceManager.applyCurrentTheme(this);
        } else {
            ActivitiesUtils.showInfo("You already have the Light theme", this);
        }
    }

    private void clickOnBackBtn(View v) {
        ActivitiesUtils.switchToActivity(SettingsActivity.class, this);
    }
}

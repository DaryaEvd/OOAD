package ooad.timewise.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;

public class ChangeLanguageActivity extends AppCompatActivity {
    private static final AppearanceManager appearanceManager = new AppearanceManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.changing_language_layout);

        Button backBtn = findViewById(R.id.change_appearance_back_btn);
        backBtn.setOnClickListener(this::clickOnBackBtn);
        Button switchToEnglishBtn = findViewById(R.id.switch_to_en_language);
        switchToEnglishBtn.setOnClickListener(this::clickOnEnglishBtn);
        Button switchToRussianBtn = findViewById(R.id.switch_to_rus_language);
        switchToRussianBtn.setOnClickListener(this::clickOnRussianBtn);
    }

    private void clickOnEnglishBtn(View v) {
        if (!appearanceManager.getCurrentTheme(this).equals(getString(R.string.english))) {
            appearanceManager.saveLanguage(this, getString(R.string.english));
            ActivitiesUtils.showInfo("Please, restart the app", this);
        }
    }

    private void clickOnRussianBtn(View v) {
        if (!appearanceManager.getCurrentTheme(this).equals(getString(R.string.rus))) {
            appearanceManager.saveLanguage(this, getString(R.string.rus));
            ActivitiesUtils.showInfo("Please, restart the app", this);
        }
    }

    private void clickOnBackBtn(View v) {
        ActivitiesUtils.switchToActivity(SettingsActivity.class, this);
    }
}

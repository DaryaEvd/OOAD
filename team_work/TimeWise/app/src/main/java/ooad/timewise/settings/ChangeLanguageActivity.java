package ooad.timewise.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;

public class ChangeLanguageActivity extends AppCompatActivity {
    private static final LanguageManager langManager = new LanguageManager();

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
        if (!langManager.getCurrentLang(this).equals(getString(R.string.english))) {
            langManager.saveLanguage(this, getString(R.string.english));
            langManager.applyCurrentLang(this);
        }
        else {
            ActivitiesUtils.showInfo("Language of app is english already :)", this);
        }
    }

    private void clickOnRussianBtn(View v) {
        if (!langManager.getCurrentLang(this).equals(getString(R.string.rus))) {
            langManager.saveLanguage(this, getString(R.string.rus));
            langManager.applyCurrentLang(this);
        }
        else {
            ActivitiesUtils.showInfo("Язык приложения и так русский :)", this);
        }
    }

    private void clickOnBackBtn(View v) {
        ActivitiesUtils.switchToActivity(SettingsActivity.class, this);
    }
}

package ooad.timewise.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ooad.timewise.ActivitiesUtils;
import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        Button backBtn = findViewById(R.id.settings_go_to_start_page_btn);
        backBtn.setOnClickListener(this::clickOnBackBtn);
        Button switchToChangeAppearanceBtn = findViewById(R.id.change_appearance_btn);
        switchToChangeAppearanceBtn.setOnClickListener(this::clickSwitchAppearanceBtn);
        Button switchToChangeLanguageBtn = findViewById(R.id.change_language_btn);
        switchToChangeLanguageBtn.setOnClickListener(this::clickSwitchLanguageBtn);
    }

    private void clickOnBackBtn(View v){
        ActivitiesUtils.switchToActivity(StartPageActivity.class, this);
    }

    private void clickSwitchAppearanceBtn(View v){
        ActivitiesUtils.switchToActivity(ChangeAppearanceActivity.class, this);
    }
    private void clickSwitchLanguageBtn(View v){
        ActivitiesUtils.switchToActivity(ChangeLanguageActivity.class, this);
    }

}
package ooad.timewise;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.alarms.AlarmsListActivity;
import ooad.timewise.calendar.CalendarActivity;
import ooad.timewise.cheerup.CheerUpActivity;
import ooad.timewise.notes.NotesListActivity;
import ooad.timewise.settings.SettingsActivity;

public class StartPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        Button cheerUpBtn = findViewById(R.id.cheer_up_btn);
        Button notesBtn = findViewById(R.id.notes_btn);
        Button calendarBtn = findViewById(R.id.calendar_btn);
        Button alarmsBtn = findViewById(R.id.alarms_btn);
        Button settingsBtn = findViewById(R.id.settings_btn);
        Button exitBtn = findViewById(R.id.exit_start_page_btn);

        exitBtn.setOnClickListener(v -> finishAffinity());
        calendarBtn.setOnClickListener(this::clickOnCalendarBtn);
        alarmsBtn.setOnClickListener(this::clickOnAlarmsBtn);
        cheerUpBtn.setOnClickListener(this::clickOnCheerUpBtn);
        settingsBtn.setOnClickListener(this::clickOnSettingsBtn);
        notesBtn.setOnClickListener(this::clickOnNotesBtn);
    }

    private void clickOnCalendarBtn(View v) {
        switchToActivity(CalendarActivity.class, this);
    }

    private void clickOnAlarmsBtn(View v) {
        switchToActivity(AlarmsListActivity.class, this);
    }

    private void clickOnCheerUpBtn(View v) {
        switchToActivity(CheerUpActivity.class, this);
    }

    private void clickOnNotesBtn(View v) {
        switchToActivity(NotesListActivity.class, this);
    }

    private void clickOnSettingsBtn(View v) {
        switchToActivity(SettingsActivity.class, this);
    }
}

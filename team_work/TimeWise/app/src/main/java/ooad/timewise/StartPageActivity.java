package ooad.timewise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartPageActivity extends AppCompatActivity {

    private Button cheerUpBtn;
    private Button notesBtn;
    private Button calendarBtn;
    private Button alarmsBtn;
    private Button settingsBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        cheerUpBtn = findViewById(R.id.cheer_up_btn);
        notesBtn = findViewById(R.id.notes_btn);
        calendarBtn = findViewById(R.id.calendar_btn);
        alarmsBtn = findViewById(R.id.alarms_btn);
        exitBtn = findViewById(R.id.exit_btn);

        exitBtn.setOnClickListener(v -> finishAffinity());
    }

}

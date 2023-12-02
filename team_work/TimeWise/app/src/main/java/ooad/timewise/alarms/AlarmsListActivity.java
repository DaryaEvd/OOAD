package ooad.timewise.alarms;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class AlarmsListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarms_list);

        Button backBtn = findViewById(R.id.alarms_back_btn);

        backBtn.setOnClickListener(this::clickOnBackBtn);
    }

    private void clickOnBackBtn(View v) {
        switchToActivity(StartPageActivity.class, this);
    }
}
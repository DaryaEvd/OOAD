package ooad.timewise.notes;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class NotesListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_list);

        Button backBtn = findViewById(R.id.notes_back_btn);

        backBtn.setOnClickListener(this::clickOnBackBtn);
    }

    private void clickOnBackBtn(View v) {
        switchToActivity(StartPageActivity.class, this);
    }
}
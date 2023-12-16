package ooad.timewise.cheerup;

import androidx.appcompat.app.AppCompatActivity;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class CheerUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cheer_up_page);

        Button showCatBtn = findViewById(R.id.get_cat_cheerup_btn);
        Button backBtn = findViewById(R.id.cheerup_back_btn);

        showCatBtn.setOnClickListener(this::clickOnShowCatBtn);
        backBtn.setOnClickListener(this::clickOnBackBtn);

    }

    private void clickOnBackBtn(View view) {
        switchToActivity(StartPageActivity.class, this);
    }

    private void clickOnShowCatBtn(View view) {
        switchToActivity(CatPictureActivity.class, this);
    }

}
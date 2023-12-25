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
        setContentView(R.layout.cheer_up_layout);

        Button showCatBtn = findViewById(R.id.get_cat_cheerup_btn);
        Button showQuoteBtn = findViewById(R.id.get_quote);
        Button backBtn = findViewById(R.id.cheerup_back_btn);

        showCatBtn.setOnClickListener(this::clickOnShowCatBtn);
        showQuoteBtn.setOnClickListener(this::clickOnShowQuoteBtn);
        backBtn.setOnClickListener(this::clickOnBackBtn);
    }

    private void clickOnShowCatBtn(View view) {
        switchToActivity(CatPictureActivity.class, this);
    }

    private void clickOnShowQuoteBtn(View view) {
        switchToActivity(QuoteActivity.class, this);
    }

    private void clickOnBackBtn(View view) {
        switchToActivity(StartPageActivity.class, this);
    }
}

package ooad.timewise.password;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class PasswordEnteringPageActivity extends AppCompatActivity {

    private Button continueBtn;
    private Button exitBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_entering_page);

        continueBtn = findViewById(R.id.continue_passw_btn);
        exitBtn = findViewById(R.id.exit_passw_btn);

        exitBtn.setOnClickListener(v -> finishAffinity());
        continueBtn.setOnClickListener(this::clickOnContinueBtn);
    }

    private void clickOnContinueBtn(View v){
        Intent intent = new Intent(this, StartPageActivity.class);
        startActivity(intent);
    }
}

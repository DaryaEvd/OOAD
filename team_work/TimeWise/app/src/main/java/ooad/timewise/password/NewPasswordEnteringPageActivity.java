package ooad.timewise.password;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import ooad.timewise.R;

public class NewPasswordEnteringPageActivity extends AppCompatActivity {
    private Button saveBtn;
    private Button exitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_password_entering_page);

        saveBtn = findViewById(R.id.save_password_new_pass_btn);
        exitBtn = findViewById(R.id.exit_new_passw_btn);

        exitBtn.setOnClickListener(v -> finishAffinity());
    }

}

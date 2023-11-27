package ccifit.nsu.clown;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextPassword;
    private EditText editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
    }

    public void onSubmitClicked(View view) {
        String password = editTextPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (password.equals(confirmPassword)) {
            // Passwords match, you can proceed
            // Here you can start the main interface activity
            startMainInterfaceActivity();
        } else {
            // Passwords do not match
            Toast.makeText(this, "Passwords do not match. Try again.", Toast.LENGTH_SHORT).show();
        }
    }

    private void startMainInterfaceActivity() {
        Intent intent = new Intent(this, MainInterfaceActivity.class);
        startActivity(intent);
    }
}

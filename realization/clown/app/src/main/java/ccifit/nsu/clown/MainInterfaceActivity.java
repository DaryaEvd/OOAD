package ccifit.nsu.clown;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainInterfaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        Button btnNotesList = findViewById(R.id.btnNotesList);
        Button btnAlarmsList = findViewById(R.id.btnAlarmsList);
        Button btnCheerUp = findViewById(R.id.btnCheerUp);
        Button btnGeneratePic = findViewById(R.id.btnGeneratePic);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnNotesList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for Notes List button
                Toast.makeText(MainInterfaceActivity.this, "Notes List Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnAlarmsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for Alarms List button
                Toast.makeText(MainInterfaceActivity.this, "Alarms List Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnCheerUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for Cheer Up button
                Toast.makeText(MainInterfaceActivity.this, "Cheer Up Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnGeneratePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for Generate Pic button
                Toast.makeText(MainInterfaceActivity.this, "Generate Pic Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Placeholder code for Settings button
                Toast.makeText(MainInterfaceActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

package ooad.timewise.alarms.activities;

import static ooad.timewise.ActivitiesUtils.switchToActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import ooad.timewise.R;
import ooad.timewise.StartPageActivity;

public class AlarmsListActivity extends AppCompatActivity {

    public static final String CHANNEL_ID = "ALARM_SERVICE_CHANNEL";
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createNotificationChannel();
        setContentView(R.layout.alarms_list);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        edit = sharedPreferences.edit();

       // Button backBtn = findViewById(R.id.alarms_back_btn);

       // backBtn.setOnClickListener(this::clickOnBackBtn);
    }

    private void clickOnBackBtn(View v) {
        switchToActivity(StartPageActivity.class, this);
    }
    private void createNotificationChannel() {
        NotificationChannel serviceChannel = new NotificationChannel(
                CHANNEL_ID,
                "Service Channel",
                NotificationManager.IMPORTANCE_HIGH
        );

        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(serviceChannel);
    }
}
package ooad.timewise.alarms.adapter;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Locale;

import ooad.timewise.R;
import ooad.timewise.alarms.model.Alarm;
import ooad.timewise.alarms.util.DayUtil;
import ooad.timewise.alarms.util.OnToggleAlarmListener;
import ooad.timewise.databinding.SingleAlarmBinding;

public class AlarmViewHolder extends RecyclerView.ViewHolder {
    private final TextView alarmTime;
    private final ImageView alarmRecurring;
    private final TextView alarmRecurringDays;
    private final TextView alarmTitle;
    private final Switch alarmStarted;
    private final ImageButton deleteAlarm;
    private final View itemView;
    private final TextView alarmDay;
    public AlarmViewHolder(@NonNull SingleAlarmBinding itemAlarmBinding) {
        super(itemAlarmBinding.getRoot());
        alarmTime = itemAlarmBinding.itemAlarmTime;
        alarmStarted = itemAlarmBinding.itemAlarmStarted;
        alarmRecurring = itemAlarmBinding.itemAlarmRecurring;
        alarmRecurringDays = itemAlarmBinding.itemAlarmRecurringDays;
        alarmTitle = itemAlarmBinding.itemAlarmTitle;
        deleteAlarm= itemAlarmBinding.itemAlarmRecurringDelete;
        alarmDay = itemAlarmBinding.itemAlarmDay;
        this.itemView=itemAlarmBinding.getRoot();
    }

    public void bind(Alarm alarm, OnToggleAlarmListener listener) {
        String alarmText = String.format(Locale.UK, "%02d:%02d", alarm.getHour(), alarm.getMinute());

        alarmTime.setText(alarmText);
        alarmStarted.setChecked(alarm.isStarted());

        if (alarm.isRecurring()) {
            alarmRecurring.setImageResource(R.drawable.ic_repeat_black_24dp);
            alarmRecurringDays.setText(alarm.getRecurringDaysText());
        } else {
            alarmRecurring.setImageResource(R.drawable.ic_looks_one_black_24dp);
            alarmRecurringDays.setText("Once Off");
        }

        if (alarm.getTitle().length() != 0) {
            alarmTitle.setText(alarm.getTitle());
        } else {
            alarmTitle.setText("My alarm");
        }
        if(alarm.isRecurring()){
            alarmDay.setText(alarm.getRecurringDaysText());
        }
        else {
            alarmDay.setText(DayUtil.getDay(alarm.getHour(),alarm.getMinute()));
        }
        alarmStarted.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(buttonView.isShown() || buttonView.isPressed())
                listener.onToggle(alarm);
        });

        deleteAlarm.setOnClickListener(view -> listener.onDelete(alarm));

        itemView.setOnClickListener(view -> listener.onItemClick(alarm,view));
    }
}
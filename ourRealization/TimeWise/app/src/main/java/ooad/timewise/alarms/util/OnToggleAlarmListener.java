package ooad.timewise.alarms.util;

import android.view.View;

import ooad.timewise.alarms.model.Alarm;

public interface OnToggleAlarmListener {
    void onToggle(Alarm alarm);
    void onDelete(Alarm alarm);
    void onItemClick(Alarm alarm,View view);
}

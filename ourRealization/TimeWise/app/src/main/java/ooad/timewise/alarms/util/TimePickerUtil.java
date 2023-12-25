package ooad.timewise.alarms.util;

import android.widget.TimePicker;

public final class TimePickerUtil {
    public static int getTimePickerHour(TimePicker tp) {
        return tp.getHour();
    }

    public static int getTimePickerMinute(TimePicker tp) {
        return tp.getMinute();
    }
}

package ooad.timewise.alarms.fragments;

import android.app.Activity;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import java.util.Random;

import ooad.timewise.R;
import ooad.timewise.alarms.model.Alarm;
import ooad.timewise.alarms.util.DayUtil;
import ooad.timewise.alarms.util.TimePickerUtil;
import ooad.timewise.alarms.viewmodel.CreateAlarmViewModel;
import ooad.timewise.databinding.FragmentCreateAlarmBinding;

public class CreateAlarmFragment extends Fragment {
    FragmentCreateAlarmBinding fragmentCreateAlarmBinding;
    private CreateAlarmViewModel createAlarmViewModel;
    boolean isVibrate=false;
    String tone;
    Alarm alarm;
    Ringtone ringtone;
    public CreateAlarmFragment() {
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            alarm= (Alarm) getArguments().getSerializable(getString(R.string.arg_alarm_obj));
        }
        createAlarmViewModel = ViewModelProviders.of(this).get(CreateAlarmViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentCreateAlarmBinding = FragmentCreateAlarmBinding.inflate(inflater,container,false);
        View v = fragmentCreateAlarmBinding.getRoot();
        tone=RingtoneManager.getActualDefaultRingtoneUri(this.getContext(), RingtoneManager.TYPE_ALARM).toString();
        ringtone = RingtoneManager.getRingtone(getContext(), Uri.parse(tone));
        fragmentCreateAlarmBinding.fragmentCreatealarmSetToneName.setText(ringtone.getTitle(getContext()));
        if(alarm!=null){
            updateAlarmInfo(alarm);
        }
        fragmentCreateAlarmBinding.fragmentCreatealarmRecurring.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                fragmentCreateAlarmBinding.fragmentCreatealarmRecurringOptions.setVisibility(View.VISIBLE);
            } else {
                fragmentCreateAlarmBinding.fragmentCreatealarmRecurringOptions.setVisibility(View.GONE);
            }
        });

        fragmentCreateAlarmBinding.fragmentCreatealarmScheduleAlarm.setOnClickListener(v1 -> {
            if(alarm!=null) {
                updateAlarm();
            }
            else{
                scheduleAlarm();
            }

            Navigation.findNavController(v1).navigate(R.id.action_createAlarmFragment_to_alarmsListFragment);
        });

        fragmentCreateAlarmBinding.fragmentCreatealarmCardSound.setOnClickListener(view -> {
            Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_ALARM);
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select Alarm Sound");
            intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, (Uri) Uri.parse(tone));
            startActivityForResult(intent, 5);
        });

        fragmentCreateAlarmBinding.fragmentCreatealarmVibrateSwitch.setOnCheckedChangeListener((compoundButton, b) -> {
            isVibrate= b;
        });

        fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker.setOnTimeChangedListener((timePicker, i, i1) ->
                fragmentCreateAlarmBinding.fragmentCreatealarmScheduleAlarmHeading.setText
                        (DayUtil.getDay(TimePickerUtil.getTimePickerHour(timePicker),TimePickerUtil.getTimePickerMinute(timePicker))));

        return v;
    }

    private void scheduleAlarm() {
        String alarmTitle=getString(R.string.alarm_title);
        int alarmId = new Random().nextInt(Integer.MAX_VALUE);
        if(!fragmentCreateAlarmBinding.fragmentCreatealarmTitle.getText().toString().isEmpty()){
            alarmTitle=fragmentCreateAlarmBinding.fragmentCreatealarmTitle.getText().toString();
        }
        Alarm alarm = new Alarm(
                alarmId,
                TimePickerUtil.getTimePickerHour(fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker),
                TimePickerUtil.getTimePickerMinute(fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker),
                alarmTitle,
                true,
                fragmentCreateAlarmBinding.fragmentCreatealarmRecurring.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckMon.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckTue.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckWed.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckThu.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckFri.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSat.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSun.isChecked(),
                tone,
                isVibrate
        );

        createAlarmViewModel.insert(alarm);

        alarm.schedule(requireContext());
    }

    private void updateAlarm(){
        String alarmTitle=getString(R.string.alarm_title);
        if(!fragmentCreateAlarmBinding.fragmentCreatealarmTitle.getText().toString().isEmpty()){
            alarmTitle=fragmentCreateAlarmBinding.fragmentCreatealarmTitle.getText().toString();
        }
        Alarm updatedAlarm = new Alarm(
                alarm.getAlarmId(),
                TimePickerUtil.getTimePickerHour(fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker),
                TimePickerUtil.getTimePickerMinute(fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker),
                alarmTitle,
                true,
                fragmentCreateAlarmBinding.fragmentCreatealarmRecurring.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckMon.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckTue.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckWed.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckThu.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckFri.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSat.isChecked(),
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSun.isChecked(),
                tone,
                isVibrate
        );
        createAlarmViewModel.update(updatedAlarm);
        updatedAlarm.schedule(requireContext());
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        if (resultCode == Activity.RESULT_OK && requestCode == 5) {
            Uri uri = intent.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            ringtone = RingtoneManager.getRingtone(getContext(), uri);
            String title = ringtone.getTitle(getContext());
            if (uri != null) {
                tone=uri.toString();
                if(title!=null && !title.isEmpty())
                    fragmentCreateAlarmBinding.fragmentCreatealarmSetToneName.setText(title);
            } else {
                fragmentCreateAlarmBinding.fragmentCreatealarmSetToneName.setText("");
            }
        }
    }

    private void updateAlarmInfo(Alarm alarm){
        fragmentCreateAlarmBinding.fragmentCreatealarmTitle.setText(alarm.getTitle());
        fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker.setHour(alarm.getHour());
        fragmentCreateAlarmBinding.fragmentCreatealarmTimePicker.setMinute(alarm.getMinute());
        if(alarm.isRecurring()){
            fragmentCreateAlarmBinding.fragmentCreatealarmRecurring.setChecked(true);
            fragmentCreateAlarmBinding.fragmentCreatealarmRecurringOptions.setVisibility(View.VISIBLE);
            if(alarm.isMonday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckMon.setChecked(true);
            if(alarm.isTuesday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckTue.setChecked(true);
            if(alarm.isWednesday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckWed.setChecked(true);
            if(alarm.isThursday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckThu.setChecked(true);
            if(alarm.isFriday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckFri.setChecked(true);
            if(alarm.isSaturday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSat.setChecked(true);
            if(alarm.isSunday())
                fragmentCreateAlarmBinding.fragmentCreatealarmCheckSun.setChecked(true);
            tone=alarm.getTone();
            ringtone = RingtoneManager.getRingtone(getContext(), Uri.parse(tone));
            fragmentCreateAlarmBinding.fragmentCreatealarmSetToneName.setText(ringtone.getTitle(getContext()));
            if(alarm.isVibrate())
                fragmentCreateAlarmBinding.fragmentCreatealarmVibrateSwitch.setChecked(true);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        fragmentCreateAlarmBinding = null;
    }
}
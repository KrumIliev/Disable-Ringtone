package com.krumiliev.disableringtone;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.zenkun.datetimepicker.time.RadialPickerLayout;
import com.zenkun.datetimepicker.time.TimePickerDialog;

/**
 * Created by KrumIliev on 2/3/2015.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private TextView mStartTime, mStopTime;
    private Switch mRepeatWeekly, mVibration, mNotification;
    private TextView mDayMon, mDayTue, mDayWed, mDayThu, mDayFri, mDaySat, mDaySun;
    private SharedPreferences mSharedPreferences;
    private RingtoneAlarms mRingtoneAlarms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mRingtoneAlarms = new RingtoneAlarms(getActivity());

        mRepeatWeekly = (Switch) rootView.findViewById(R.id.main_repeat_button);
        mRepeatWeekly.setChecked(mSharedPreferences.getBoolean(getString(R.string.pref_repeat), true));
        mVibration = (Switch) rootView.findViewById(R.id.main_vibration_button);
        mVibration.setChecked(mSharedPreferences.getBoolean(getString(R.string.pref_vibration), true));
        mNotification = (Switch) rootView.findViewById(R.id.main_notification_button);
        mNotification.setChecked(mSharedPreferences.getBoolean(getString(R.string.pref_notification), true));

        initTime(rootView);

        // Set start time button
        rootView.findViewById(R.id.main_start_time_set).setOnClickListener(this);
        // Set stop time button
        rootView.findViewById(R.id.main_stop_time_set).setOnClickListener(this);
        // Set button
        rootView.findViewById(R.id.main_set).setOnClickListener(this);
        // Disable button
        rootView.findViewById(R.id.main_disable).setOnClickListener(this);

        initDayButtons(rootView);
        return rootView;
    }

    private void initTime(View rootView) {
        mStartTime = (TextView) rootView.findViewById(R.id.main_start_time_text);
        mStopTime = (TextView) rootView.findViewById(R.id.main_stop_time_text);

        int startTimeHour = mSharedPreferences.getInt(getString(R.string.pref_time_disable_hour), 12);
        int startTimeMin = mSharedPreferences.getInt(getString(R.string.pref_time_disable_min), 0);
        int stopTimeHour = mSharedPreferences.getInt(getString(R.string.pref_time_enable_hour), 12);
        int stopTimeMin = mSharedPreferences.getInt(getString(R.string.pref_time_enable_min), 0);

        mStartTime.setText(MainActivity.getTimeString(startTimeHour, startTimeMin));
        mStopTime.setText(MainActivity.getTimeString(stopTimeHour, stopTimeMin));
    }

    private void initDayButtons(View rootView) {
        mDayMon = (TextView) rootView.findViewById(R.id.main_days_mon);
        mDayTue = (TextView) rootView.findViewById(R.id.main_days_tus);
        mDayWed = (TextView) rootView.findViewById(R.id.main_days_wen);
        mDayThu = (TextView) rootView.findViewById(R.id.main_days_thu);
        mDayFri = (TextView) rootView.findViewById(R.id.main_days_fri);
        mDaySat = (TextView) rootView.findViewById(R.id.main_days_sat);
        mDaySun = (TextView) rootView.findViewById(R.id.main_days_sun);

        mDayMon.setOnClickListener(this);
        mDayTue.setOnClickListener(this);
        mDayWed.setOnClickListener(this);
        mDayThu.setOnClickListener(this);
        mDayFri.setOnClickListener(this);
        mDaySat.setOnClickListener(this);
        mDaySun.setOnClickListener(this);

        if (mSharedPreferences.getBoolean(getString(R.string.pref_mon), false)) {
            mDayMon.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDayMon.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_tue), false)) {
            mDayTue.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDayTue.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_wen), false)) {
            mDayWed.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDayWed.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_thu), false)) {
            mDayThu.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDayThu.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_fri), false)) {
            mDayFri.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDayFri.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_sat), false)) {
            mDaySat.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDaySat.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }

        if (mSharedPreferences.getBoolean(getString(R.string.pref_sun), false)) {
            mDaySun.setBackgroundColor(getResources().getColor(R.color.blue));
        } else {
            mDaySun.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_start_time_set:
                setStartTime();
                break;
            case R.id.main_stop_time_set:
                setStopTime();
                break;
            case R.id.main_days_mon:
                setDayButton(mDayMon, getString(R.string.pref_mon));
                break;
            case R.id.main_days_tus:
                setDayButton(mDayTue, getString(R.string.pref_tue));
                break;
            case R.id.main_days_wen:
                setDayButton(mDayWed, getString(R.string.pref_wen));
                break;
            case R.id.main_days_thu:
                setDayButton(mDayThu, getString(R.string.pref_thu));
                break;
            case R.id.main_days_fri:
                setDayButton(mDayFri, getString(R.string.pref_fri));
                break;
            case R.id.main_days_sat:
                setDayButton(mDaySat, getString(R.string.pref_sat));
                break;
            case R.id.main_days_sun:
                setDayButton(mDaySun, getString(R.string.pref_sun));
                break;
            case R.id.main_set:
                startAlarms();
                break;
            case R.id.main_disable:
                stopAlarms();
                break;
        }
    }

    private void setStartTime() {
        int hour = mSharedPreferences.getInt(getString(R.string.pref_time_disable_hour), 12);
        int min = mSharedPreferences.getInt(getString(R.string.pref_time_disable_min), 0);

        TimePickerDialog time = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                mStartTime.setText(MainActivity.getTimeString(hourOfDay, minute));
                mSharedPreferences.edit().putInt(getString(R.string.pref_time_disable_hour), hourOfDay).commit();
                mSharedPreferences.edit().putInt(getString(R.string.pref_time_disable_min), minute).commit();
            }
        }, hour, min, true);

        time.show(getActivity().getSupportFragmentManager(), "time");
    }

    private void setStopTime() {
        int hour = mSharedPreferences.getInt(getString(R.string.pref_time_enable_hour), 12);
        int min = mSharedPreferences.getInt(getString(R.string.pref_time_enable_min), 0);

        TimePickerDialog time = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                mStopTime.setText(MainActivity.getTimeString(hourOfDay, minute));
                mSharedPreferences.edit().putInt(getString(R.string.pref_time_enable_hour), hourOfDay).commit();
                mSharedPreferences.edit().putInt(getString(R.string.pref_time_enable_min), minute).commit();
            }
        }, hour, min, true);

        time.show(getActivity().getSupportFragmentManager(), "time");
    }


    private void setDayButton(TextView button, String prefTag) {
        if (mSharedPreferences.getBoolean(prefTag, false)) {
            button.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
            mSharedPreferences.edit().putBoolean(prefTag, false).commit();
        } else {
            button.setBackgroundColor(getResources().getColor(R.color.blue));
            mSharedPreferences.edit().putBoolean(prefTag, true).commit();
        }
    }

    private void startAlarms() {
        ComponentName receiver = new ComponentName(getActivity(), BootReceiver.class);
        PackageManager pm = getActivity().getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);

        mSharedPreferences.edit().putBoolean(getString(R.string.pref_repeat), mRepeatWeekly.isChecked()).commit();
        mSharedPreferences.edit().putBoolean(getString(R.string.pref_notification), mNotification.isChecked()).commit();
        mSharedPreferences.edit().putBoolean(getString(R.string.pref_vibration), mVibration.isChecked()).commit();
        mRingtoneAlarms.startAlarms();
    }

    private void stopAlarms() {
        ComponentName receiver = new ComponentName(getActivity(), BootReceiver.class);
        PackageManager pm = getActivity().getPackageManager();

        pm.setComponentEnabledSetting(receiver,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);

        mRingtoneAlarms.stopAlarms();
    }
}

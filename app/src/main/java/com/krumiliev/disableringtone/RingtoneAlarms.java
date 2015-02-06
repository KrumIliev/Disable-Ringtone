package com.krumiliev.disableringtone;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by KrumIliev on 2/3/2015.
 */
public class RingtoneAlarms {

    private Context mContext;
    private SharedPreferences mSharedPreferences;
    private AlarmManager mAlarmManager;
    private HashMap<String, PendingIntent> mEnableSoundIntents;
    private HashMap<String, PendingIntent> mDisableSoundIntents;
    private NotificationManager mNotificationManager;

    private final int NOTIFICATION_ID = 1111;

    public RingtoneAlarms(Context context) {
        mContext = context;
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        mAlarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mEnableSoundIntents = new HashMap<>();
        mDisableSoundIntents = new HashMap<>();
    }

    private void setupIntents() {
        mEnableSoundIntents.clear();
        mDisableSoundIntents.clear();

        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_mon), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_mon),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_mon)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_mon),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_mon)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_tue), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_tue),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_tue)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_tue),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_tue)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_wen), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_wen),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_wen)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_wen),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_wen)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_thu), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_thu),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_thu)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_thu),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_thu)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_fri), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_fri),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_fri)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_fri),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_fri)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_sat), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_sat),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_sat)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_sat),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_sat)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_sun), false)) {
            mEnableSoundIntents.put(
                    mContext.getString(R.string.pref_sun),
                    setupEnableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_start_sun)));
            mDisableSoundIntents.put(
                    mContext.getString(R.string.pref_sun),
                    setupDisableSoundIntent(mContext.getResources().getInteger(R.integer.alarm_stop_sun)));
        }
    }

    private PendingIntent setupEnableSoundIntent(int id) {
        Intent enableSoundIntent = new Intent(mContext, EnableReceiver.class);
        return PendingIntent.getBroadcast(mContext, id, enableSoundIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    private PendingIntent setupDisableSoundIntent(int id) {
        Intent disableSoundIntent = new Intent(mContext, DisableReceiver.class);
        return PendingIntent.getBroadcast(mContext, id, disableSoundIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }

    public void startAlarms() {
        setupIntents();

        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_mon), false)) {
            addEnableAlarm(Calendar.MONDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_mon)));
            addDisableAlarm(Calendar.MONDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_mon)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_tue), false)) {
            addEnableAlarm(Calendar.TUESDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_tue)));
            addDisableAlarm(Calendar.TUESDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_tue)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_wen), false)) {
            addEnableAlarm(Calendar.WEDNESDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_wen)));
            addDisableAlarm(Calendar.WEDNESDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_wen)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_thu), false)) {
            addEnableAlarm(Calendar.THURSDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_thu)));
            addDisableAlarm(Calendar.THURSDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_thu)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_fri), false)) {
            addEnableAlarm(Calendar.FRIDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_fri)));
            addDisableAlarm(Calendar.FRIDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_fri)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_sat), false)) {
            addEnableAlarm(Calendar.SATURDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_sat)));
            addDisableAlarm(Calendar.SATURDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_sat)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_sun), false)) {
            addEnableAlarm(Calendar.SUNDAY, mEnableSoundIntents.get(mContext.getString(R.string.pref_sun)));
            addDisableAlarm(Calendar.SUNDAY, mDisableSoundIntents.get(mContext.getString(R.string.pref_sun)));
        }
        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_notification), true)) {
            showNotification();
        }
    }

    private void addEnableAlarm(int day, PendingIntent intent) {
        int hour = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_enable_hour), 9);
        int min = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_enable_min), 0);

        Calendar calendar = getCalendar(day, hour, min);

        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_repeat), true)) {
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, intent);
        } else {
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }

    private void addDisableAlarm(int day, PendingIntent intent) {
        int hour = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_disable_hour), 9);
        int min = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_disable_min), 0);

        Calendar calendar = getCalendar(day, hour, min);

        if (mSharedPreferences.getBoolean(mContext.getString(R.string.pref_repeat), true)) {
            mAlarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY * 7, intent);
        } else {
            mAlarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intent);
        }
    }

    private Calendar getCalendar(int day, int hour, int min) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        if (day > calendar.get(Calendar.DAY_OF_WEEK)) {
            calendar.add(Calendar.DAY_OF_YEAR, (day - calendar.get(Calendar.DAY_OF_WEEK)));
        } else if (day < calendar.get(Calendar.DAY_OF_WEEK)) {
            calendar.add(Calendar.DAY_OF_YEAR, (7 - (calendar.get(Calendar.DAY_OF_WEEK) - day)));
        } else {
            if ((hour < calendar.get(Calendar.HOUR_OF_DAY))
                    || ((hour == calendar.get(Calendar.HOUR_OF_DAY))
                    && (min < calendar.get(Calendar.MINUTE)))) {
                calendar.add(Calendar.DAY_OF_YEAR, 7);
            }
        }

        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);

        return calendar;
    }

    public void stopAlarms() {
        setupIntents();
        for (String day : mEnableSoundIntents.keySet()) {
            mAlarmManager.cancel(mEnableSoundIntents.get(day));
        }
        for (String day : mDisableSoundIntents.keySet()) {
            mAlarmManager.cancel(mDisableSoundIntents.get(day));
        }
        mNotificationManager.cancelAll();
    }

    private void showNotification() {
        int icon = R.drawable.ic_notification;
        String title = mContext.getString(R.string.app_name);

        int startTimeHour = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_disable_hour), 12);
        int startTimeMin = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_disable_min), 0);
        int stopTimeHour = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_enable_hour), 12);
        int stopTimeMin = mSharedPreferences.getInt(mContext.getString(R.string.pref_time_enable_min), 0);
        String subText = "Between " +
                MainActivity.getTimeString(startTimeHour, startTimeMin)
                + " - " +
                MainActivity.getTimeString(stopTimeHour, stopTimeMin);


        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mContext)
                        .setSmallIcon(icon)
                        .setContentTitle(title)
                        .setContentText(subText)
                        .setOngoing(true);

        Intent resultIntent = new Intent(mContext, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(MainActivity.class);
        stackBuilder.addNextIntent(resultIntent);

        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }
}

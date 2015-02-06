package com.krumiliev.disableringtone;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction().add(R.id.container, new MainFragment())
                    .commit();
        }
    }

    /**
     * Formats hour and min values
     *
     * @return time String with format hh:mm
     */
    public static String getTimeString(int hourOfDay, int minute) {
        String hour, min;

        if (hourOfDay < 10) {
            hour = String.valueOf(0) + String.valueOf(hourOfDay);
        } else {
            hour = String.valueOf(hourOfDay);
        }

        if (minute < 10) {
            min = String.valueOf(0) + String.valueOf(minute);
        } else {
            min = String.valueOf(minute);
        }

        return hour + ":" + min;
    }
}

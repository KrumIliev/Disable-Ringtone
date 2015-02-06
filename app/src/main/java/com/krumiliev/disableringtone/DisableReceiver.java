package com.krumiliev.disableringtone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.preference.PreferenceManager;

public class DisableReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

        if (PreferenceManager.getDefaultSharedPreferences(context).getBoolean(context.getString(R.string.pref_vibration), true)) {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        } else {
            audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        }
    }
}

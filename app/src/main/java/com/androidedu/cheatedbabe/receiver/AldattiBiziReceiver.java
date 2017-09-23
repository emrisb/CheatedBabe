package com.androidedu.cheatedbabe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 16.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class AldattiBiziReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent ıntent) {

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);

        //titre, uyu, titre, uyu pattern
        long[] pattern = {0, 100, 1000, 300, 200, 100, 500, 200, 100};

        //0 sonsuz -1 sonlu
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(300, 10));
        } else {
            vibrator.vibrate(pattern, -1);
        }

        Toast.makeText(context, "Titredim ve Kendime Geldim. O Kaybetti !!", Toast.LENGTH_SHORT).show();
    }
}

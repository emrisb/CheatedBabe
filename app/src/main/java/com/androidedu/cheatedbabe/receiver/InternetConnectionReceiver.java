package com.androidedu.cheatedbabe.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 16.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class InternetConnectionReceiver extends BroadcastReceiver implements DialogInterface.OnClickListener {

    private Context context = null;

    @Override
    public void onReceive(Context context, Intent arg1) {

        isNetworkAvailable(context);
    }


    private boolean isNetworkAvailable(Context context) {

        this.context = context;

        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivity != null) {

            NetworkInfo activeNetwork = connectivity.getActiveNetworkInfo();
            if (activeNetwork != null) {
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {

                    return true;

                } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {

                    return true;
                }
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Uyarı")
                .setMessage("Tatlım internetler kesik yhaa !!")
                .setPositiveButton("Hmm, ok!", this)
                .setNegativeButton("Snane be, slk .s .s", this)
                .setCancelable(false)
                .show();

        Toast.makeText(context, "Anam, internetler gitti :'(", Toast.LENGTH_SHORT).show();

        return false;
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int whichButton) {

        //-1 positive button | -2 negative button
        if (whichButton == -1) {
            Toast.makeText(context, "Trip atma :(", Toast.LENGTH_SHORT).show();
        } else if (whichButton == -2) {
            Toast.makeText(context, "Terbiyesiz", Toast.LENGTH_SHORT).show();
        }
    }
}

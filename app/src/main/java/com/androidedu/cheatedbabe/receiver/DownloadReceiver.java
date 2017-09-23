package com.androidedu.cheatedbabe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.androidedu.cheatedbabe.service.DownloadDataService;

import static android.app.Activity.RESULT_OK;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 23.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class DownloadReceiver extends BroadcastReceiver {

    private TextView txtDurumu = null;

    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String string = bundle.getString(DownloadDataService.FILEPATH);
            int resultCode = bundle.getInt(DownloadDataService.RESULT);
            if (resultCode == RESULT_OK) {
                Toast.makeText(context, "Download complete. Download URI: " + string,
                        Toast.LENGTH_LONG).show();
                txtDurumu.setText("Download done");
            } else {
                Toast.makeText(context, "Download failed", Toast.LENGTH_LONG).show();
                txtDurumu.setText("Download failed");
            }
        }
    }

    public void setTextView(TextView txtDurumu) {

        this.txtDurumu = txtDurumu;
    }
}

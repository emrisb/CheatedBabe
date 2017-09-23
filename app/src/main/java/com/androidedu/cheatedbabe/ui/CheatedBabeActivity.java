package com.androidedu.cheatedbabe.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.androidedu.cheatedbabe.R;
import com.androidedu.cheatedbabe.receiver.InternetConnectionReceiver;
import com.androidedu.cheatedbabe.service.ChatWithMeService;

public class CheatedBabeActivity extends AppCompatActivity implements View.OnClickListener {

    //Component View
    private TextView txtHello = null;
    private Button btnAlihan = null;

    //receiver
    private InternetConnectionReceiver internetConnectionReceiver = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheated_babe);

        txtHello = (TextView) findViewById(R.id.activity_cheated_babe_txtHello);
        btnAlihan = (Button) findViewById(R.id.activity_cheated_babe_btnAlihan);
        txtHello.setOnClickListener(this);
        btnAlihan.setOnClickListener(this);

        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);

        internetConnectionReceiver = new InternetConnectionReceiver();
        registerReceiver(internetConnectionReceiver, filter);

        //promt izni icin
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {

            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, 140);
        }
    }

    @Override
    protected void onDestroy() {

        unregisterReceiver(internetConnectionReceiver);

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.activity_cheated_babe_btnAlihan:

                startService(new Intent(this, ChatWithMeService.class));
                break;

            case R.id.activity_cheated_babe_txtHello:
                Intent intent = new Intent();
                intent.setAction("com.androidedu.cheatedbabe.aldattibizi");
                sendBroadcast(intent);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        btnAlihan.performClick();
    }
}

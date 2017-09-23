package com.androidedu.cheatedbabe.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidedu.cheatedbabe.R;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 16.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class ChatWithMeService extends Service {

    private WindowManager windowManager;
    private ImageView chatImage;

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Servis Basladi", Toast.LENGTH_SHORT).show();

        //kapatilsa bile tekrar baslar.
//        return Service.START_STICKY;

        //kapatilirsa tekrar baslamaz.
        return Service.START_NOT_STICKY;

        //temelde START_STICKY ile aynı ancak intent'in yeniden gonderilmesini de saglıyor.
        //return Service.START_REDELIVER_INTENT;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatImage = new ImageView(this);
        chatImage.setImageResource(R.drawable.head_alihan);

        final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;
        chatImage.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = params.x;
                        initialY = params.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        params.x = initialX + (int) (event.getRawX() - initialTouchX);
                        params.y = initialY + (int) (event.getRawY() - initialTouchY);
                        windowManager.updateViewLayout(chatImage, params);
                        return true;
                }
                return false;
            }
        });
        windowManager.addView(chatImage, params);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatImage != null) windowManager.removeView(chatImage);

        //servisin içindeysek..
        //stopSelf();
        stopService(new Intent(this, ChatWithMeService.class));
    }
}
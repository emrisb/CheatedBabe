package com.androidedu.cheatedbabe.ui;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.androidedu.cheatedbabe.R;
import com.androidedu.cheatedbabe.receiver.DownloadReceiver;
import com.androidedu.cheatedbabe.service.DownloadDataService;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 23.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class TestServiceActivity extends AppCompatActivity {

    //servisler temelde 3 e ayrılıyor.

    //onyüz servisleri (foreground service) radyo uygulamaları, spotify, messenger
    //arka plan servisleri (background service) - bizim yapacağımız.
    //bağıl servisler (bound service)

    //servisleri temelde 2 farklı noktadan extends edebiliyoruz.

    //Service ve IntentService
    //Service'ler ui olmayan islerde kullanılmalı ve gorev süreleri uzun olmamalidir.
    //Eger uzun surecekse Thread'lerle beraber kullanmamız gerekir.

    //IntentService Main Thread (Ana Akış) ile iletişim kurmadan yapılan uzun islerde daha çok kullanılır.
    //illa iletişim kurulacaksa boradcast receiever kullanılabilir.

    //Service, startService() ile başlatılır.
    //IntentService ise intent ile baslatilir. Ayrı bir thread üzerinde koşmaya baslar.

    //Service'ler arka planda ancak yine de Main Thread (Ana Akış) üzerinde çalışır
    //IntentService'ler ayrı bir worker thread (işçi akış?)

    //Limitations / Drawbacks
    //Service'ler ana akış (Main Thread) dediğimiz yapılar üzerinde çalıştığı için ana akışı blocklayabilir.
    //IntentService'ler ayrı bir akış üzerinde çalıştıkları için işleri hızlandırabilirler. Ama maliyetlidir.

    private DownloadReceiver downloadReceiver = null;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_service);

        initView();
    }

    private void initView() {

        textView = (TextView) findViewById(R.id.activity_test_service_txtDurumu);

        downloadReceiver = new DownloadReceiver();
        downloadReceiver.setTextView(textView);
    }

    //activity xml'inde onClick event'ı olarak tanımlandı.
    public void onClickXML(View view) {

        Intent intent = new Intent(this, DownloadDataService.class);

        intent.putExtra(DownloadDataService.FILENAME, "index.html");
        intent.putExtra(DownloadDataService.URL,
                "https://github.com/google/web-starter-kit/blob/master/app/index.html");
        startService(intent);
        textView.setText(R.string.servis_basladi);
    }

    @Override
    protected void onResume() {

        super.onResume();

        registerReceiver(downloadReceiver, new IntentFilter(DownloadDataService.NOTIFICATION));
    }

    @Override
    protected void onPause() {

        super.onPause();

        unregisterReceiver(downloadReceiver);
    }
}
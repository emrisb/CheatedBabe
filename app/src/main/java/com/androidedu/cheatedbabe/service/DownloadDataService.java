package com.androidedu.cheatedbabe.service;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 23.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class DownloadDataService extends IntentService {

    public static final String URL = "urlpath";
    public static final String FILENAME = "filename";
    public static final String FILEPATH = "filepath";
    public static final String RESULT = "result";
    public static final String NOTIFICATION = "com.androidedu.cheatedbabe.service.DownloadDataService";

    private int result = Activity.RESULT_CANCELED;
    private File output = null;
    private InputStream stream;
    private FileOutputStream fos;

    //intentService bizden bir constructor ve isim bekliyor.
    public DownloadDataService() {
        super("DownloadDataService");
    }

    //async şekilde dosya acıp yazdıracak.
    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            URL url = new URL(openFile(intent));

            stream = url.openConnection().getInputStream();

            InputStreamReader reader = new InputStreamReader(stream);
            fos = new FileOutputStream(output.getPath());

            int next;

            while ((next = reader.read()) != -1) {
                fos.write(next);
            }

            //tüm yazma islemi bittiginde result'a basarili kodu don.
            result = Activity.RESULT_OK;

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            //InputStream ve FileOutputStream'i kapat.
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        publishResults(output.getAbsolutePath(), result);
    }

    private String openFile(Intent intent) {

        String urlPath = intent.getStringExtra(URL);
        String fileName = intent.getStringExtra(FILENAME);

        output = new File(Environment.getExternalStorageDirectory(), fileName);

        if (output.exists()) {
            output.delete();
        }

        return urlPath;
    }

    private void publishResults(String outputPath, int result) {

        //broadcast receiever baslat.
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(FILEPATH, outputPath);
        intent.putExtra(RESULT, result);
        sendBroadcast(intent);
    }
}
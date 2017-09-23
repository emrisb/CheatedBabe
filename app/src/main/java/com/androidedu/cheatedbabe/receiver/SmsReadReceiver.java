package com.androidedu.cheatedbabe.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/******************************
 * Created by Gökhan ÖZTÜRK   |
 * 16.09.2017                 |
 * GokhanOzturk@AndroidEdu.IO |
 *****************************/
public class SmsReadReceiver extends BroadcastReceiver {

    private Bundle bundle;
    private SmsMessage currentSMS;
    private String message;


    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {

            bundle = intent.getExtras();

            if (bundle != null) {

                Object[] pdu_Objects = (Object[]) bundle.get("pdus");

                if (pdu_Objects != null) {

                    for (Object aObject : pdu_Objects) {

                        currentSMS = getIncomingMessage(aObject, bundle);

                        String senderNo = currentSMS.getDisplayOriginatingAddress();

                        message = currentSMS.getDisplayMessageBody();
                        Toast.makeText(context, "Eşin seni aldatıyor !!" + "\n\nGönderen Numarası : "
                                + senderNo + "\n\n Aldatma Kanıtı : " + message, Toast.LENGTH_LONG).show();
                        Log.e("Eşin seni aldatıyor !!", "\n\nGönderen Numarası : "
                                + senderNo + "\n\n Aldatma Kanıtı : " + message);
                    }
                    this.abortBroadcast();
                }
            }
        }
    }


    private SmsMessage getIncomingMessage(Object aObject, Bundle bundle) {

        SmsMessage currentSMS;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            String format = bundle.getString("format");
            currentSMS = SmsMessage.createFromPdu((byte[]) aObject, format);

        } else {

            currentSMS = SmsMessage.createFromPdu((byte[]) aObject);
        }
        return currentSMS;
    }

    //sistem yayinlari...

//    android.app.action.ACTION_PASSWORD_CHANGED
//    android.app.action.ACTION_PASSWORD_EXPIRING
//    android.app.action.ACTION_PASSWORD_FAILED
//    android.app.action.ACTION_PASSWORD_SUCCEEDED
//    android.app.action.DEVICE_ADMIN_DISABLED
//    android.app.action.DEVICE_ADMIN_DISABLE_REQUESTED
//    android.app.action.DEVICE_ADMIN_ENABLED
//    android.app.action.LOCK_TASK_ENTERING
//    android.app.action.LOCK_TASK_EXITING
//    android.app.action.NEKatilmadiXT_ALARM_CLOCK_CHANGED
//    android.app.action.PROFILE_PROVISIONING_COMPLETE
//    android.bluetooth.a2dp.profile.action.CONNECTION_STATE_CHANGED
//    android.bluetooth.a2dp.profile.action.PLAYING_STATE_CHANGED
//    android.bluetooth.adapter.action.CONNECTION_STATE_CHANGED
//    android.bluetooth.adapter.action.DISCOVERY_FINISHED
//    android.bluetooth.adapter.action.DISCOVERY_STARTED
//    android.bluetooth.adapter.action.LOCAL_NAME_CHANGED
//    android.bluetooth.adapter.action.SCAN_MODE_CHANGED
//    android.bluetooth.adapter.action.STATE_CHANGED
//    android.bluetooth.device.action.ACL_CONNECTED
//    android.bluetooth.device.action.ACL_DISCONNECTED
//    android.bluetooth.device.action.ACL_DISCONNECT_REQUESTED
//    android.bluetooth.device.action.BOND_STATE_CHANGED
//    android.bluetooth.device.action.CLASS_CHANGED
//    android.bluetooth.device.action.FOUND
//    android.bluetooth.device.action.NAME_CHANGED
//    android.bluetooth.device.action.PAIRING_REQUEST
//    android.bluetooth.device.action.UUID
//    android.bluetooth.devicepicker.action.DEVICE_SELECTED
//    android.bluetooth.devicepicker.action.LAUNCH
//    android.bluetooth.headset.action.VENDOR_SPECIFIC_HEADSET_EVENT
//    android.bluetooth.headset.profile.action.AUDIO_STATE_CHANGED
//    android.bluetooth.headset.profile.action.CONNECTION_STATE_CHANGED
//    android.bluetooth.input.profile.action.CONNECTION_STATE_CHANGED
//    android.bluetooth.pan.profile.action.CONNECTION_STATE_CHANGED
//    android.hardware.action.NEW_PICTURE
//    android.hardware.action.NEW_VIDEO
//    android.hardware.hdmi.action.OSD_MESSAGE
//    android.hardware.input.action.QUERY_KEYBOARD_LAYOUTS
//    android.intent.action.ACTION_POWER_CONNECTED
//    android.intent.action.ACTION_POWER_DISCONNECTED
//    android.intent.action.ACTION_SHUTDOWN
//    android.intent.action.AIRPLANE_MODE
//    android.intent.action.APPLICATION_RESTRICTIONS_CHANGED
//    android.intent.action.BATTERY_CHANGED
//    android.intent.action.BATTERY_LOW android.intent.action.BATTERY_OKAY
//    android.intent.action.BOOT_COMPLETED
//    android.intent.action.CAMERA_BUTTON
//    android.intent.action.CONFIGURATION_CHANGED
//    android.intent.action.CONTENT_CHANGED
//    android.intent.action.DATA_SMS_RECEIVED
//    android.intent.action.DATE_CHANGED
//    android.intent.action.DEVICE_STORAGE_LOW
//    android.intent.action.DEVICE_STORAGE_OK
//    android.intent.action.DOCK_EVENT
//    android.intent.action.DOWNLOAD_COMPLETE
//    android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED
//    android.intent.action.DREAMING_STARTED
//    android.intent.action.DREAMING_STOPPED
//    android.intent.action.EXTERNAL_APPLICATIONS_AVAILABLE
//    android.intent.action.EXTERNAL_APPLICATIONS_UNAVAILABLE
//    android.intent.action.FETCH_VOICEMAIL
//    android.intent.action.GTALK_CONNECTED
//    android.intent.action.GTALK_DISCONNECTED
//    android.intent.action.HEADSET_PLUG
//    android.intent.action.HEADSET_PLUG
//    android.intent.action.INPUT_METHOD_CHANGED
//    android.intent.action.LOCALE_CHANGED
//    android.intent.action.MANAGE_PACKAGE_STORAGE
//    android.intent.action.MEDIA_BAD_REMOVAL
//    android.intent.action.MEDIA_BUTTON
//    android.intent.action.MEDIA_CHECKING
//    android.intent.action.MEDIA_EJECT
//    android.intent.action.MEDIA_MOUNTED android.intent.action.MEDIA_NOFS
//    android.intent.action.MEDIA_REMOVED
//    android.intent.action.MEDIA_SCANNER_FINISHED
//    android.intent.action.MEDIA_SCANNER_SCAN_FILE
//    android.intent.action.MEDIA_SCANNER_STARTED
//    android.intent.action.MEDIA_SHARED
//    android.intent.action.MEDIA_UNMOUNTABLE
//    android.intent.action.MEDIA_UNMOUNTED
//    android.intent.action.MY_PACKAGE_REPLACED
//    android.intent.action.NEW_OUTGOING_CALL
//    android.intent.action.NEW_VOICEMAIL
//    android.intent.action.PACKAGE_ADDED
//    android.intent.action.PACKAGE_CHANGED
//    android.intent.action.PACKAGE_DATA_CLEARED
//    android.intent.action.PACKAGE_FIRST_LAUNCH
//    android.intent.action.PACKAGE_FULLY_REMOVED
//    android.intent.action.PACKAGE_INSTALL
//    android.intent.action.PACKAGE_NEEDS_VERIFICATION
//    android.intent.action.PACKAGE_REMOVED
//    android.intent.action.PACKAGE_REPLACED
//    android.intent.action.PACKAGE_RESTARTED
//    android.intent.action.PACKAGE_VERIFIED
//    android.intent.action.PHONE_STATE
//    android.intent.action.PROVIDER_CHANGED
//    android.intent.action.PROXY_CHANGE android.intent.action.REBOOT
//    android.intent.action.SCREEN_OFF android.intent.action.SCREEN_ON
//    android.intent.action.TIMEZONE_CHANGED
//    android.intent.action.TIME_SET android.intent.action.TIME_TICK
//    android.intent.action.UID_REMOVED android.intent.action.USER_PRESENT
//    android.intent.action.WALLPAPER_CHANGED
//    android.media.ACTION_SCO_AUDIO_STATE_UPDATED
//    android.media.AUDIO_BECOMING_NOISY android.media.RINGER_MODE_CHANGED
//    android.media.SCO_AUDIO_STATE_CHANGED
//    android.media.VIBRATE_SETTING_CHANGED
//    android.media.action.CLOSE_AUDIO_EFFECT_CONTROL_SESSION
//    android.media.action.HDMI_AUDIO_PLUG
//    android.media.action.OPEN_AUDIO_EFFECT_CONTROL_SESSION
//    android.net.conn.BACKGROUND_DATA_SETTING_CHANGED
//    android.net.conn.CONNECTIVITY_CHANGE android.net.nsd.STATE_CHANGED
//    android.net.scoring.SCORER_CHANGED
//    android.net.scoring.SCORE_NETWORKS
//    android.net.wifi.NETWORK_IDS_CHANGED android.net.wifi.RSSI_CHANGED
//    android.net.wifi.SCAN_RESULTS android.net.wifi.STATE_CHANGE
//    android.net.wifi.WIFI_STATE_CHANGED
//    android.net.wifi.p2p.CONNECTION_STATE_CHANGE
//    android.net.wifi.p2p.DISCOVERY_STATE_CHANGE
//    android.net.wifi.p2p.PEERS_CHANGED
//    android.net.wifi.p2p.STATE_CHANGED
//    android.net.wifi.p2p.THIS_DEVICE_CHANGED
//    android.net.wifi.supplicant.CONNECTION_CHANGE
//    android.net.wifi.supplicant.STATE_CHANGE
//    android.nfc.action.ADAPTER_STATE_CHANGED
//    android.os.action.POWER_SAVE_MODE_CHANGED
//    android.provider.Telephony.SIM_FULL
//    android.provider.Telephony.SMS_CB_RECEIVED
//    android.provider.Telephony.SMS_DELIVER
//    android.provider.Telephony.SMS_EMERGENCY_CB_RECEIVED
//    android.provider.Telephony.SMS_RECEIVED
//    android.provider.Telephony.SMS_REJECTED
//    android.provider.Telephony.SMS_SERVICE_CATEGORY_PROGRAM_DATA_RECEIVED
//    android.provider.Telephony.WAP_PUSH_DELIVER
//    android.provider.Telephony.WAP_PUSH_RECEIVED
//    android.speech.tts.TTS_QUEUE_PROCESSING_COMPLETED
//    android.speech.tts.engine.TTS_DATA_INSTALLED
}
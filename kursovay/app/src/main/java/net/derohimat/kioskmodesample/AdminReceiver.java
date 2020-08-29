package net.derohimat.kioskmodesample;

import android.app.admin.DeviceAdminReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Toast;


/**
 * Created by derohimat on 19/08/2016.
 */
public class AdminReceiver extends DeviceAdminReceiver {

    @Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.device_admin_enabled), Toast.LENGTH_SHORT).show();

//        BroadcastReceiver br = new LocksreenReceiver();
//
//        IntentFilter filter = new IntentFilter("android.intent.action.USER_PRESENT");
//        context.registerReceiver(br, filter);
    }

    @Override
    public CharSequence onDisableRequested(Context context, Intent intent) {
        return context.getString(R.string.device_admin_warning);
    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.device_admin_disabled), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLockTaskModeEntering(Context context, Intent intent, String pkg) {
      Toast.makeText(context, context.getString(R.string.kiosk_mode_enabled), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLockTaskModeExiting(Context context, Intent intent) {
        Toast.makeText(context, context.getString(R.string.kiosk_mode_disabled), Toast.LENGTH_SHORT).show();
    }
}
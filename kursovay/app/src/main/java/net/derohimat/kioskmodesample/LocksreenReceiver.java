package net.derohimat.kioskmodesample;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocksreenReceiver extends BroadcastReceiver {
    public LocksreenReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent intent1 = new Intent(context, SecondActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);


    }
}
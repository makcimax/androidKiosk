package net.derohimat.kioskmodesample;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class LocksreenReceiver  extends BroadcastReceiver {
    public LocksreenReceiver () {
    }
@Override
    public void onReceive(Context context, Intent intent) {

//    Toast.makeText(context,"max",Toast.LENGTH_SHORT);
      //  KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
//        if (keyguardManager.isKeyguardSecure()) {
//
//



    //String action = intent.getAction();
  //  if (Intent.ACTION_SCREEN_ON.equals(action)) {
        //code

        //phone was unlocked, do stuff here
        Intent intent1 = new Intent(context, SecondActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);
    //} else if (Intent.ACTION_SCREEN_OFF.equals(action)) {
        //code
  //  }

  //      }


    }
}
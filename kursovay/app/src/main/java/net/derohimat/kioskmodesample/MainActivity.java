package net.derohimat.kioskmodesample;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by derohimat on 19/08/2016.
 */
public class MainActivity extends BaseActivity  {

    private static final String TAG = "MainActivity";
    protected Context mContext = this;
    private DbHelper db;
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);
        db = new DbHelperSqlLite(this);


      registerReceiver(new BootCompletedReceiver(), new IntentFilter("android.intent.action.BOOT_COMPLETED"));

      //  enableKioskMode(true);
     if (savedInstanceState == null) {
            DevicePolicyManager manager =
                    (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
           if (manager.isDeviceOwnerApp(getApplicationContext().getPackageName())) {
                // This app is set up as the device owner. Show the main features.
               Log.d(TAG, "The app is the device owner.");
                EnterActivity.startThisActivity(mContext);
            } else {
                // This app is not set up as the device owner. Show instructions.
             Log.d(TAG, "The app is not the device owner.");
              showFragment(InstructionFragment.newInstance());
          }
        }

       // setUpAdmin();

   }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }



}

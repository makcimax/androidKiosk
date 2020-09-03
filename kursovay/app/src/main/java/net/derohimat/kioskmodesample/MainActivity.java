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


public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    protected Context mContext = this;
    int numberOfRemainingLoginAttempts = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        registerReceiver(new BootCompletedReceiver(), new IntentFilter("android.intent.action.BOOT_COMPLETED"));

        if (savedInstanceState == null) {
            DevicePolicyManager manager =
                    (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
            if (manager.isDeviceOwnerApp(getApplicationContext().getPackageName())) {

                Log.d(TAG, "The app is the device owner.");
                SettingActivity.startThisActivity(mContext);
            } else {

                Log.d(TAG, "The app is not the device owner.");
                showFragment(InstructionFragment.newInstance());
            }
        }
         setUpAdmin();
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

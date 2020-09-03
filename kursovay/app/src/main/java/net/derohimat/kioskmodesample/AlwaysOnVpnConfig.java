package net.derohimat.kioskmodesample;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class AlwaysOnVpnConfig extends Activity {

    private DevicePolicyManager mDpm;
    private ComponentName mWho;
    private Context mContext;
AlwaysOnVpnConfig(Bundle savedInstanceState)
{ super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    mContext = this;
    mDpm = (DevicePolicyManager) mContext.getSystemService(Context.DEVICE_POLICY_SERVICE);
    mWho = new ComponentName(this, AdminReceiver.class);


}


    private void setAlwaysOnVpnPackageQPlus(String pkg)
            throws PackageManager.NameNotFoundException {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            mDpm.setAlwaysOnVpnPackage(mWho, pkg, true, Collections.singleton(pkg));
        }
    }



}

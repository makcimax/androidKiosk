package net.derohimat.kioskmodesample;

import android.app.Activity;
import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public class AlwaysOnVpnConfig extends BaseActivity {

    AlwaysOnVpnConfig(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    private void setAlwaysOnVpnPackageQPlus(String pkg)
            throws PackageManager.NameNotFoundException {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            getDpm().setAlwaysOnVpnPackage(getAdminName(), pkg, true);
        }
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, AlwaysOnVpnConfig.class);
        context.startActivity(intent);
    }


}

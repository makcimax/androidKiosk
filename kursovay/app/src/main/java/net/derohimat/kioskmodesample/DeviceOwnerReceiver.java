package net.derohimat.kioskmodesample;

import android.app.admin.DeviceAdminReceiver;
import android.app.admin.DevicePolicyManager;
import android.app.admin.NetworkEvent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import java.util.List;

public class DeviceOwnerReceiver extends DeviceAdminReceiver {

    @Override
    public void onProfileProvisioningComplete(Context context, Intent intent) {
        // Enable the profile
        DevicePolicyManager manager =
                (DevicePolicyManager) context.getSystemService(Context.DEVICE_POLICY_SERVICE);
        ComponentName componentName = getComponentName(context);
        manager.setProfileName(componentName, context.getString(R.string.profile_name));
        // Open the main screen
        Intent launch = new Intent(context, MainActivity.class);
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(launch);
    }

    public static ComponentName getComponentName(Context context) {
        return new ComponentName(context.getApplicationContext(), DeviceOwnerReceiver.class);
    }

    @Override
    public void onNetworkLogsAvailable(Context context, Intent intent, long batchToken,
                                       int networkLogsCount) {
        super.onSecurityLogsAvailable(context, intent);
        Intent intentActivity = new Intent(context, NetloggingUtils.class);
        context.startActivity(intentActivity);

        NetloggingUtils netloggingUtils = new NetloggingUtils();

        List<NetworkEvent> res =  netloggingUtils.getNetworkLogging(batchToken);

    }

    @Override
    public void onSecurityLogsAvailable(@NonNull Context context, @NonNull Intent intent) {
        super.onSecurityLogsAvailable(context, intent);
    }
}

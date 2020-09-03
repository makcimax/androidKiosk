package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Build;

public class WifiConfigUtil {
    private static final int INVALID_NETWORK_ID = -1;

    public static boolean saveWifiConfiguration(
            Context context, WifiConfiguration wifiConfiguration) {
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        final int networkId;
        if (wifiConfiguration.networkId == INVALID_NETWORK_ID) {
            networkId = addWifiNetwork(wifiManager, wifiConfiguration);
        } else {
            networkId = updateWifiNetwork(wifiManager, wifiConfiguration);
        }
        if (networkId == INVALID_NETWORK_ID) {
            return false;
        }
        wifiManager.enableNetwork(networkId, false);
        return true;
    }


    private static int addWifiNetwork(
            WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        int networkId = wifiManager.addNetwork(wifiConfiguration);
        if (networkId == INVALID_NETWORK_ID) {
            return INVALID_NETWORK_ID;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            // Saving the configuration is required pre-O.
            if (!saveAddedWifiConfiguration(wifiManager, networkId)) {
                return INVALID_NETWORK_ID;
            }
        }
        return networkId;
    }

    private static boolean saveAddedWifiConfiguration(WifiManager wifiManager, int networkId) {
        boolean saveConfigurationSuccess = wifiManager.saveConfiguration();
        if (!saveConfigurationSuccess) {
            wifiManager.removeNetwork(networkId);
            return false;
        }
        return true;
    }


    private static int updateWifiNetwork(
            WifiManager wifiManager, WifiConfiguration wifiConfiguration) {
        int networkId = wifiManager.updateNetwork(wifiConfiguration);
        if (networkId == INVALID_NETWORK_ID) {
            return INVALID_NETWORK_ID;
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            if (!saveUpdatedWifiConfiguration(wifiManager)) {
                return INVALID_NETWORK_ID;
            }
        }
        return networkId;
    }

    private static boolean saveUpdatedWifiConfiguration(WifiManager wifiManager) {
        return wifiManager.saveConfiguration();
    }


    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, WifiConfigUtil.class);
        context.startActivity(intent);
    }
}

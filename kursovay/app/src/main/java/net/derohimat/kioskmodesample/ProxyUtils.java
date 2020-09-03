package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.net.ProxyInfo;
import android.os.Bundle;
import android.util.Log;

public class ProxyUtils extends BaseActivity {

    private static final String TAG = "Proxy";

    ProxyUtils(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void setProxy(String host, String port) {

        if (host.isEmpty()) {
            Log.w(TAG, "Your host is empty");
            return;
        }

        if (port.isEmpty()) {
            Log.w(TAG, "Your port is empty");
            return;
        }
        final int portInt = Integer.parseInt(port);
        if (portInt > 65535) {
            Log.w(TAG, "Your port is out of range");
            return;
        }
        getDpm().setRecommendedGlobalProxy(getAdminName(),
                ProxyInfo.buildDirectProxy(host, portInt));
    }


    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, ProxyUtils.class);
        context.startActivity(intent);
    }

}

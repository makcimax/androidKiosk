package net.derohimat.kioskmodesample;

import android.app.admin.NetworkEvent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

public class NetloggingUtils extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getDpm().setNetworkLoggingEnabled(getAdminName(), true);
    }

    public List<NetworkEvent> getNetworkLogging(long batchToken) {


        Log.i("NetworkLogging", "onNetworkLogsAvailable(), batchToken: " + batchToken
        );

        List<NetworkEvent> events = null;
        try {
            events = getDpm().retrieveNetworkLogs(getAdminName(), batchToken);
        } catch (SecurityException e) {
            Log.e("NetworkLogging",
                    "Exception while retrieving network logs batch with batchToken: " + batchToken
                    , e);
        }

        if (events == null) {
            Log.e("NetwrokLogging", "Failed to retrieve network logs batch with batchToken: " + batchToken);
            return events;
        }
        return events;

    }
}

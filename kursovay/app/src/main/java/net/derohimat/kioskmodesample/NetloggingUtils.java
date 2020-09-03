package net.derohimat.kioskmodesample;

import android.app.admin.NetworkEvent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetloggingUtils extends BaseActivity {

    public static final String NETWORK_LOGS_FILE_PREFIX = "network_logs_";
    private static final String TAG = "AdminReceiver";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        getDpm().setNetworkLoggingEnabled(getAdminName(), true);
    }

    public List<NetworkEvent> getNetworkLogging(long batchToken) {

        Log.i(TAG, "onNetworkLogsAvailable(), batchToken: " + batchToken
        );

        List<NetworkEvent> events = null;
        try {
            events = getDpm().retrieveNetworkLogs(getAdminName(), batchToken);
        } catch (SecurityException e) {
            Log.e(TAG,
                    "Exception while retrieving network logs batch with batchToken: " + batchToken
                    , e);
        }

        if (events == null) {
            Log.e(TAG, "Failed to retrieve network logs batch with batchToken: " + batchToken);
            return events;
        }
        return events;

    }

    private static class EventSavingTask extends AsyncTask<Void, Void, Void> {

        private Context mContext;
        private long mBatchToken;
        private List<String> mLoggedEvents;

        public EventSavingTask(Context context, long batchToken, ArrayList<String> loggedEvents) {
            mContext = context;
            mBatchToken = batchToken;
            mLoggedEvents = loggedEvents;
        }

        @Override
        protected Void doInBackground(Void... params) {
            Date timestamp = new Date();
            String filename = NETWORK_LOGS_FILE_PREFIX + mBatchToken + "_" + timestamp.getTime()
                    + ".txt";
            File file = new File(mContext.getExternalFilesDir(null), filename);
            try (OutputStream os = new FileOutputStream(file)) {
                for (String event : mLoggedEvents) {
                    os.write((event + "\n").getBytes());
                }
                Log.d(TAG, "Saved network logs to file: " + filename);
            } catch (IOException e) {
                Log.e(TAG, "Failed saving network events to file" + filename, e);
            }
            return null;
        }

    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, NetloggingUtils.class);
        context.startActivity(intent);
    }

}

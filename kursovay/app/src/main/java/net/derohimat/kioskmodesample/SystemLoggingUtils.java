package net.derohimat.kioskmodesample;

import android.app.admin.DevicePolicyManager;
import android.app.admin.SecurityLog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class SystemLoggingUtils extends BaseListFragment {

    private static final String TAG = "SystemLogs";
    private static final String PRE_REBOOT_KEY = "pre-reboot";

    private final ArrayList<String> mLogs = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;


    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mAdminName;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdminName = getAdminName();
        mDevicePolicyManager = getDpm();
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, mLogs);
        setListAdapter(mAdapter);
    }

    private List<SecurityLog.SecurityEvent> getLogs() {
        List<SecurityLog.SecurityEvent>  res = getDpm().retrievePreRebootSecurityLogs(getAdminName());
return res;
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter.add(getString(R.string.security_logs_retrieved_messages, new Date().toString()));
        try {
            processEvents(getLogs());
        } catch (SecurityException e) {
            Log.e(TAG, "Exception thrown when trying to retrieve security logs", e);
            mAdapter.add(getString(R.string.exception_retrieving_security_logs));
        }
    }


    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, SystemLoggingUtils.class);
        context.startActivity(intent);
    }

    private void processEvents(List<SecurityLog.SecurityEvent> logs) {
        if (logs == null) {
            Log.w(TAG, "logs == null, are you polling too early?");
            final String message = getString( R.string.failed_to_retrieve_pre_reboot_security_logs );
            mAdapter.add(message);
        } else {
            SimpleDateFormat formatter = new SimpleDateFormat("MM-dd HH:mm:ss.SSS");
            Log.d(TAG, "Incoming logs size: " + logs.size());
            for (SecurityLog.SecurityEvent event : logs) {
                StringBuilder sb = new StringBuilder();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    sb.append(getEventId(event) + ": ");
                }
                sb.append(getStringEventTagFromId(event.getTag()));
                sb.append(" (").append(formatter.format(new Date(TimeUnit.NANOSECONDS.toMillis(
                        event.getTimeNanos())))).append("): ");
                printData(sb, event.getData());
                mAdapter.add(sb.toString());
            }
            ListView listView = this.getListView();
            listView.setSelection(listView.getCount() - 1);
        }
    }



    private long getEventId(SecurityLog.SecurityEvent event) {
        return event.getId();
    }

    private String getStringEventTagFromId(int eventId) {
        final String eventTag;
        switch (eventId) {
            case SecurityLog.TAG_ADB_SHELL_INTERACTIVE:
                eventTag = "ADB_SHELL_INTERACTIVE";
                break;
            case SecurityLog.TAG_ADB_SHELL_CMD:
                eventTag = "ADB_SHELL_CMD";
                break;
            case SecurityLog.TAG_SYNC_RECV_FILE:
                eventTag = "SYNC_RECV_FILE";
                break;
            case SecurityLog.TAG_SYNC_SEND_FILE:
                eventTag = "SYNC_SEND_FILE";
                break;
            case SecurityLog.TAG_APP_PROCESS_START:
                eventTag = "APP_PROCESS_START";
                break;
            case SecurityLog.TAG_KEYGUARD_DISMISSED:
                eventTag = "KEYGUARD_DISMISSED";
                break;
            case SecurityLog.TAG_KEYGUARD_DISMISS_AUTH_ATTEMPT:
                eventTag = "KEYGUARD_DISMISS_AUTH_ATTEMPT";
                break;
            case SecurityLog.TAG_KEYGUARD_SECURED:
                eventTag = "KEYGUARD_SECURED";
                break;
            case SecurityLog.TAG_OS_STARTUP:
                eventTag = "OS_STARTUP";
                break;
            case SecurityLog.TAG_OS_SHUTDOWN:
                eventTag = "OS_SHUTDOWN";
                break;
            case SecurityLog.TAG_LOGGING_STARTED:
                eventTag = "LOGGING_STARTED";
                break;
            case SecurityLog.TAG_LOGGING_STOPPED:
                eventTag = "LOGGING_STOPPED";
                break;
            case SecurityLog.TAG_MEDIA_MOUNT:
                eventTag = "MEDIA_MOUNT";
                break;
            case SecurityLog.TAG_MEDIA_UNMOUNT:
                eventTag = "MEDIA_UNMOUNT";
                break;
            case SecurityLog.TAG_LOG_BUFFER_SIZE_CRITICAL:
                eventTag = "LOG_BUFFER_SIZE_CRITICAL";
                break;
            case SecurityLog.TAG_PASSWORD_EXPIRATION_SET:
                eventTag = "PASSWORD_EXPIRATION_SET";
                break;
            case SecurityLog.TAG_PASSWORD_COMPLEXITY_SET:
                eventTag = "PASSWORD_COMPLEXITY_SET";
                break;
            case SecurityLog.TAG_PASSWORD_HISTORY_LENGTH_SET:
                eventTag = "PASSWORD_HISTORY_LENGTH_SET";
                break;
            case SecurityLog.TAG_MAX_SCREEN_LOCK_TIMEOUT_SET:
                eventTag = "MAX_SCREEN_LOCK_TIMEOUT_SET";
                break;
            case SecurityLog.TAG_MAX_PASSWORD_ATTEMPTS_SET:
                eventTag = "MAX_PASSWORD_ATTEMPTS_SET";
                break;
            case SecurityLog.TAG_KEYGUARD_DISABLED_FEATURES_SET:
                eventTag = "KEYGUARD_DISABLED_FEATURES_SET";
                break;
            case SecurityLog.TAG_REMOTE_LOCK:
                eventTag = "REMOTE_LOCK";
                break;
            case SecurityLog.TAG_WIPE_FAILURE:
                eventTag = "WIPE_FAILURE";
                break;
            case SecurityLog.TAG_KEY_GENERATED:
                eventTag = "KEY_GENERATED";
                break;
            case SecurityLog.TAG_KEY_IMPORT:
                eventTag = "KEY_IMPORT";
                break;
            case SecurityLog.TAG_KEY_DESTRUCTION:
                eventTag = "KEY_DESTRUCTION";
                break;
            case SecurityLog.TAG_USER_RESTRICTION_ADDED:
                eventTag = "USER_RESTRICTION_ADDED";
                break;
            case SecurityLog.TAG_USER_RESTRICTION_REMOVED:
                eventTag = "USER_RESTRICTION_REMOVED";
                break;
            case SecurityLog.TAG_CERT_AUTHORITY_INSTALLED:
                eventTag = "CERT_AUTHORITY_INSTALLED";
                break;
            case SecurityLog.TAG_CERT_AUTHORITY_REMOVED:
                eventTag = "CERT_AUTHORITY_REMOVED";
                break;
            case SecurityLog.TAG_CRYPTO_SELF_TEST_COMPLETED:
                eventTag = "CRYPTO_SELF_TEST_COMPLETED";
                break;
            case SecurityLog.TAG_KEY_INTEGRITY_VIOLATION:
                eventTag = "KEY_INTEGRITY_VIOLATION";
                break;
            case SecurityLog.TAG_CERT_VALIDATION_FAILURE:
                eventTag = "CERT_VALIDATION_FAILURE";
                break;
            default:
                eventTag = "UNKNOWN(" + eventId + ")";
        }
        return eventTag;
    }

    private void printData(StringBuilder sb, Object data) {
        if (data instanceof Integer || data instanceof Long || data instanceof Float
                || data instanceof String) {
            sb.append(data.toString()).append(" ");
        } else if (data instanceof Object[]) {
            for (Object item : (Object[]) data) {
                printData(sb, item);
            }
        }
    }



}

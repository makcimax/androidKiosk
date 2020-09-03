package net.derohimat.kioskmodesample;

import android.app.admin.SecurityLog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

public class SystemLoggingUtils extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    private List<SecurityLog.SecurityEvent> getLogs() {
        List<SecurityLog.SecurityEvent>  res = getDpm().retrievePreRebootSecurityLogs(getAdminName());
return res;
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, SystemLoggingUtils.class);
        context.startActivity(intent);
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

}

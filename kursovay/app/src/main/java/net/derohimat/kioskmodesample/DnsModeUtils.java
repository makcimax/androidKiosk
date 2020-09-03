package net.derohimat.kioskmodesample;

import android.annotation.TargetApi;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;


public class DnsModeUtils extends BaseActivity {
    DnsModeUtils(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void setPrivateDns(String dns) {
        SetPrivateDnsTask setPrivateDnsTask = new SetPrivateDnsTask(getDpm(), getAdminName());
    }


    @TargetApi(Build.VERSION_CODES.Q)
    final class SetPrivateDnsTask extends AsyncTask<Void, Void, String> {
        public static final String TAG = "Networking";
        private final DevicePolicyManager mDpm;
        private final ComponentName mComponent;

        public SetPrivateDnsTask(
                DevicePolicyManager dpm, ComponentName component) {
            mDpm = dpm;
            mComponent = component;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                final int result;

                result = mDpm.setGlobalPrivateDnsModeOpportunistic(mComponent);
                switch (result) {
                    case DevicePolicyManager.PRIVATE_DNS_SET_NO_ERROR:
                        return null;
                    case DevicePolicyManager.PRIVATE_DNS_SET_ERROR_FAILURE_SETTING:
                        return "General failure to set the Private DNS mode";
                    default:
                        return "Unexpected error setting private dns: " + result;
                }
            } catch (SecurityException | IllegalArgumentException e) {
                Log.w(TAG, "Failed to invoke, cause", e);
                return e.getMessage();
            }
        }
    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, DnsModeUtils.class);
        context.startActivity(intent);
    }

}

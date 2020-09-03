package net.derohimat.kioskmodesample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AppRestriction extends BaseActivity {
    AppRestriction(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public Bundle getAppnRestrictions(String app) {
        Bundle res = getDpm().getApplicationRestrictions(getAdminName(), app);

        return res;

    }

    public void setAppnRestrictions(String app, Bundle settings) {

        getDpm().setApplicationRestrictions(getAdminName(), app, settings);

    }

    public static void startThisActivity(Context context) {
        Intent intent = new Intent(context, AppRestriction.class);
        context.startActivity(intent);
    }

}

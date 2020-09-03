package net.derohimat.kioskmodesample;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

public class DnsModeUtils extends BaseActivity {
    DnsModeUtils(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void setPrivateDns(String dns)
    {
        getDpm().setGlobalPrivateDnsModeSpecifiedHost(getAdminName(),dns);
    }
}

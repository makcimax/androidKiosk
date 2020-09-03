package net.derohimat.kioskmodesample;

import android.net.ProxyInfo;
import android.os.Bundle;

public class ProxyUtils extends BaseActivity {

    ProxyUtils(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void setProxy(String host,int port)
    {

        getDpm().setRecommendedGlobalProxy(getAdminName(),
            ProxyInfo.buildDirectProxy(host, port));

    }



}

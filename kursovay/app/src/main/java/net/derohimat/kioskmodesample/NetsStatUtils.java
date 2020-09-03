package net.derohimat.kioskmodesample;

import android.app.Activity;
import android.app.usage.NetworkStats;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.os.Bundle;

import java.text.SimpleDateFormat;

public class NetsStatUtils extends BaseActivity {
    private NetworkStatsManager mNetstatsManager;
    private PackageManager mPackageManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mPackageManager = getPackageManager();
        mNetstatsManager = (NetworkStatsManager)getSystemService(
                Context.NETWORK_STATS_SERVICE);
    }

    private void getDeviceStat()
    {
        NetworkStats result = null;
        NetworkStats.Bucket bucket = null;
/*        bucket = mNetstatsManager.querySummaryForDevice(
                ConnectivityManager.TYPE_WIFI, "", ,
               );*/
    }

    private void getAppStat()
    {

        NetworkStats result = null;
        NetworkStats.Bucket bucket = null;

     /*   result = mNetstatsManager.querySummary(
                ConnectivityManager.TYPE_WIFI, "", ,
                );*/

    }

}

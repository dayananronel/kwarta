package com.kwarta.ph.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

public class Validator extends AppCompatActivity {

    public static int getConnectivityStatus(Context context) {

        int NETWORK = 0;

        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Log.d("activeNetwork", "wifi");
                return 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Log.d("activeNetwork", "mobile");
                return 2;
            } else {
                Log.d("activeNetwork", "no active");
                return 0;
            }
        } else {
            Log.d("activeNetwork", "bull");
            return 0;
        }
    }

}

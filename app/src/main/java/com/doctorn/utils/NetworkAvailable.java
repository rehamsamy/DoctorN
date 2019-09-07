package com.doctorn.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkAvailable {
    Context context;

    public NetworkAvailable(Context context) {
        this.context = context;
    }

    public  Boolean isNetworkAvailable(){
      ConnectivityManager  manager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
     NetworkInfo networkInfo= manager.getActiveNetworkInfo();

     boolean state=networkInfo!=null&&networkInfo.isConnected();

        return state;
    }
}

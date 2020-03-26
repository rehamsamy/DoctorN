package com.doctorn.utils;

import android.app.ProgressDialog;
import android.content.Context;

public class DailogUtil {
    public DailogUtil() {
    }

    public  static ProgressDialog showProgress(Context context,String msg,Boolean isCancell){
        ProgressDialog progressDialog=new ProgressDialog(context);
        progressDialog.setMessage(msg);
        progressDialog.setCancelable(isCancell);
        progressDialog.show();
        return  progressDialog;

    }
}

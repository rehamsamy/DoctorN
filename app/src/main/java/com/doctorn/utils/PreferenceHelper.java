package com.doctorn.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {
    public static final String lang_selected="language";
    private static final String ARB_LANGUAGE="lang";

    public static SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(ARB_LANGUAGE,Context.MODE_PRIVATE);
    }

    public static String getValue(Context context){
        SharedPreferences preferences=getSharedPreferences(context);
        return preferences.getString(lang_selected,"en");
    }

    public static void setNewValue(Context context,String newValue){
       SharedPreferences.Editor editor=getSharedPreferences(context).edit();
       editor.putString(lang_selected,newValue);
       editor.commit();
    }
}

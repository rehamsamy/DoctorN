package com.doctorn.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHelper {

    private static final String APP_Language = "APP_Language";


    // properties
//    private static final String SOME_STRING_VALUE = "SOME_STRING_VALUE";
    public static final String select_lang = "Language";
    // other properties...

    private PreferenceHelper() {
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_Language, Context.MODE_PRIVATE);
    }

    public static String getValue(Context context) {
        return getSharedPreferences(context).getString(select_lang, "en");
    }

    public static void setNewValue(Context context, String newValue) {
        final SharedPreferences.Editor editor = getSharedPreferences(context).edit();
        editor.putString(select_lang, newValue);
        editor.commit();
    }

}



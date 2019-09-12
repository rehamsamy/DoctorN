package com.doctorn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.doctorn.privacyAndPolicy.PrivacyPolicyActivity;
import com.doctorn.utils.PreferenceHelper;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {


    private static final String TAG =SettingActivity.class.getSimpleName() ;
    private static String lang_selected="en";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.suggestions_complaints_id)
    void sendSuggestion(){
        startActivity(new Intent(SettingActivity.this,SuggestionComplaintsActivity.class));
    }

    @OnClick(R.id.privacy_policy_id)
    void privacyPolicyClick(){
        startActivity(new Intent(SettingActivity.this,PrivacyPolicyActivity.class));
    }

    @OnClick(R.id.add_balance_id)
    void addBalanceClick(){
        startActivity(new Intent(SettingActivity.this,RegisterCreditCardActivity.class));

    }

    @OnClick(R.id.language_id)
    void ChangeLanguageClick(){
        changeLanguage();
    }

    private void changeLanguage() {
        String [] lang={getString(R.string.english),getString(R.string.arabic)};
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("choose language ?!!");
        dialog.setItems(lang, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    // English Item is Slected
                    lang_selected = "en";
                    //language_val_txtV.setText(lang_selected);
                    PreferenceHelper.setNewValue(SettingActivity.this, lang_selected);
                    setConfig(lang_selected, SettingActivity.this);
                    Log.v(TAG,"lang_selceted"+lang_selected);

                } else if (which == 1) {
                    // Arabic Item is Selected vvffrer
                    lang_selected = "ar";
                    PreferenceHelper.setNewValue(SettingActivity.this, lang_selected);
                    setConfig(lang_selected, SettingActivity.this);
                    Log.v(TAG,"lang_selceted"+lang_selected);
                }

                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
        dialog.show();
    }

    private void setConfig(String lang, Context context) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
           }

}

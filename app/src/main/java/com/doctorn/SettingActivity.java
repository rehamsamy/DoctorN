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

import com.doctorn.privacyAndPolicy.PrivacyPolicyActivity;
import com.doctorn.utils.PreferenceHelper;

import java.util.Locale;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends AppCompatActivity {


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
        String [] lang={getString(R.string.arabic),getString(R.string.english)};
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("choose language ?!!");
        dialog.setItems(lang, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case 0:
                        lang_selected="ar";
                        PreferenceHelper.setNewValue(getApplicationContext(),lang_selected);
                        setConfig(lang_selected,SettingActivity.this);
                    case 1:
                        lang_selected="en";
                        PreferenceHelper.setNewValue(getApplicationContext(),lang_selected);
                        setConfig(lang_selected,SettingActivity.this);
                }

                Intent intent=getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    private void setConfig(String lang_selected, Context context) {
        Locale locale=new Locale(lang_selected);
        locale.setDefault(locale);
        Configuration config=new Configuration();
        config.locale=locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }

}

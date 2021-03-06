package com.doctorn;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.myFinancail.AllFinancialActivity;
import com.doctorn.privacyAndPolicy.PrivacyPolicyActivity;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {


    private static final String TAG =SettingActivity.class.getSimpleName() ;
    private static String lang_selected="en";
    static RetrofitInterface retrofitInterface;
    @BindView(R.id.add_balance_id) TextView balancetxt;
    Intent intent;
    SharedPreferences preferences,preferences1;
    SharedPreferences.Editor editor,editor1;
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        preferences=getSharedPreferences("user_data",MODE_PRIVATE);
        editor=preferences.edit();
        preferences1=getSharedPreferences("doctor_data",MODE_PRIVATE);
        editor1=preferences1.edit();
        intent=getIntent();
        if(DoctorListActivity.user!=null){
             balancetxt.setVisibility(View.GONE);
             flag=1;
        }else if(DoctorAccountActivity.user !=null){
            balancetxt.setVisibility(View.VISIBLE);
            flag=2;
        }
    }

    @OnClick(R.id.suggestions_complaints_id)
    void sendSuggestion(){
        startActivity(new Intent(SettingActivity.this,SuggestionComplaintsActivity.class));
    }

    @OnClick(R.id.terms_conditions_id)
    void termsClick(){
        startActivity(new Intent(SettingActivity.this,TermsAndConditionsActivity.class));
    }

    @OnClick(R.id.privacy_policy_id)
    void privacyPolicyClick(){
        startActivity(new Intent(SettingActivity.this,PrivacyPolicyActivity.class));
    }

    @OnClick(R.id.add_balance_id)
    void addBalanceClick(){
        Intent intent1=new Intent(SettingActivity.this,BalanceActivity.class);
        if(intent.getAction().equals(EditProfileActivity.update_user)){
           // intent1.setAction(EditProfileActivity.update_user);
            //startActivity(intent1);

        }else if(intent.getAction().equals(EditProfileActivity.update_doctor)){
           // intent1.setAction(EditProfileActivity.update_doctor);
            startActivity(intent1);
        }

    }


    @OnClick(R.id.log_out_id)
    void logoutClick(){
        logout();
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



    public void logout() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Call<UserModel> call=retrofitInterface.logout();
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(SettingActivity.this, response.body().getMessages().toString(), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SettingActivity.this, LoginActivity.class));
                  //  if(flag==1){
                      //preferences=getSharedPreferences("user_data",MODE_PRIVATE);
                    //  editor=preferences.edit();
                   // }else if(flag==2){
                      //  preferences=getSharedPreferences("doctor_data",MODE_PRIVATE);
                      //  editor=preferences.edit();
                   // }
                    editor.clear().commit();
                    editor1.clear().commit();

                }else {
                    Toast.makeText(SettingActivity.this, response.body().getMessages().toString(), Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


    }

    @OnClick(R.id.all_financail_id)
    void onFinancailClick(){
        startActivity(new Intent(getApplicationContext(), AllFinancialActivity.class));
    }


    @OnClick(R.id.about_app_id)
   void aboutAppClick(){
       startActivity(new Intent(getApplicationContext(),AboutAppActivity.class));
    }



}

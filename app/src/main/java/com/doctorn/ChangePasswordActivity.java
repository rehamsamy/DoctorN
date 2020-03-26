package com.doctorn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private static final String TAG =ChangePasswordActivity.class.getSimpleName();
    @BindView(R.id.old_password_input) EditText oldPasswordInput;
    @BindView(R.id.new_password_input) EditText newPasswordInput;
    @BindView(R.id.confirm_new_password_input) EditText confirmNewPasswordInput;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    RetrofitInterface retrofitInterface;
    DailogUtil dailogUtil;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        intent=getIntent();
        dailogUtil=new DailogUtil();
    }

    @OnClick(R.id.change_password_btn)
    void changePasswordClick(){
        if(!FUtilsValidation.isEmpty(oldPasswordInput,getResources().getString(R.string.edit_text_empty))
                &&!FUtilsValidation.isEmpty(newPasswordInput,getResources().getString(R.string.edit_text_empty))
               && !FUtilsValidation.isEmpty(confirmNewPasswordInput,getResources().getString(R.string.edit_text_empty))
                && FUtilsValidation.isPasswordEqual(newPasswordInput,confirmNewPasswordInput,"password not equal")){

            if(intent.getAction().equals(EditProfileActivity.update_user)){
                Log.v(TAG,"change userrrrr");
                changePassword();
            }else if(intent.getAction().equals(EditProfileActivity.update_doctor)){
                Log.v(TAG,"change doctorrrrr");
                changeDoctorPassword();
            }


        }

    }



    private void changePassword() {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        final ProgressDialog progressDialog=dailogUtil.showProgress(ChangePasswordActivity.this,getString(R.string.wait_loading),false);
        Call<UserModel> call = retrofitInterface.changePassword(DoctorListActivity.user.getUser().getId()
                , oldPasswordInput.getText().toString()
                , newPasswordInput.getText().toString()
                , confirmNewPasswordInput.getText().toString()
                , DoctorListActivity.user.getToken());
         call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.v(TAG,"rrrrr"+response.body().toString());
                progressBar.setVisibility(View.GONE);
                progressDialog.dismiss();
                finish();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    progressDialog.dismiss();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.v(TAG,"rrrrr"+t.getMessage().toString());
                progressDialog.dismiss();
            }
        });


    }



    private void changeDoctorPassword() {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        final ProgressDialog progressDialog=dailogUtil.showProgress(ChangePasswordActivity.this,getString(R.string.wait_loading),false);
        Call<UserModel> call = retrofitInterface.changeDoctorPassword(SplashActivity.model.getUser().getId()
                , oldPasswordInput.getText().toString()
                , newPasswordInput.getText().toString()
                , confirmNewPasswordInput.getText().toString()
                , SplashActivity.model.getToken());
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Log.v(TAG,"rrrrr"+response.body().toString());
                    progressBar.setVisibility(View.GONE);
                    finish();
                    progressDialog.dismiss();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.v(TAG,"rrrrr"+t.getMessage().toString());
                progressDialog.dismiss();
            }
        });


    }


}

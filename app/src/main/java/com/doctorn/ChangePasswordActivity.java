package com.doctorn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
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
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        intent=getIntent();
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
        Call<UserModel> call = retrofitInterface.changePassword(LoginActivity.user.getId()
                , oldPasswordInput.getText().toString()
                , newPasswordInput.getText().toString()
                , confirmNewPasswordInput.getText().toString()
                , LoginActivity.userModel.getToken());
         call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                Log.v(TAG,"rrrrr"+response.body().toString());
                progressBar.setVisibility(View.GONE);
                finish();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.v(TAG,"rrrrr"+t.getMessage().toString());
            }
        });


    }



    private void changeDoctorPassword() {
        retrofitInterface = RetrofitClientInstance.getRetrofit();
        progressBar.setVisibility(View.VISIBLE);
        Call<UserModel> call = retrofitInterface.changeDoctorPassword(LoginAsDoctorActivity.user.getId()
                , oldPasswordInput.getText().toString()
                , newPasswordInput.getText().toString()
                , confirmNewPasswordInput.getText().toString()
                , LoginAsDoctorActivity.userModel.getToken());
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Log.v(TAG,"rrrrr"+response.body().toString());
                    progressBar.setVisibility(View.GONE);
                    finish();
                }
                else {
                    Toast.makeText(ChangePasswordActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                Log.v(TAG,"rrrrr"+t.getMessage().toString());
            }
        });


    }


}

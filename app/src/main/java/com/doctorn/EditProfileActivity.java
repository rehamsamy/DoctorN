package com.doctorn;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.PreferenceHelper;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {

    private static final String TAG =EditProfileActivity.class.getSimpleName() ;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.user_name_input) EditText nameInput;
    @BindView(R.id.user_phone_input) EditText phoneInput;
    @BindView(R.id.user_email_input) EditText emailInput;
    @BindView(R.id.user_age_input) EditText ageInput;
    @BindView(R.id.gender_spinner_id) Spinner spinner;
    @BindView(R.id.user_type_input) TextView typeInput;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    String [] gender={"male","female"};
    ArrayAdapter adapter;
    public static final String update_user="user";
    public static final String update_doctor="doctor";
    RetrofitInterface retrofitInterface;
    UserModel user;
    String gender_value;
    DailogUtil dailogUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,gender);
        spinner.setAdapter(adapter);
        initilaizeView();
        dailogUtil=new DailogUtil();

    }

    @OnClick(R.id.edit_profile_btn)
    void EditProfile(){
        if (!FUtilsValidation.isEmpty(nameInput, getResources().getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(phoneInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(emailInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(ageInput, getString(R.string.edit_text_empty))
                && gender_value !=null) {
            if(getIntent().getAction().equals(update_user)){
            editProfile();
        }else if(getIntent().getAction().equals(update_doctor)){
               editDoctorProfile();
            }

        }else if(gender_value==null){
            Toast.makeText(this, getString(R.string.edit_text_empty), Toast.LENGTH_LONG).show();
        }else if(Integer.valueOf(ageInput.getText().toString())<0||Integer.valueOf(ageInput.getText().toString())>100){
            Toast.makeText(this, getString(R.string.integer_value), Toast.LENGTH_LONG).show();
        }


    }

    private void initilaizeView() {
        Intent intent=getIntent();
        if(intent.getAction().equals(update_doctor)){
            user=DoctorAccountActivity.user;
            nameInput.setText(user.getUser().getName());
            phoneInput.setText(user.getUser().getUserPhone());
            emailInput.setText(user.getUser().getEmail());
            ageInput.setText(String.valueOf(user.getUser().getUserAge()));
            typeInput.setText(user.getUser().getUserGender());
        }else if(intent.getAction().equals(update_user)) {
            user = DoctorListActivity.user;
            nameInput.setText(user.getUser().getName());
            phoneInput.setText(user.getUser().getUserPhone());
            emailInput.setText(user.getUser().getEmail());
            ageInput.setText(String.valueOf(user.getUser().getUserAge()));
            typeInput.setText(user.getUser().getUserGender());
        }
    }

    private void editProfile() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
      Call<UserModel> call=retrofitInterface.updateUserProfile(Integer.parseInt(ageInput.getText().toString()),
             "user",emailInput.getText().toString(),
              nameInput.getText().toString(), DoctorListActivity.user.getToken(),gender_value,
              PreferenceHelper.getValue(getApplicationContext()),phoneInput.getText().toString());

      call.enqueue(new Callback<UserModel>() {
          @Override
          public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
              progressBar.setVisibility(View.VISIBLE);

              if(response.body().isStatus()){
                  progressBar.setVisibility(View.VISIBLE);
                  Log.v(TAG,"error"+response.body().getMessage().toString());
                  UserModel user=response.body();
                  Toast.makeText(EditProfileActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(EditProfileActivity.this, UserAccountActivity.class);
                  intent.putExtra("update_data",user);
                  startActivity(intent);
              }
              else {
                  progressBar.setVisibility(View.VISIBLE);
                  Log.v(TAG,"error"+response.body().getMessage().toString());
                  Toast.makeText(EditProfileActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();

              }
          }

          @Override
          public void onFailure(Call<UserModel> call, Throwable t) {
              progressBar.setVisibility(View.VISIBLE);
              Log.v(TAG,"error"+t.getMessage().toString());
              Toast.makeText(EditProfileActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();


          }
      });

    }


    private void editDoctorProfile() {
        final ProgressDialog progressDialog=dailogUtil.showProgress(EditProfileActivity.this,getString(R.string.wait_loading),false);
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Log.v(TAG,"email input"+emailInput.getText().toString());
        Call<UserModel> call=retrofitInterface.updateDoctorProfile(Integer.parseInt(ageInput.getText().toString()),
               DoctorAccountActivity.user.getUser().getUserType(),"ali@gmail.com",
                nameInput.getText().toString(), DoctorAccountActivity.user.getToken(),typeInput.getText().toString(),
                DoctorAccountActivity.user.getUser().getId(),phoneInput.getText().toString());

        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                progressBar.setVisibility(View.GONE);

                if(response.body().isStatus()){
                    progressBar.setVisibility(View.VISIBLE);
                    Log.v(TAG,"error"+response.body().getMessage().toString());
                    UserModel user=response.body();
                    Toast.makeText(EditProfileActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(EditProfileActivity.this, DoctorAccountActivity.class);
                    intent.putExtra("update_data",user);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                    Log.v(TAG,"error"+response.body().getMessage().toString());
                    Toast.makeText(EditProfileActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                     progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);
                Log.v(TAG,"error"+t.getMessage().toString());
                Toast.makeText(EditProfileActivity.this, t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });


    }


    @OnItemSelected(R.id.gender_spinner_id)
    void itemSelected(int index){
        gender_value=(String) adapter.getItem(index);
        typeInput.setText(gender_value);
        Log.v(TAG,"gender"+adapter.getItem(index));
    }


}

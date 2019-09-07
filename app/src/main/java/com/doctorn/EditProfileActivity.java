package com.doctorn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.doctorn.models.User;
import com.doctorn.models.UserModel;
import com.doctorn.myAccount.MyAccountActivity;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    @BindView(R.id.user_type_input) EditText typeInput;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    RetrofitInterface retrofitInterface;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        initilaizeView();


    }


    @OnClick(R.id.edit_profile_btn)
    void EditProfile(){
        if (!FUtilsValidation.isEmpty(nameInput, getResources().getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(phoneInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(emailInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(ageInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(typeInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(typeInput, getString(R.string.edit_text_empty))) {
            editProfile();
        }


    }
    private void initilaizeView() {
        user=LoginActivity.user;
        nameInput.setText(user.getName());
        phoneInput.setText(user.getUserPhone());
        emailInput.setText(user.getEmail());
        ageInput.setText(String.valueOf(user.getUserAge()));
        typeInput.setText(user.getUserGender());
    }

    private void editProfile() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
      Call<UserModel> call=retrofitInterface.updateProfile(Integer.parseInt(ageInput.getText().toString()),
              LoginActivity.user.getUserType(),"adel@gmail.com",
              nameInput.getText().toString(),LoginActivity.userModel.getToken(),typeInput.getText().toString(),
              LoginActivity.user.getId(),phoneInput.getText().toString());

      call.enqueue(new Callback<UserModel>() {
          @Override
          public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
              progressBar.setVisibility(View.VISIBLE);

              if(response.body().isStatus()){
                  progressBar.setVisibility(View.VISIBLE);
                  Log.v(TAG,"error"+response.body().getMessage().toString());
                  User user=response.body().getUser();
                  Toast.makeText(EditProfileActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                  Intent intent=new Intent(EditProfileActivity.this, MyAccountActivity.class);
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

}

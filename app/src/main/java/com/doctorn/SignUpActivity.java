package com.doctorn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemSelected;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private static final String TAG =SignUpActivity.class.getSimpleName() ;
    Animation up,down;
    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.user_name_input) EditText nameInput;
    @BindView(R.id.user_phone_input) EditText phoneInput;
    @BindView(R.id.user_email_input) EditText emailInput;
    @BindView(R.id.user_age_input) EditText ageInput;
    @BindView(R.id.user_type_input) TextView typeInput;
    @BindView(R.id.user_password_input) EditText passwordInput;
    @BindView(R.id.user_confirm_password_input) EditText confirmPasswordInput;
    @BindView(R.id.gender_spinner_id) Spinner spinner;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    @BindView(R.id.hello_msg) TextView helloMsg;
    NetworkAvailable available;
    String [] gender={"male","female"};
    ArrayAdapter adapter;
    RetrofitInterface retrofitInterface;
    String gender_value;
    String registerPersonType;
   public static UserModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);

        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
        available=new NetworkAvailable(this);


        adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,gender);
        //spinner.setDrop
        spinner.setAdapter(adapter);


        Intent intent=getIntent();
        if(intent.getAction().equals("register_user")){
            registerPersonType="user";
            Log.v(TAG,"type"+registerPersonType);
            helloMsg.setText(getString(R.string.login_as_user));
        }
        else if(intent.getAction().equals("register_doctor")){
            registerPersonType="doctor";
            Log.v(TAG,"type"+registerPersonType);
        }

    }

    @OnItemSelected(R.id.gender_spinner_id)
    void itemSelected(int index){
  gender_value=(String) adapter.getItem(index);
  typeInput.setText(gender_value);
        Log.v(TAG,"gender"+adapter.getItem(index));
    }


    @OnClick(R.id.view_password_id)
    void  viewPasswordClick(){
        passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

    }

    @OnClick(R.id.have_account_id)
    void loginClick(){
        startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
    }

    @OnClick(R.id.continu_btn)
    void continueRegister(){


        if (!FUtilsValidation.isEmpty(nameInput, getResources().getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(passwordInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(phoneInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(emailInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(ageInput, getString(R.string.edit_text_empty))
                && !FUtilsValidation.isEmpty(confirmPasswordInput, getString(R.string.edit_text_empty))
                && FUtilsValidation.isPasswordEqual(passwordInput,confirmPasswordInput,"password not equal??")) {
            if(available.isNetworkAvailable()){
                registerUser();
            }else {
                Toast.makeText(this, getString(R.string.internet_offline), Toast.LENGTH_SHORT).show();
            }

        }


    }


    private void registerUser() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        final Map<String,Object> map=new HashMap<>();
        map.put("name",nameInput.getText().toString());
        map.put("user_phone",phoneInput.getText().toString());
        map.put("email",emailInput.getText().toString());
        map.put("user_age",ageInput.getText().toString());
        map.put("user_gender",typeInput.getText().toString());
        map.put("password",passwordInput.getText().toString());
        map.put("user_type",registerPersonType);
        map.put("password_confirmation",confirmPasswordInput.getText().toString());

        Log.v(TAG,"ggg"+typeInput.getText().toString());
        progressBar.setVisibility(View.VISIBLE);
        Call<UserModel> call=retrofitInterface.registerUser(map);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                   if(registerPersonType.equals("user")){
                       model=response.body();
                       Intent intent=new Intent(SignUpActivity.this, DoctorListActivity.class);
                       startActivity(intent);

                   }else if(registerPersonType.equals("doctor")){
                       model=response.body();
                       startActivity(new Intent(SignUpActivity.this, ContinuRegisterActivity.class));
                   }

                }
                else {

                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(SignUpActivity.this, response.body().getErrorModel().toString(), Toast.LENGTH_SHORT).show();

                    //Toast.makeText(SignUpActivity.this, response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserModel> call, Throwable t) {

            }
        });


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        gender_value= (String) parent.getItemAtPosition(position);
        Log.v(TAG,"genderrr"+gender_value);
    }
}

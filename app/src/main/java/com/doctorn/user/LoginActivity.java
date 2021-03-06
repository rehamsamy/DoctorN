package com.doctorn.user;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doctorn.ForgetPasswordActivity;
import com.doctorn.LoginAsDoctorActivity;
import com.doctorn.R;
import com.doctorn.SignUpActivity;
import com.doctorn.SplashActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.User;
import com.doctorn.models.UserModel;
import com.doctorn.utils.DailogUtil;
import com.doctorn.utils.NetworkAvailable;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;
import com.fourhcode.forhutils.FUtilsValidation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.user_email_input) EditText emailInput;
    @BindView(R.id.user_password_input) EditText passwordInput;
    @BindView(R.id.progress_id) ProgressBar progressBar;
    @BindView(R.id.login_asDoctor_id) TextView loginDoctor;
    @BindView(R.id.view_password_id) ImageView viewPassword;
    public  static User user;
    public static  UserModel userModel;
    NetworkAvailable available;
    Animation up,down;
    DailogUtil dailogUtil;
    FirebaseAuth firebaseAuth;
    DatabaseReference reference;

    RetrofitInterface retrofitInterface;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);
        dailogUtil=new DailogUtil();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        available=new NetworkAvailable(this);


        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
        preferences=getSharedPreferences("user_data",MODE_PRIVATE);
       passwordInput.setText(preferences.getString("password",null));
        emailInput.setText(preferences.getString("email",null));


    }

    @OnClick(R.id.signup_btn)
    void signUp(){
        Intent intent=new Intent(LoginActivity.this,SignUpActivity.class);
        intent.setAction("register_user");
        startActivity(intent);
    }


    @OnClick(R.id.forget_password_id)
    void forgetPassword(){
        startActivity(new Intent(LoginActivity.this,ForgetPasswordActivity.class));
    }

    @OnClick(R.id.login_asDoctor_id)
    void loginDoctor(){
        Intent intent=new Intent(LoginActivity.this,LoginAsDoctorActivity.class);
        intent.putExtra("name","");
        startActivity(intent);

    }

    @OnClick(R.id.view_password_id)
    void  viewPasswordClick(){
        passwordInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

    }

    @OnClick(R.id.signin_btn)
    void loginClick(){
        if(!FUtilsValidation.isEmpty(passwordInput,getResources().getString(R.string.password_empty))&&
                !FUtilsValidation.isEmpty(emailInput,getResources().getString(R.string.email_empty))){

            if(available.isNetworkAvailable()){
                loginUser();
            }else {
                Toast.makeText(this, "make sure internet is connected", Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void loginUser() {
        Map<String,Object> map= new HashMap<>();
        map.put("email",emailInput.getText().toString());
        map.put("password",passwordInput.getText().toString());
         retrofitInterface= RetrofitClientInstance.getRetrofit();
        final ProgressDialog progressDialog= dailogUtil.showProgress(LoginActivity.this,getString(R.string.wait_loading),false);
         progressBar.setVisibility(View.GONE);
         Call<UserModel> call=retrofitInterface.loginUser(map);
         call.enqueue(new Callback<UserModel>() {
             @Override
             public void onResponse(Call<UserModel> call, Response<UserModel> response) {

                 if(response.body().isStatus()){
                     userModel=response.body();
                     user=response.body().getUser();
                     if(user.getUserType().equals("user")){
                         loginToFirebase(progressDialog);
                         userModel.setToken(userModel.getToken());
                         userModel.setUser(userModel.getUser());
                         Gson gson=new Gson();
                         String doctor_data=gson.toJson(userModel);
                         editor=getSharedPreferences("user_data",MODE_PRIVATE).edit();
                         editor.putString("user_model",doctor_data);
                         Log.v("TAG","user_model"+gson.toString());
                         editor.commit();
                     }else{
                         Toast.makeText(LoginActivity.this, getString(R.string.you_register_as_user), Toast.LENGTH_LONG).show();
                         progressDialog.dismiss();
                     }



                     //Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                 }else {
                     Toast.makeText(LoginActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                     progressDialog.dismiss();
                 }


             }

             @Override
             public void onFailure(Call<UserModel> call, Throwable t) {
                progressDialog.dismiss();
             }
         });

    }

    private void loginToFirebase(final ProgressDialog progressDialog) {
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(emailInput.getText().toString(),passwordInput.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                           reference= FirebaseDatabase.getInstance().getReference();
                           reference=reference.child("users").child(firebaseAuth.getCurrentUser().getUid());
                           Map<String,Object> map=new HashMap<>();
                            map.put("name",user.getName());
                            map.put("id",firebaseAuth.getCurrentUser().getUid());
                            map.put("type","user");
                            map.put("key","user"+String.valueOf(user.getId()));
                            reference.setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                   if(task.isSuccessful()){
                                       Log.v("TAG","sucesssss");
                                       Intent intent=new Intent(LoginActivity.this,DoctorListActivity.class);
                                       intent.putExtra("user_data",userModel);
                                       startActivity(intent);
                                       progressDialog.dismiss();
                                   }
                                }
                            });
                        }
                    }
                });

    }


    @Override
    protected void onPause() {
        super.onPause();
        editor=preferences.edit();
        editor.putString("password",passwordInput.getText().toString());
        editor.putString("email",emailInput.getText().toString());
        editor.apply();
        editor.commit();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(getApplicationContext(), SplashActivity.class));
    }

}

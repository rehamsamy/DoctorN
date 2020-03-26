package com.doctorn;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.doctorList.DoctorListActivity;
import com.doctorn.models.UserModel;
import com.doctorn.user.LoginActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;

public class SplashActivity extends AppCompatActivity   {

    Animation up;
    Handler handler;
    @BindView(R.id.logo_img)
    ImageView imageView;
   public static UserModel model,model1;
    SharedPreferences preferences,preferences1;
    String doctor_data,user_data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        handler = new Handler();
        up = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        imageView.setAnimation(up);


        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                preferences=getSharedPreferences("doctor_data",MODE_PRIVATE);
                doctor_data=preferences.getString("doctor_model","");
                Gson gson=new Gson();
                model=gson.fromJson(doctor_data,UserModel.class);


                 preferences1=getSharedPreferences("user_data",MODE_PRIVATE);
                 user_data=preferences1.getString("user_model","");
                Gson gson1=new Gson();
                model1=gson1.fromJson(user_data,UserModel.class);

                if(doctor_data !=null && ! doctor_data .equals("")){
                    Intent intent=new Intent(SplashActivity.this, DoctorAccountActivity.class);
                    intent.putExtra("doctor_data",model);
                    Log.v("TAG","xxxxxxx"+model.getToken());
                   // intent.putExtra("token",model.getToken());
                   // intent.putExtra("doctor_id",model.getUser().getId());
                    startActivity(intent);
                }else if(user_data !=null && ! user_data .equals("")){
                    Intent intent=new Intent(SplashActivity.this, DoctorListActivity.class);
                    intent.putExtra("user_data",model1);
                    startActivity(intent);
                }else {
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                }


            }
        },4000);


    }





}
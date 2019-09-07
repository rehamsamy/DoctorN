package com.doctorn;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity {

    Animation up;
    Handler handler;
    @BindView(R.id.logo_img)ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        handler=new Handler();

        up= AnimationUtils.loadAnimation(this,R.anim.downtoup);
        imageView.setAnimation(up);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
            startActivity(new Intent(SplashActivity.this,LoginActivity.class));
            }
        },4000);
    }
}

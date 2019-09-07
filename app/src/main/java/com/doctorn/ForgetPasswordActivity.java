package com.doctorn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.doctorn.utils.NetworkAvailable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    Animation up,down;
    NetworkAvailable available;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);


    }
}

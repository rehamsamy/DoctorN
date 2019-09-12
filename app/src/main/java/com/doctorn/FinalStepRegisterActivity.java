package com.doctorn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.doctorn.userAccount.userAccount.UserAccountActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FinalStepRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    Animation up,down;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_step_register);
        ButterKnife.bind(this);
        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
    }

    @OnClick(R.id.create_account_btn)
    void createAccountClick(){
        startActivity(new Intent(getApplicationContext(), UserAccountActivity.class));
    }
}

package com.doctorn;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Collections;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ContinuRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    Animation up,down;
    Dialog dialog;
    @BindView(R.id.accept_checkbox_id) CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_continu_register);
        ButterKnife.bind(this);
        up= AnimationUtils.loadAnimation(this,R.anim.uptodown);
        down=AnimationUtils.loadAnimation(this,R.anim.downtoup);

        imageView.setAnimation(down);
        frameLayout.setAnimation(up);
    }

    @OnClick(R.id.continu_btn)
    void continuClick(){
        
        if(checkBox.isChecked()){

            dialog=new Dialog(this);
            dialog.setContentView(R.layout.confirmation_layout);
            dialog.setCancelable(false);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            ConstraintLayout layout=dialog.findViewById(R.id.con_root);
            layout.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();

            Button button= dialog.findViewById(R.id.accept_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getApplicationContext(),RegisterCreditCardActivity.class));
                }
            });

        }else {
            Toast.makeText(getApplicationContext(), "please check the box to continue??", Toast.LENGTH_SHORT).show();
        }
        
       
    }
}

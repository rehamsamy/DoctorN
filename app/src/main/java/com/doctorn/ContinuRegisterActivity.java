package com.doctorn;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.doctorn.models.UserModel;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

public class ContinuRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.graduate_university_input)EditText graduateUniversityInput;
    @BindView(R.id.graduation_year_input)EditText graduationYearInput;
    @BindView(R.id.certification_input) EditText certificationInput;
    @BindView(R.id.degree_input) EditText degreeInput;
    @BindView(R.id.profession_license_input) EditText professionLicenceInput;
    @BindView(R.id.language_input)EditText languageInput;


    RetrofitInterface retrofitInterface;
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

           // registerDoctorInfo();

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
                    Intent intent=new Intent(getApplicationContext(),RegisterCreditCardActivity.class);
                    intent.putExtra(Cons.graduation_universty,graduateUniversityInput.getText().toString());
                    intent.putExtra(Cons.graduation_year,graduationYearInput.getText().toString());
                    intent.putExtra(Cons.specialization,certificationInput.getText().toString());
                    intent.putExtra(Cons.degree,degreeInput.getText().toString());
                    intent.putExtra(Cons.profession_license,professionLicenceInput.getText().toString());
                    intent.putExtra(Cons.languages,languageInput.getText().toString());
                    intent.setAction(Cons.registerDoctorAction);
                    startActivity(intent);
                }
            });

        }else {
            Toast.makeText(getApplicationContext(), "please check the box to continue??", Toast.LENGTH_SHORT).show();
        }
        
       
    }


}

package com.doctorn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.doctorn.doctorAccount.DoctorAccountActivity;
import com.doctorn.models.UserModel;
import com.doctorn.userAccount.userAccount.UserAccountActivity;
import com.doctorn.utils.RetrofitClientInstance;
import com.doctorn.utils.RetrofitInterface;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

public class FinalStepRegisterActivity extends AppCompatActivity {

    @BindView(R.id.logo_img)ImageView imageView;
    @BindView(R.id.card_view)FrameLayout frameLayout;
    @BindView(R.id.work_days_input) EditText workDaysInput;
    @BindView(R.id.counseling_time_input)EditText consultTimeInput;
    @BindView(R.id.price_input)EditText consultPriceInput;
    RetrofitInterface retrofitInterface;

    Intent intent;
    String graduation_university,graduation_year,degree,special,profession,language;
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

        intent=getIntent();

        if(intent.getAction().equals(Cons.registerDoctorAction)){
            graduation_university=intent.getStringExtra(Cons.graduation_universty);
            graduation_year=intent.getStringExtra(Cons.graduation_year);
            degree=intent.getStringExtra(Cons.degree);
            profession=intent.getStringExtra(Cons.profession_license);
            special=intent.getStringExtra(Cons.specialization);
            language=intent.getStringExtra(Cons.languages);
        }

    }

    @OnClick(R.id.create_account_btn)
    void createAccountClick(){

        registerDoctorInfo();
    }


    private void registerDoctorInfo() {
        retrofitInterface= RetrofitClientInstance.getRetrofit();
        Map<String,Object> map=new HashMap<>();
        map.put("graduation_universty",graduation_university);
        map.put("graduation_year",graduation_year);
        map.put("specialization",special);
        map.put("degree",degree);
        map.put("profession_license",profession);
        map.put("languages",language);
        map.put("work_days",workDaysInput.getText().toString());
        map.put("consultation_duration",consultTimeInput.getText().toString());
        map.put("consultation_price",consultPriceInput.getText().toString());
        map.put("doctor_id",7);
        map.put("api_token","JM3oe2fvFIFde4PXxjgobpquPHjbxxmOLm1epHjsB6tdQrpeaRKOJ8KGyEj1DHjM748gtX2G0PM9r14s");
        retrofit2.Call<UserModel> call=retrofitInterface.addDoctorInfo(map);
        call.enqueue(new Callback<UserModel>() {
            @Override
            public void onResponse(retrofit2.Call<UserModel> call, Response<UserModel> response) {
                if(response.body().isStatus()){
                    Intent intent=new Intent(getApplicationContext(), DoctorAccountActivity.class);
                    intent.putExtra(Cons.graduation_universty,graduation_university);
                    intent.putExtra(Cons.graduation_year,graduation_year);
                    intent.putExtra(Cons.specialization,special);
                    intent.putExtra(Cons.degree,degree);
                    intent.putExtra(Cons.profession_license,profession);
                    intent.putExtra(Cons.languages,language);
                    intent.setAction(Cons.registerDoctorAction);
                    startActivity(intent);
                    Toast.makeText(FinalStepRegisterActivity.this,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(FinalStepRegisterActivity.this,response.body().getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<UserModel> call, Throwable t) {

            }
        });
    }
}

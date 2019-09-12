package com.doctorn.userPayment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.doctorn.R;
import com.doctorn.utils.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserPaymentActivity extends AppCompatActivity {

    @BindView(R.id.back_eng)ImageView backEng;
    @BindView(R.id.back_ar) ImageView backAr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_payment);
        ButterKnife.bind(this);

        if(PreferenceHelper.getValue(this).equals("ar")){
            backEng.setVisibility(View.GONE);
            backAr.setVisibility(View.VISIBLE);
        }else if(PreferenceHelper.getValue(this).equals("en")){
            backAr.setVisibility(View.VISIBLE);
            backEng.setVisibility(View.GONE);
        }
    }
}
